package com.cx.bank.dao;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cx.bank.model.UserBean;
import com.cx.bank.util.MD5;

public class FiledaoImpl implements BankDaoInterface{

    /*新建对象池，保证用户无法同时登录多个界面
      当登录多个界面时线程阻塞
      在对象池中使用hashMap以UserBean对象为键,用户名为值
    */
    Map<String, UserBean> obj = new HashMap<>();

    private UserBean bean;
    private MD5 md5 = new MD5();

    /**
     * 注册
     * @param userName  用户名
     * @param password  用户密码
     */
    public boolean register(String userName,String password){
        try{
            //获取注册文件对象
            String url = userName + ".properties";
            String passwordMd5 = md5.encode(password.getBytes());
            File file = new File(url);
            Properties pro = new Properties();

            //判断文件用户名是否存在 不存在则返回false
            if(file.exists()){
                return false;
            }else{
                //读取本地模版文件
                FileInputStream fileName = new FileInputStream(new File("bank.properties"));
                pro.load(fileName);
                fileName.close();

                //存储设置的密码和用户名并初始化金钱为0
                pro.setProperty("userName", userName);
                pro.setProperty("password",passwordMd5);
                pro.setProperty("money", "0");

                //将注册数据输出到新文件中
                OutputStream out = new FileOutputStream(new File(url));
                pro.store(out,url);
                out.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 登录方法
     * @param userName  登录用户名
     * @param password  登录密码
     * @return 返回若为1登录成功,为0登录失败
     */
    public boolean login(String userName,String password){
        try {

            //读取用户文件
            FileInputStream file = new FileInputStream(userName + ".properties");
            Properties pro = new Properties();
            pro.load(file);
            file.close();

            //比较用户名和密码
            if((pro.getProperty("userName").equals(userName)) &&
                    (pro.getProperty("password").equals(md5.encode(password.getBytes())))){

                //判断当前对象池中是否存在该用户名，如果存在则返回false
                if(obj.get(userName) != null){
                    return false;
                }

                //实例化UserBean对象并将数据赋值
                bean = new UserBean();
                obj.put(userName,bean);
                bean.setName(pro.getProperty("userName"));
                bean.setMoney(Double.parseDouble(pro.getProperty("money")));

                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 查询余额
     * @return 返回当前余额
     */
    public double getBankMoney(){
        System.out.println(bean.getMoney());
        return bean.getMoney();
    }

    /**
     * 转账方法
     * @param otherName  转账对象用户名
     * @param money  转账金额
     */
    public boolean transfer(String otherName,String money){
        try{
            //定义f1为本机账户数据流,f2为对象账户数据流
            FileInputStream f1 = new FileInputStream(new File(bean.getName() + ".properties"));
            FileInputStream f2 = new FileInputStream(new File(otherName + ".properties"));
            Properties pro1 = new Properties();
            Properties pro2 = new Properties();
            pro1.load(f1);
            pro2.load(f2);
            f1.close();
            f2.close();

            //定义moneyIn为本机账户余额,moneyTo为对象账户余额
            double moneyIn = Double.parseDouble(pro1.getProperty("money"));
            double moneyTo = Double.parseDouble(pro2.getProperty("money"));

            //判断转账金额是否大于账户余额
            if(Double.parseDouble(money) > moneyIn){
                return false;
            }else{
                moneyIn = moneyIn - Double.parseDouble(money);
                moneyTo = moneyTo + Double.parseDouble(money);
                pro1.setProperty("money", String.valueOf(moneyIn));
                pro2.setProperty("money", String.valueOf(moneyTo));
                pro1.store(new FileOutputStream(new File(bean.getName() + ".properties")),"money");
                pro2.store(new FileOutputStream(new File(otherName + ".properties")),"money");

                bean.setMoney(moneyIn);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 存款 返回当前余额
     * @param money 存款金额
     */
    public void deposit(double money){
        try{
            //导入用户文件
            FileInputStream fl = new FileInputStream(new File(bean.getName() + ".properties"));
            Properties pro = new Properties();
            pro.load(fl);
            fl.close();

            //更新金额
            double inMoney = (Double.parseDouble(pro.getProperty("money"))) + money;
            pro.setProperty("money", String.valueOf(inMoney));
            bean.setMoney(inMoney);

            //同步文件信息
            saveFile(bean.getName(), inMoney);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
	
    /**
     * 取款  返回当前余额
     * @param money 取款金额
     */
    public boolean withdraw(double money){
        try{
            //导入用户文件
            FileInputStream fl = new FileInputStream(new File(bean.getName() + ".properties"));
            Properties pro = new Properties();
            pro.load(fl);
            fl.close();

            //如果账户金额小于取款金额,返回false
            if(Double.parseDouble(pro.getProperty("money")) < money ){
                return false;
            }

            //更新金额
            double outMoney = Double.parseDouble(pro.getProperty("money")) - money;
            pro.setProperty("money", String.valueOf(outMoney));
            bean.setMoney(outMoney);

            //同步文件信息
            saveFile(bean.getName(), outMoney);

        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 将数据更新到用户文件中
     * @param userName 用户名文件
     */
    public void saveFile(String userName,double money){
        try{
            //导入用户文件
            Properties pro = new Properties();
            FileInputStream in = new FileInputStream(new File(userName + ".properties"));
            pro.load(in);
            in.close();

            //更新金额数据
            pro.setProperty("money", String.valueOf(money));

            //导出文件
            FileOutputStream out = new FileOutputStream(new File(userName + ".properties"));
            pro.store(out,"money");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
