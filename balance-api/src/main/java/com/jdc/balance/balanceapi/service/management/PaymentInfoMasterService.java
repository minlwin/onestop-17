package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.PaymentInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.domain.master.PaymentInfo;
import com.jdc.balance.balanceapi.model.repo.master.PaymentInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentInfoMasterService {

    private final PaymentInfoRepo repo;

    public List<PaymentInfoDto> findAll() {
        return repo.findAll().stream().map(PaymentInfoDto::from).toList();
    }

    public PaymentInfoDto findById(int id) {
        return safeCall(repo.findById(id).map(PaymentInfoDto::from))
                .apply("Payment Information").apply("id").apply(String.valueOf(id));
    }

    @Transactional
    public ModificationResult<Integer> create(PaymentInfoForm form) {
        var entity = new PaymentInfo();

        entity.setProvider(form.provider());
        entity.setAccountNo(form.accountNo());
        entity.setAccountName(form.accountName());

        entity = repo.save(entity);
        return new ModificationResult<>(entity.getId());
    }

    @Transactional
    public ModificationResult<Integer> update(int id, PaymentInfoForm form) {
        var entity = safeCall(repo.findById(id))
                .apply("Payment Information").apply("id").apply(String.valueOf(id));

        entity.setProvider(form.provider());
        entity.setAccountNo(form.accountNo());
        entity.setAccountName(form.accountName());

        return new ModificationResult<>(entity.getId());
    }
}
