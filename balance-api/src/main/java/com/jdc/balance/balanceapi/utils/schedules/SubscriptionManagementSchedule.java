package com.jdc.balance.balanceapi.utils.schedules;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionManagementSchedule {

    @Scheduled(cron = "0 10 0 * * *")
    public void execute() {

    }
}
