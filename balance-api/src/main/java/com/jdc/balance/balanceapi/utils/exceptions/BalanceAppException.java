package com.jdc.balance.balanceapi.utils.exceptions;

public class BalanceAppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public BalanceAppException(String message) {
        super(message);
    }

    public BalanceAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceAppException(Throwable cause) {
        super(cause);
    }
}
