package com.cx.bank.util;

//取款超出余额时抛出异常
public class InvalidDepositException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidDepositException(){}
    public InvalidDepositException(String message){
    	super(message);
    }    
}
