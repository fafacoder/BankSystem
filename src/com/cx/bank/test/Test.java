package com.cx.bank.test;
import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.manager.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class Thread1 extends Thread{
    private View test;
    public Thread1(View test){
        super();
        this.test = test;
    }
    public void run(){
        test.LoginView();
    }
}

class Thread2 extends Thread{
    private View test;
    public Thread2(View test){
        super();
        this.test = test;
    }
    public void run(){
        test.LoginView();
    }
}

public class Test{

    public static void main(String[] args) {
        //�û�1 �û�����new  �û����룺123
        //�û�2 �û�����Huar �û����룺123
        View test1 = new View();
        View test2 = new View();
        Thread1 str1 = new Thread1(test1);
        Thread2 str2 = new Thread2(test2);
        str1.start();
        str2.start();
    }

}

class View extends JFrame{
    Manager str=new ManagerImpl();

    /**
    * ��¼����
    */

    public void LoginView(){
        //������¼������
        JFrame login=new JFrame("login");
        login.setSize(350,200);   //350,250
        login.setLocation(550,300);
        Image icon=Toolkit.getDefaultToolkit().getImage("src/com/cx/bank/test/big.jpg");
        login.setIconImage(icon);

        //������¼���滭��
        JPanel panel=new JPanel();
        panel.setLayout(null);

        //���������ı���
        JLabel loginLable = new JLabel("Bank System 1.4");
        loginLable.setBounds(125,10,150,25);
        panel.add(loginLable);

        // �����û������ı���
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(52,50,80,25);
        panel.add(userLabel);

        // ����������ı���
        JLabel passwordLabel=new JLabel("Password:");
        passwordLabel.setBounds(40,80,80,25);
        panel.add(passwordLabel);


        //�����ı��������û�����
        JTextField userText=new JTextField(20);
        userText.setBounds(100,50,165,25);
        panel.add(userText);

        //��������������������������
        JPasswordField passwordText=new JPasswordField(20);
        passwordText.setBounds(100,80,165,25);
        panel.add(passwordText);

        //����ע�ᰴť
        JButton registerButton=new JButton("register");
        registerButton.setBounds(60,130,80,25);
        panel.add(registerButton);

        // ������¼��ť
        JButton loginButton=new JButton("login");
        loginButton.setBounds(180,130,80,25);
        panel.add(loginButton);

        //ע���¼�
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String userNameText = userText.getText();
                String passwordText1 = new String(passwordText.getPassword());
                if(str.register(userNameText,passwordText1) == true){
                    JOptionPane.showMessageDialog(null,"ע��ɹ�,���¼��","ע��",JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"ע��ʧ��,�û����Ѵ���","ע��",JOptionPane.ERROR_MESSAGE);
                    userText.setText("");
                    passwordText.setText("");
                }
            }
        });

        //��¼�¼�
        loginButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String userNameText = userText.getText();
                String passwordText1 = new String(passwordText.getPassword());

                if(str.login(userNameText,passwordText1) == true){
                    JOptionPane.showMessageDialog(null,"��¼�ɹ�","��¼",JOptionPane.PLAIN_MESSAGE);
                    login.setVisible(false);
                    MenuView();
                }else{
                    JOptionPane.showMessageDialog(null,"��¼ʧ��,����������","��¼",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        login.add(panel);
        login.setVisible(true);
        }

    //��������
    public void MenuView(){
        //�����˵����
        JFrame jMenu=new JFrame("�˵�");
        jMenu.setSize(300,400);
        jMenu.setLocation(650,250);
        jMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu.setVisible(true);

        JPanel menu=new JPanel();
        menu.setLayout(null);
        //��������
        jMenu.getLayeredPane().add(menu,new Integer(Integer.MIN_VALUE));

        //�����˵���
        JMenuBar menuBar=new JMenuBar();
        JMenu m1=new JMenu("File");
        JMenu m2=new JMenu("Edit");
        JMenu m3=new JMenu("View");
        JMenuItem item1=new JMenuItem("New");
        JMenuItem item2=new JMenuItem("Open");
        JMenuItem item3=new JMenuItem("Save");
        JCheckBoxMenuItem checkBoxMenuItem1=new JCheckBoxMenuItem("choseBox1");
        JCheckBoxMenuItem checkBoxMenuItem2=new JCheckBoxMenuItem("choseBox2");
        JCheckBoxMenuItem checkBoxMenuItem3=new JCheckBoxMenuItem("choseBox3");
        JRadioButtonMenuItem radioButtonMenuItem01=new JRadioButtonMenuItem("chose1");
        JRadioButtonMenuItem radioButtonMenuItem02=new JRadioButtonMenuItem("chose2");

        m1.add(item1);
        m1.add(item2);
        m1.add(item3);
        m1.add(checkBoxMenuItem1);
        m1.add(checkBoxMenuItem2);
        m1.add(checkBoxMenuItem3);
        m1.add(radioButtonMenuItem01);
        m1.add(radioButtonMenuItem02);
        menuBar.add(m1);
        menuBar.add(m2);
        menuBar.add(m3);
        jMenu.setJMenuBar(menuBar);


        //�ֱ𴴽���ѯ����ȡ�ת�ˡ��˳���ť
        JLabel wec=new JLabel("��ӭ�� ");
        wec.setBounds(80,20,200,20);
        menu.add(wec);

        JButton inquity=new JButton("��ѯ���");
        inquity.setBounds(80,80,120,30);
        menu.add(inquity);

        JButton deposit=new JButton("���");
        deposit.setBounds(80,130,120,30);
        menu.add(deposit);


        JButton withdraw=new JButton("ȡ��");
        withdraw.setBounds(80,180,120,30);
        menu.add(withdraw);

        JButton transfer=new JButton("ת��");
        transfer.setBounds(80,230,120,30);
        menu.add(transfer);

        JButton exit=new JButton("�˳�");
        exit.setBounds(80,280,120,30);
        menu.add(exit);


        //��ѯ����¼�
        inquity.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu.setVisible(false);
                inquiryMenu();
            }
        });

        //����¼�
        deposit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu.setVisible(false);
                depositMenu();
            }
        });

        //ȡ���¼�
        withdraw.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                withdrawMenu();
            }
        });

        //ת���¼�
        transfer.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                transferMenu();
            }
        });

        //�˳��¼�
        exit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }
        });

        jMenu.add(menu);
        jMenu.setVisible(true);
        }


    /**
    * ��ѯ������
     **/
    public void inquiryMenu(){
        JFrame jMenu1=new JFrame();
        jMenu1.setSize(300,400);
        jMenu1.setLocation(650,250);
        jMenu1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu1.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //��������
        jMenu1.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel select=new JLabel("����ǰ�����Ϊ��");
        select.setBounds(50,80,120,30);
        jp.add(select);

        JLabel display=new JLabel(str.inquiry()+"Ԫ");
        display.setBounds(180,80,80,30);
        jp.add(display);

        JButton returnMenu=new JButton("���ز˵�");
        returnMenu.setBounds(80,280,120,30);
        jp.add(returnMenu);
        returnMenu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
        jMenu1.setVisible(false);
        MenuView();
        }
        });

        jMenu1.add(jp);
        jMenu1.setVisible(true);
        }

    /**
 * ������
 */
    public void depositMenu(){
        JFrame jMenu2=new JFrame();
        jMenu2.setSize(300,400);
        jMenu2.setLocation(650,250);
        jMenu2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu2.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //��������
        jMenu2.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel deposit=new JLabel("����������");
        JTextField depositText=new JTextField();
        deposit.setBounds(80,80,120,30);
        depositText.setBounds(80,120,120,30);
        jp.add(deposit);
        jp.add(depositText);

        JLabel display=new JLabel();
        display.setBounds(80,160,200,30);
        jp.add(display);

        JButton submit=new JButton("�ύ");
        submit.setBounds(40,280,100,30);
        jp.add(submit);
        submit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String money=depositText.getText();
                if(Double.parseDouble(money)<= 0){
                    JOptionPane.showMessageDialog(null,"���������0Ԫ","����",JOptionPane.PLAIN_MESSAGE);
                    deposit.setText("");
                }else{
                    display.setText("���ɹ�����ǰ���Ϊ��"+str.deposit(money)+"Ԫ");
                    deposit.setText("");
                }
            }
        });

        JButton returnMenu=new JButton("���ز˵�");
        returnMenu.setBounds(150,280,100,30);
        jp.add(returnMenu);
        returnMenu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu2.setVisible(false);
                MenuView();
            }
        });

        jMenu2.add(jp);
        jMenu2.setVisible(true);
        }

    /**
     * ȡ�����
      */
    public void withdrawMenu(){
        JFrame jMenu3=new JFrame();
        jMenu3.setSize(300,400);
        jMenu3.setLocation(650,250);
        jMenu3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu3.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //��������
        jMenu3.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel withdraw=new JLabel("������ȡ����");
        JTextField withdrawText=new JTextField();
        withdraw.setBounds(80,80,120,30);
        withdrawText.setBounds(80,120,120,30);
        jp.add(withdraw);
        jp.add(withdrawText);

        JLabel returnMoney=new JLabel();
        returnMoney.setBounds(80,160,200,30);
        jp.add(returnMoney);

        JButton submit=new JButton("�ύ");
        submit.setBounds(40,280,100,30);
        jp.add(submit);
        submit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String money=withdrawText.getText();
                if(Double.parseDouble(money)<0){
                    JOptionPane.showMessageDialog(null,"����С��0","����",JOptionPane.PLAIN_MESSAGE);
                    withdrawText.setText("");
                }else{
                    withdrawText.setText("");
                    returnMoney.setText(str.withdraw(money));
                }
            }
        });

        JButton returnMenu=new JButton("���ز˵�");
        returnMenu.setBounds(150,280,100,30);
        jp.add(returnMenu);
        returnMenu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu3.setVisible(false);
                MenuView();
            }
        });


        jMenu3.add(jp);
        jMenu3.setVisible(true);
        }

    //ת���¼�
    public void transferMenu(){
        JFrame jMenu4=new JFrame();
        jMenu4.setSize(300,400);
        jMenu4.setLocation(650,250);
        jMenu4.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu4.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //��������
        jMenu4.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel money=new JLabel("������ת�˽��");
        JTextField moneyText=new JTextField();
        money.setBounds(80,50,120,30);
        moneyText.setBounds(80,90,120,30);
        jp.add(money);
        jp.add(moneyText);

        JLabel toName=new JLabel("������ת���û�");
        JTextField toNameText=new JTextField();
        toName.setBounds(80,130,120,30);
        toNameText.setBounds(80,170,120,30);
        jp.add(toName);
        jp.add(toNameText);

        JLabel returnMoney=new JLabel();
        returnMoney.setBounds(80,210,200,30);
        jp.add(returnMoney);

        JButton submit=new JButton("�ύ");
        submit.setBounds(40,280,100,30);
        jp.add(submit);
        submit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String money=moneyText.getText();
                if(Double.parseDouble(money)<0){
                    JOptionPane.showMessageDialog(null,"����С��0","����",JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                }else{
                    //JOptionPane.showMessageDialog(null, "ת���û�������", "����",JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                    toNameText.setText("");
                    returnMoney.setText(str.transfer(toNameText.getText(),moneyText.getText()));
                }
            }
        });

        JButton returnMenu=new JButton("���ز˵�");
        returnMenu.setBounds(150,280,100,30);
        jp.add(returnMenu);
        returnMenu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu4.setVisible(false);
                MenuView();
            }
        });

        jMenu4.add(jp);
        jMenu4.setVisible(true);
    }
}
