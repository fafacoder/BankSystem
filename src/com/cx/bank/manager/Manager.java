package com.cx.bank.manager;

public interface Manager{
    //注册
    boolean register(String name, String password);

    //登录
    boolean login(String name, String password);

    //转账
    String transfer(String userName, String money);

    //查询余额方法
    String inquiry();

    //存款
    String deposit(String money);

    //取款
    String withdraw(String money);
}
