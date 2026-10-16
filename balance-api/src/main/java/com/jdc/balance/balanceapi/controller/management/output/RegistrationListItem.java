package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.UUID;

public record RegistrationListItem(
        UUID id,
        String partnerName,
        String phone,
        String email,
        String position,
        String company,
        String companyType,
        String companyAddress,
        RegistrationStatus status,
        LocalDate registeredAt,
        LocalDate approvedAt
) {
    public static void select(CriteriaBuilder cb, CriteriaQuery<RegistrationListItem> cq, Root<Company> root) {
        cq.select(cb.construct(RegistrationListItem.class,
                root.get(Company_.id),
                root.get(Company_.partner).get(Partner_.account).get(Account_.name),
                root.get(Company_.partner).get(Partner_.phone),
                root.get(Company_.partner).get(Partner_.email),
                root.get(Company_.partner).get(Partner_.position),
                root.get(Company_.name),
                root.get(Company_.category),
                root.get(Company_.address),
                root.get(Company_.status),
                root.get(Company_.registerAt),
                root.get(Company_.approvedAt)
        ));
    }
}
