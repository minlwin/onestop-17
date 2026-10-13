package com.jdc.balance.balanceapi;

import com.jdc.balance.balanceapi.model.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BalanceApiJpaConfig {
}
