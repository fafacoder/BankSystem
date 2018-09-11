package com.cx.bank.util;

//存款为负数时抛出异常
public class AccountOverDrawnException extends Exception{
	private static final long serialVersionUID = 1L;
    public AccountOverDrawnException(){}
    public AccountOverDrawnException(String message){
    	super(message);
    }
}
