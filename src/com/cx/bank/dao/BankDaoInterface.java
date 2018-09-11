package com.cx.bank.dao;

public interface BankDaoInterface {
    //注册
    boolean register(String userName,String password);

    //登录
    boolean login(String userName,String password);

    //查询
    double getBankMoney();

    //转账
    boolean transfer(String otherName,String money);

    //存款
    void deposit(double money);

    //取款
    boolean withdraw(double money);

}
