package com.cx.bank.util;

//���Ϊ����ʱ�׳��쳣
public class AccountOverDrawnException extends Exception{
	private static final long serialVersionUID = 1L;
    public AccountOverDrawnException(){}
    public AccountOverDrawnException(String message){
    	super(message);
    }
}
