package com.jdc.balance.balanceapi.model.entity.consts;

public enum AccountRole {
    Administrator, Management, Partner, Employee;

    public static boolean isMember(AccountRole role) {
        return role == Partner || role == Employee;
    }
}
