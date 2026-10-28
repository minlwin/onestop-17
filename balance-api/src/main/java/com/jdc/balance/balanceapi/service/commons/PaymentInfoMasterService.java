package com.jdc.balance.balanceapi.service.commons;

import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.model.entity.domain.master.PaymentInfo;
import com.jdc.balance.balanceapi.model.repo.master.PaymentInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentInfoMasterService {

    private final PaymentInfoRepo repo;

    @Cacheable("payment.list")
    public List<PaymentInfoDto> findAll() {
        return repo.findAll().stream().map(PaymentInfoDto::from).toList();
    }

    @Cacheable(value = "payment.master", key = "#id")
    public PaymentInfoDto findById(int id) {
        return safeCall(repo.findById(id).map(PaymentInfoDto::from))
                .apply("Payment Information").apply("id").apply(String.valueOf(id));
    }

    public PaymentInfo getReference(Integer id) {
        return repo.getReferenceById(id);
    }
}
