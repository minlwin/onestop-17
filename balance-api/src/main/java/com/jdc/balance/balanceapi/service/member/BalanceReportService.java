package com.jdc.balance.balanceapi.service.member;

import com.jdc.balance.balanceapi.controller.member.input.BalanceSearch;
import com.jdc.balance.balanceapi.controller.member.output.BalanceListItem;
import com.jdc.balance.balanceapi.controller.member.output.BalanceSearchResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntryItem_;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Ledger_;
import com.jdc.balance.balanceapi.model.repo.business.LedgerEntryRepo;
import com.jdc.balance.balanceapi.service.LoginCompanyService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BalanceReportService {

    private final LoginCompanyService loginCompanyService;
    private final LedgerEntryRepo entryRepo;

    @Transactional(readOnly = true)
    public BalanceSearchResult search(BalanceSearch form, int page, int size) {

        var company = loginCompanyService.getInfo();
        var result = searchResult(company, form, page, size);

        return new BalanceSearchResult(
                getBalance(company, form.from(),true),
                getBalance(company, form.to(), false),
                result
        );
    }

    private BigDecimal getBalance(Company company, LocalDate date, boolean open) {
        if(null == date && open) {
            return  BigDecimal.ZERO;
        }
        var searchDate = Optional.ofNullable(date).map(a -> open ? a : a.plusDays(1)).orElse(null);
        var debitTotal = getBalance(company, searchDate, EntryType.Debit);
        var creditTotal = getBalance(company, searchDate, EntryType.Credit);
        return  debitTotal.subtract(creditTotal);
    }

    private BigDecimal getBalance(Company company, LocalDate date, EntryType type) {

        Function<CriteriaBuilder, CriteriaQuery<BigDecimal>> queryFunc = cb -> {
            var cq = cb.createQuery(BigDecimal.class);
            var root = cq.from(LedgerEntry.class);
            var items = root.join(LedgerEntry_.items, JoinType.LEFT);
            cq.select(cb.sum(cb.prod(items.get(LedgerEntryItem_.unitPrice), items.get(LedgerEntryItem_.unitPrice))));

            var params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get(LedgerEntry_.ledger).get(Ledger_.company).get(Company_.id), company.getId()));

            if(null != date) {
                params.add(cb.lessThan(root.get(LedgerEntry_.issueDate) ,date));
            }

            cq.where(params);
            return  cq;
        };

        return entryRepo.search(queryFunc).stream().findAny().orElseGet(() -> BigDecimal.ZERO);
    }

    private PageResult<BalanceListItem> searchResult(Company company, BalanceSearch form, int page, int size) {

        Function<CriteriaBuilder, CriteriaQuery<BalanceListItem>> queryFunction = cb -> {
            var cq = cb.createQuery(BalanceListItem.class);
            var root = cq.from(LedgerEntry.class);
            var items = root.join(LedgerEntry_.items, JoinType.LEFT);

            BalanceListItem.select(cb, cq, root, items);
            cq.where(form.where(cb, root, company));
            cq.orderBy(cb.asc(root.get(LedgerEntry_.issueDate)));

            return cq;
        };

        Function<CriteriaBuilder, CriteriaQuery<Long>> countFunction = cb -> {
            var cq = cb.createQuery(Long.class);
            var root = cq.from(LedgerEntry.class);
            cq.select(cb.count(root.get(LedgerEntry_.id)));
            cq.where(form.where(cb, root, company));
            return cq;
        };

        return entryRepo.search(queryFunction, countFunction, page, size);
    }
}
