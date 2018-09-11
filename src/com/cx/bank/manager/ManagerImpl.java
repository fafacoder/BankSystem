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
     * ע�᷽��
     * @param name  �û���
     * @param password  �û�����
     */
    public boolean register(String name, String password){
        return dao.register(name, password);
    }

    /**
     * ��¼����
     * @param name  �û���
     * @param password  �û�����
     * @return ���ص�¼��֤��Ϣ(�Ƿ��¼�ɹ�)
     */
    public boolean login(String name, String password){
        return dao.login(name, password);
    }
	
    /**
     * ת��
     * @param otherName ת�˶����û���
     * @param money ת�˽��
     * @return �����˻����
     */
    public String transfer(String otherName, String money){
        if(dao.transfer(otherName, money) == true){
            return ("ת�˳ɹ�,��ǰ���Ϊ��" + String.valueOf(dao.getBankMoney()));
        }
        return "ת��ʧ��,�˻�����";
    }

    /**
     * ��ѯ
     * @return  �����˻����
     */
    public String inquiry(){
	return String.valueOf(dao.getBankMoney());
    }
    
    /**
     * ���
     * @param money �����
     * @return �����˻����
     */
    public String deposit(String money){
        dao.deposit(Double.parseDouble(money));
        return String.valueOf(dao.getBankMoney());
    }
    
    /**
     * ȡ�� 
     * @param money ȡ����
     * @return �����˻����
     */
    public String withdraw(String money){
        if(dao.withdraw(Double.parseDouble(money)) == true){
            return ("ȡ��ɹ�,��ǰ���Ϊ��" + String.valueOf(dao.getBankMoney()) + "Ԫ");
        }
        return "ȡ��ʧ��,�˻�����";
    }

}
