package com.jdc.balance.balanceapi.service.member;

import com.jdc.balance.balanceapi.controller.member.input.EntryForm;
import com.jdc.balance.balanceapi.controller.member.input.EntrySearch;
import com.jdc.balance.balanceapi.controller.member.output.EntryDetails;
import com.jdc.balance.balanceapi.controller.member.output.EntryListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntryItem;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry_;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.EnterBy;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.LedgerEntryItemPk;
import com.jdc.balance.balanceapi.model.repo.business.LedgerEntryItemRepo;
import com.jdc.balance.balanceapi.model.repo.business.LedgerEntryRepo;
import com.jdc.balance.balanceapi.model.repo.business.LedgerRepo;
import com.jdc.balance.balanceapi.service.LoginCompanyService;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Function;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class LedgerEntryService {

    private final LoginCompanyService loginCompanyService;
    private final LedgerEntryRepo entryRepo;
    private final LedgerEntryItemRepo itemRepo;
    private final LedgerRepo ledgerRepo;

    @Transactional(readOnly = true)
    public PageResult<EntryListItem> search(EntrySearch form, int page, int size) {
        var company = loginCompanyService.getInfo();
        Function<CriteriaBuilder, CriteriaQuery<EntryListItem>> queryFunction = cb -> {
            var cq = cb.createQuery(EntryListItem.class);
            var root = cq.from(LedgerEntry.class);
            var items = root.join(LedgerEntry_.items, JoinType.LEFT);

            EntryListItem.select(cb, cq, root, items);
            cq.where(form.where(cb, root, company));

            cq.orderBy(cb.desc(root.get(LedgerEntry_.issueDate)));
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

    @Transactional
    public ModificationResult<UUID> create(EntryForm form) {
        var company = loginCompanyService.getInfo();
        var account = loginCompanyService.getAccount();
        var enterBy = new EnterBy(account.getId(), account.getName());

        var ledger = safeCall(ledgerRepo.findById(UUID.fromString(form.ledgerId())))
                .apply("Ledger").apply("id").apply(form.ledgerId());

        if(!company.getId().equals(ledger.getCompany().getId())) {
            throw new BalanceAppException("You can't use ledger of other company.");
        }

        var entry = new LedgerEntry();
        entry.setLedger(ledger);
        entry.setEnterBy(enterBy);
        entry.setIssueDate(form.issueDate());
        entry.setParticular(form.particular());

        entry = entryRepo.save(entry);

        for(var index = 0; index < form.items().size(); index ++) {
            var item = form.items().get(index);

            var entryItem = new LedgerEntryItem();
            entryItem.setId(new LedgerEntryItemPk(entry.getId(), index + 1));
            entryItem.setEntry(entry);
            entryItem.setArticle(item.article());
            entryItem.setQuantity(item.quantity());
            entryItem.setUnitPrice(item.unitPrice());
            itemRepo.save(entryItem);
        }

        return new ModificationResult<>(entry.getId());
    }

    @Transactional(readOnly = true)
    public EntryDetails findById(String id) {
        return safeCall(entryRepo.findById(UUID.fromString(id)).map(EntryDetails::from))
                .apply("Ledger entry").apply("id").apply(id);
    }
}
