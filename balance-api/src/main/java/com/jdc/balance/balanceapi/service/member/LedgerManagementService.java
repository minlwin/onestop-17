package com.jdc.balance.balanceapi.service.member;

import com.jdc.balance.balanceapi.controller.member.input.LedgerForm;
import com.jdc.balance.balanceapi.controller.member.input.LedgerSearch;
import com.jdc.balance.balanceapi.controller.member.output.LedgerListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Ledger;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Ledger_;
import com.jdc.balance.balanceapi.model.repo.business.LedgerRepo;
import com.jdc.balance.balanceapi.service.LoginCompanyService;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@Transactional
@RequiredArgsConstructor
public class LedgerManagementService {

    private final LoginCompanyService loginCompanyService;
    private final LedgerRepo ledgerRepo;

    @Transactional(readOnly = true)
    public List<LedgerListItem> search(LedgerSearch search) {

        var company = loginCompanyService.getInfo();

        return ledgerRepo.search(cb -> {
            var cq = cb.createQuery(LedgerListItem.class);
            var root = cq.from(Ledger.class);

            var entries = root.join(Ledger_.entry, JoinType.LEFT);
            var items = entries.join(LedgerEntry_.items, JoinType.LEFT);

            LedgerListItem.select(cb, cq, root, items);

            var params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get(Ledger_.company).get(Company_.id), company.getId()));

            if(null != search.type()) {
                params.add(cb.equal(root.get(Ledger_.type), search.type()));
            }
            cq.where(params);

            return cq;
        });
    }

    public ModificationResult<UUID> create(LedgerForm form) {
        var company = loginCompanyService.getInfo();
        var ledger = new Ledger();
        ledger.setCompany(company);
        ledger.setType(form.type());
        ledger.setName(form.name());
        ledger.setDescription(form.description());
        ledger = ledgerRepo.save(ledger);
        return new ModificationResult<>(ledger.getId());
    }

    public ModificationResult<UUID> update(String id, LedgerForm form) {
        var ledger = safeCall(ledgerRepo.findById(UUID.fromString(id)))
                .apply("Ledger").apply("id").apply(id);

        var company = loginCompanyService.getInfo();

        if(!ledger.getCompany().getId().equals(company.getId())) {
            throw new BalanceAppException("You can't update ledger of other company.");
        }

        ledger.setType(form.type());
        ledger.setName(form.name());
        ledger.setDescription(form.description());

        return new ModificationResult<>(ledger.getId());
    }
}
