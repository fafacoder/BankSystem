package com.cx.bank.manager;

import com.cx.bank.dao.BankDaoInterface;
import com.cx.bank.dao.FiledaoImpl;
import com.cx.bank.factory.UserDaoFactory;


public class ManagerImpl implements Manager {

    private BankDaoInterface dao = new FiledaoImpl();

    public ManagerImpl(){
        UserDaoFactory factory = UserDaoFactory.getInstance();
        dao = factory.createDaoFactory();
    }

    /**
     * 注册方法
     * @param name  用户名
     * @param password  用户密码
     */
    public boolean register(String name, String password){
        return dao.register(name, password);
    }

    /**
     * 登录方法
     * @param name  用户名
     * @param password  用户密码
     * @return 返回登录验证消息(是否登录成功)
     */
    public boolean login(String name, String password){
        return dao.login(name, password);
    }
	
    /**
     * 转账
     * @param otherName 转账对象用户名
     * @param money 转账金额
     * @return 返回账户余额
     */
    public String transfer(String otherName, String money){
        if(dao.transfer(otherName, money) == true){
            return ("转账成功,当前余额为：" + String.valueOf(dao.getBankMoney()));
        }
        return "转账失败,账户余额不足";
    }

    /**
     * 查询
     * @return  返回账户余额
     */
    public String inquiry(){
	return String.valueOf(dao.getBankMoney());
    }
    
    /**
     * 存款
     * @param money 存款金额
     * @return 返回账户余额
     */
    public String deposit(String money){
        dao.deposit(Double.parseDouble(money));
        return String.valueOf(dao.getBankMoney());
    }
    
    /**
     * 取款 
     * @param money 取款金额
     * @return 返回账户余额
     */
    public String withdraw(String money){
        if(dao.withdraw(Double.parseDouble(money)) == true){
            return ("取款成功,当前余额为：" + String.valueOf(dao.getBankMoney()) + "元");
        }
        return "取款失败,账户余额不足";
    }

}
