package com.cx.bank.dao;

public interface BankDaoInterface {
    //ע��
    boolean register(String userName,String password);

    //��¼
    boolean login(String userName,String password);

    //��ѯ
    double getBankMoney();

    //ת��
    boolean transfer(String otherName,String money);

    //���
    void deposit(double money);

    //ȡ��
    boolean withdraw(double money);

}
