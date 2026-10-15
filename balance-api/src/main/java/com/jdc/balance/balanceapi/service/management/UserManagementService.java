package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.RetirementForm;
import com.jdc.balance.balanceapi.controller.management.input.UserForm;
import com.jdc.balance.balanceapi.controller.management.input.UserSearch;
import com.jdc.balance.balanceapi.controller.management.output.UserDetails;
import com.jdc.balance.balanceapi.controller.management.output.UserListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.model.entity.consts.UserStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member_;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.model.repo.account.MemberRepo;
import com.jdc.balance.balanceapi.utils.listener.AccountCreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserManagementService {

    private final AccountRepo accountRepo;
    private final MemberRepo memberRepo;
    private final ApplicationEventPublisher eventPublisher;

    public List<UserListItem> search(UserSearch form) {
        return memberRepo.search(cb -> {
            var cq = cb.createQuery(UserListItem.class);
            var root = cq.from(Member.class);

            UserListItem.select(cq, cb, root);
            cq.where(form.where(cb, root));

            cq.orderBy(cb.desc(root.get(Member_.assignDate)));

            return cq;
        });
    }

    public UserDetails findById(String id) {
        return safeCall(memberRepo.findById(UUID.fromString(id)).map(UserDetails::from))
                .apply("User").apply("id").apply(id);
    }

    @Transactional
    public ModificationResult<UUID> create(UserForm form) {

        var account = new Account();
        account.setUsername(form.email());
        account.setRole(AccountRole.Management);
        account = accountRepo.save(account);

        var member = new Member();
        member.setAccount(account);
        member.setName(form.name());
        member.setPhone(form.phone());
        member.setEmail(form.email());
        member.setStatus(UserStatus.Pending);
        member.setAssignDate(form.assignDate());
        memberRepo.save(member);

        eventPublisher.publishEvent(new AccountCreationEvent(account.getId()));

        return new ModificationResult<>(account.getId());
    }

    @Transactional
    public ModificationResult<UUID> update(String id, RetirementForm form) {
        var entity = safeCall(memberRepo.findById(UUID.fromString(id)))
                .apply("User").apply("id").apply(id);

        entity.setResignDate(form.retireDate());

        return new ModificationResult<>(entity.getId());
    }
}
