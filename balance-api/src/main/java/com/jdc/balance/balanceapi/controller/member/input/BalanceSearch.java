package com.jdc.balance.balanceapi.controller.member.input;

import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Ledger_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record BalanceSearch(LocalDate from, LocalDate to) {
    public List<Predicate> where(CriteriaBuilder cb, Root<LedgerEntry> root, Company company) {
        ArrayList<Predicate> params = new ArrayList<>();
        params.add(cb.equal(root.get(LedgerEntry_.ledger).get(Ledger_.company).get(Company_.id), company.getId()));

        if(null != from) {
            params.add(cb.greaterThanOrEqualTo(root.get(LedgerEntry_.issueDate), from));
        }

        if(null != to) {
            params.add(cb.lessThanOrEqualTo(root.get(LedgerEntry_.issueDate), to));
        }
        return params;
    }
}
