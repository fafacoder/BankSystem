package com.cx.bank.dao;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cx.bank.model.UserBean;
import com.cx.bank.util.MD5;

public class FiledaoImpl implements BankDaoInterface{

    /*�½�����أ���֤�û��޷�ͬʱ��¼�������
      ����¼�������ʱ�߳�����
      �ڶ������ʹ��hashMap��UserBean����Ϊ��,�û���Ϊֵ
    */
    Map<String, UserBean> obj = new HashMap<>();

    private UserBean bean;
    private MD5 md5 = new MD5();

    /**
     * ע��
     * @param userName  �û���
     * @param password  �û�����
     */
    public boolean register(String userName,String password){
        try{
            //��ȡע���ļ�����
            String url = userName + ".properties";
            String passwordMd5 = md5.encode(password.getBytes());
            File file = new File(url);
            Properties pro = new Properties();

            //�ж��ļ��û����Ƿ���� �������򷵻�false
            if(file.exists()){
                return false;
            }else{
                //��ȡ����ģ���ļ�
                FileInputStream fileName = new FileInputStream(new File("bank.properties"));
                pro.load(fileName);
                fileName.close();

                //�洢���õ�������û�������ʼ����ǮΪ0
                pro.setProperty("userName", userName);
                pro.setProperty("password",passwordMd5);
                pro.setProperty("money", "0");

                //��ע��������������ļ���
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
     * ��¼����
     * @param userName  ��¼�û���
     * @param password  ��¼����
     * @return ������Ϊ1��¼�ɹ�,Ϊ0��¼ʧ��
     */
    public boolean login(String userName,String password){
        try {

            //��ȡ�û��ļ�
            FileInputStream file = new FileInputStream(userName + ".properties");
            Properties pro = new Properties();
            pro.load(file);
            file.close();

            //�Ƚ��û���������
            if((pro.getProperty("userName").equals(userName)) &&
                    (pro.getProperty("password").equals(md5.encode(password.getBytes())))){

                //�жϵ�ǰ��������Ƿ���ڸ��û�������������򷵻�false
                if(obj.get(userName) != null){
                    return false;
                }

                //ʵ����UserBean���󲢽����ݸ�ֵ
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
     * ��ѯ���
     * @return ���ص�ǰ���
     */
    public double getBankMoney(){
        System.out.println(bean.getMoney());
        return bean.getMoney();
    }

    /**
     * ת�˷���
     * @param otherName  ת�˶����û���
     * @param money  ת�˽��
     */
    public boolean transfer(String otherName,String money){
        try{
            //����f1Ϊ�����˻�������,f2Ϊ�����˻�������
            FileInputStream f1 = new FileInputStream(new File(bean.getName() + ".properties"));
            FileInputStream f2 = new FileInputStream(new File(otherName + ".properties"));
            Properties pro1 = new Properties();
            Properties pro2 = new Properties();
            pro1.load(f1);
            pro2.load(f2);
            f1.close();
            f2.close();

            //����moneyInΪ�����˻����,moneyToΪ�����˻����
            double moneyIn = Double.parseDouble(pro1.getProperty("money"));
            double moneyTo = Double.parseDouble(pro2.getProperty("money"));

            //�ж�ת�˽���Ƿ�����˻����
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
     * ��� ���ص�ǰ���
     * @param money �����
     */
    public void deposit(double money){
        try{
            //�����û��ļ�
            FileInputStream fl = new FileInputStream(new File(bean.getName() + ".properties"));
            Properties pro = new Properties();
            pro.load(fl);
            fl.close();

            //���½��
            double inMoney = (Double.parseDouble(pro.getProperty("money"))) + money;
            pro.setProperty("money", String.valueOf(inMoney));
            bean.setMoney(inMoney);

            //ͬ���ļ���Ϣ
            saveFile(bean.getName(), inMoney);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
	
    /**
     * ȡ��  ���ص�ǰ���
     * @param money ȡ����
     */
    public boolean withdraw(double money){
        try{
            //�����û��ļ�
            FileInputStream fl = new FileInputStream(new File(bean.getName() + ".properties"));
            Properties pro = new Properties();
            pro.load(fl);
            fl.close();

            //����˻����С��ȡ����,����false
            if(Double.parseDouble(pro.getProperty("money")) < money ){
                return false;
            }

            //���½��
            double outMoney = Double.parseDouble(pro.getProperty("money")) - money;
            pro.setProperty("money", String.valueOf(outMoney));
            bean.setMoney(outMoney);

            //ͬ���ļ���Ϣ
            saveFile(bean.getName(), outMoney);

        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * �����ݸ��µ��û��ļ���
     * @param userName �û����ļ�
     */
    public void saveFile(String userName,double money){
        try{
            //�����û��ļ�
            Properties pro = new Properties();
            FileInputStream in = new FileInputStream(new File(userName + ".properties"));
            pro.load(in);
            in.close();

            //���½������
            pro.setProperty("money", String.valueOf(money));

            //�����ļ�
            FileOutputStream out = new FileOutputStream(new File(userName + ".properties"));
            pro.store(out,"money");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
