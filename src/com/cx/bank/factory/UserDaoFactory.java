package com.cx.bank.factory;

import com.cx.bank.dao.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 * Created by s3 on 2018/7/17.
 */
public class UserDaoFactory {
    private volatile static UserDaoFactory in;
    private static BankDaoInterface dao;

    //构造方法装配业务层对象
    public UserDaoFactory(){
        try{
            //读取配置文件
            FileInputStream file = new FileInputStream(new File("classInfo.properties"));
            Properties pro = new Properties();
            pro.load(file);
            file.close();
            String url = pro.getProperty("className");

            //通过反射创建dao层对象
            Class clazz = Class.forName(url);
            dao = (FiledaoImpl)clazz.newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized BankDaoInterface createDaoFactory() {
        return dao;
    }

    /**
     * 获取实例方法  使用DCL双重锁检测机制方法
     * @return
     */
    public static UserDaoFactory getInstance(){
        if(in == null){
            synchronized (UserDaoFactory.class){
                if(in == null){
                    in = new UserDaoFactory();
                }
            }
        }
        return in;
    }
}
