package com.cx.bank.manager;

public interface Manager{
    //ע��
    boolean register(String name, String password);

    //��¼
    boolean login(String name, String password);

    //ת��
    String transfer(String userName, String money);

    //��ѯ����
    String inquiry();

    //���
    String deposit(String money);

    //ȡ��
    String withdraw(String money);
}
