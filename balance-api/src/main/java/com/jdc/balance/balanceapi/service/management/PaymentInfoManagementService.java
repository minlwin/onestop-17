package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.PaymentInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.model.entity.domain.master.PaymentInfo;
import com.jdc.balance.balanceapi.model.repo.master.PaymentInfoRepo;
import com.jdc.balance.balanceapi.service.commons.PaymentInfoMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentInfoManagementService {

    private final PaymentInfoRepo repo;
    private final PaymentInfoMasterService masterService;

    public List<PaymentInfoDto> findAll() {
        return masterService.findAll();
    }

    public PaymentInfoDto findById(int id) {
        return masterService.findById(id);
    }

    @Transactional
    @CachePut(value = "payment.master", key = "#result.id()")
    @CacheEvict(value = "payment.list", allEntries = true)
    public PaymentInfoDto create(PaymentInfoForm form) {
        var entity = new PaymentInfo();

        entity.setProvider(form.provider());
        entity.setAccountNo(form.accountNo());
        entity.setAccountName(form.accountName());

        entity = repo.save(entity);
        return PaymentInfoDto.from(entity);
    }

    @Transactional
    @CachePut(value = "payment.master", key = "#result.id()")
    @CacheEvict(value = "payment.list", allEntries = true)
    public PaymentInfoDto update(int id, PaymentInfoForm form) {
        var entity = safeCall(repo.findById(id))
                .apply("Payment Information").apply("id").apply(String.valueOf(id));

        entity.setProvider(form.provider());
        entity.setAccountNo(form.accountNo());
        entity.setAccountName(form.accountName());

        return PaymentInfoDto.from(entity);
    }
}
