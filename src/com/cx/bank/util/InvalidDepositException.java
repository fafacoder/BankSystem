package com.cx.bank.util;

//ȡ������ʱ�׳��쳣
public class InvalidDepositException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidDepositException(){}
    public InvalidDepositException(String message){
    	super(message);
    }    
}
