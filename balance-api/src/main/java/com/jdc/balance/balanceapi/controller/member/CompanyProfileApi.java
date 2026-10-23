package com.jdc.balance.balanceapi.controller.member;

import com.jdc.balance.balanceapi.controller.member.output.CompanyProfileDto;
import com.jdc.balance.balanceapi.service.member.CompanyProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("member/company")
public class CompanyProfileApi {

    private final CompanyProfileService service;

    @GetMapping
    CompanyProfileDto findProfile() {
        return service.getProfile();
    }
}
