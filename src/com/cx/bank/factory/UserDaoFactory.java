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

    //���췽��װ��ҵ������
    public UserDaoFactory(){
        try{
            //��ȡ�����ļ�
            FileInputStream file = new FileInputStream(new File("classInfo.properties"));
            Properties pro = new Properties();
            pro.load(file);
            file.close();
            String url = pro.getProperty("className");

            //ͨ�����䴴��dao�����
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
     * ��ȡʵ������  ʹ��DCL˫���������Ʒ���
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
