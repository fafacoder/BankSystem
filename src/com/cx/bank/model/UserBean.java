package com.cx.bank.model;

public class UserBean {
    private String name;
    private double money;

    /*
    //��֤����ton��ԭ����
    private volatile UserBean ton = null;

    //DCL˫�ؼ�����ģʽ
    public UserBean getInstance(){
    	if(ton == null){
            synchronized(UserBean.class){
                if(ton == null){
                    ton = new UserBean();
                }
            }
    	}
    	return ton;
    }
    */


    public double getMoney()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
