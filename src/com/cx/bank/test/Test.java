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
        //用户1 用户名：new  用户密码：123
        //用户2 用户名：Huar 用户密码：123
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
    * 登录界面
    */

    public void LoginView(){
        //创建登录界面框架
        JFrame login=new JFrame("login");
        login.setSize(350,200);   //350,250
        login.setLocation(550,300);
        Image icon=Toolkit.getDefaultToolkit().getImage("src/com/cx/bank/test/big.jpg");
        login.setIconImage(icon);

        //创建登录界面画布
        JPanel panel=new JPanel();
        panel.setLayout(null);

        //创建标题文本域
        JLabel loginLable = new JLabel("Bank System 1.4");
        loginLable.setBounds(125,10,150,25);
        panel.add(loginLable);

        // 创建用户名的文本域
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(52,50,80,25);
        panel.add(userLabel);

        // 输入密码的文本域
        JLabel passwordLabel=new JLabel("Password:");
        passwordLabel.setBounds(40,80,80,25);
        panel.add(passwordLabel);


        //创建文本域用于用户输入
        JTextField userText=new JTextField(20);
        userText.setBounds(100,50,165,25);
        panel.add(userText);

        //创建密码输入域用于密码输入
        JPasswordField passwordText=new JPasswordField(20);
        passwordText.setBounds(100,80,165,25);
        panel.add(passwordText);

        //创建注册按钮
        JButton registerButton=new JButton("register");
        registerButton.setBounds(60,130,80,25);
        panel.add(registerButton);

        // 创建登录按钮
        JButton loginButton=new JButton("login");
        loginButton.setBounds(180,130,80,25);
        panel.add(loginButton);

        //注册事件
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String userNameText = userText.getText();
                String passwordText1 = new String(passwordText.getPassword());
                if(str.register(userNameText,passwordText1) == true){
                    JOptionPane.showMessageDialog(null,"注册成功,请登录！","注册",JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"注册失败,用户名已存在","注册",JOptionPane.ERROR_MESSAGE);
                    userText.setText("");
                    passwordText.setText("");
                }
            }
        });

        //登录事件
        loginButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String userNameText = userText.getText();
                String passwordText1 = new String(passwordText.getPassword());

                if(str.login(userNameText,passwordText1) == true){
                    JOptionPane.showMessageDialog(null,"登录成功","登录",JOptionPane.PLAIN_MESSAGE);
                    login.setVisible(false);
                    MenuView();
                }else{
                    JOptionPane.showMessageDialog(null,"登录失败,请重新输入","登录",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        login.add(panel);
        login.setVisible(true);
        }

    //操作界面
    public void MenuView(){
        //创建菜单框架
        JFrame jMenu=new JFrame("菜单");
        jMenu.setSize(300,400);
        jMenu.setLocation(650,250);
        jMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu.setVisible(true);

        JPanel menu=new JPanel();
        menu.setLayout(null);
        //消除浮动
        jMenu.getLayeredPane().add(menu,new Integer(Integer.MIN_VALUE));

        //创建菜单条
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


        //分别创建查询、存款、取款、转账、退出按钮
        JLabel wec=new JLabel("欢迎您 ");
        wec.setBounds(80,20,200,20);
        menu.add(wec);

        JButton inquity=new JButton("查询余额");
        inquity.setBounds(80,80,120,30);
        menu.add(inquity);

        JButton deposit=new JButton("存款");
        deposit.setBounds(80,130,120,30);
        menu.add(deposit);


        JButton withdraw=new JButton("取款");
        withdraw.setBounds(80,180,120,30);
        menu.add(withdraw);

        JButton transfer=new JButton("转账");
        transfer.setBounds(80,230,120,30);
        menu.add(transfer);

        JButton exit=new JButton("退出");
        exit.setBounds(80,280,120,30);
        menu.add(exit);


        //查询余额事件
        inquity.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu.setVisible(false);
                inquiryMenu();
            }
        });

        //存款事件
        deposit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                jMenu.setVisible(false);
                depositMenu();
            }
        });

        //取款事件
        withdraw.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                withdrawMenu();
            }
        });

        //转账事件
        transfer.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                transferMenu();
            }
        });

        //退出事件
        exit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }
        });

        jMenu.add(menu);
        jMenu.setVisible(true);
        }


    /**
    * 查询余额界面
     **/
    public void inquiryMenu(){
        JFrame jMenu1=new JFrame();
        jMenu1.setSize(300,400);
        jMenu1.setLocation(650,250);
        jMenu1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu1.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //消除浮动
        jMenu1.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel select=new JLabel("您当前的余额为：");
        select.setBounds(50,80,120,30);
        jp.add(select);

        JLabel display=new JLabel(str.inquiry()+"元");
        display.setBounds(180,80,80,30);
        jp.add(display);

        JButton returnMenu=new JButton("返回菜单");
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
 * 存款界面
 */
    public void depositMenu(){
        JFrame jMenu2=new JFrame();
        jMenu2.setSize(300,400);
        jMenu2.setLocation(650,250);
        jMenu2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu2.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //消除浮动
        jMenu2.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel deposit=new JLabel("请输入存款金额");
        JTextField depositText=new JTextField();
        deposit.setBounds(80,80,120,30);
        depositText.setBounds(80,120,120,30);
        jp.add(deposit);
        jp.add(depositText);

        JLabel display=new JLabel();
        display.setBounds(80,160,200,30);
        jp.add(display);

        JButton submit=new JButton("提交");
        submit.setBounds(40,280,100,30);
        jp.add(submit);
        submit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String money=depositText.getText();
                if(Double.parseDouble(money)<= 0){
                    JOptionPane.showMessageDialog(null,"金额必须大于0元","警告",JOptionPane.PLAIN_MESSAGE);
                    deposit.setText("");
                }else{
                    display.setText("存款成功，当前余额为："+str.deposit(money)+"元");
                    deposit.setText("");
                }
            }
        });

        JButton returnMenu=new JButton("返回菜单");
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
     * 取款界面
      */
    public void withdrawMenu(){
        JFrame jMenu3=new JFrame();
        jMenu3.setSize(300,400);
        jMenu3.setLocation(650,250);
        jMenu3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu3.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //消除浮动
        jMenu3.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel withdraw=new JLabel("请输入取款金额");
        JTextField withdrawText=new JTextField();
        withdraw.setBounds(80,80,120,30);
        withdrawText.setBounds(80,120,120,30);
        jp.add(withdraw);
        jp.add(withdrawText);

        JLabel returnMoney=new JLabel();
        returnMoney.setBounds(80,160,200,30);
        jp.add(returnMoney);

        JButton submit=new JButton("提交");
        submit.setBounds(40,280,100,30);
        jp.add(submit);
        submit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String money=withdrawText.getText();
                if(Double.parseDouble(money)<0){
                    JOptionPane.showMessageDialog(null,"金额不能小于0","警告",JOptionPane.PLAIN_MESSAGE);
                    withdrawText.setText("");
                }else{
                    withdrawText.setText("");
                    returnMoney.setText(str.withdraw(money));
                }
            }
        });

        JButton returnMenu=new JButton("返回菜单");
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

    //转账事件
    public void transferMenu(){
        JFrame jMenu4=new JFrame();
        jMenu4.setSize(300,400);
        jMenu4.setLocation(650,250);
        jMenu4.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jMenu4.setVisible(true);

        JPanel jp=new JPanel();
        jp.setLayout(null);
        //消除浮动
        jMenu4.getLayeredPane().add(jp,new Integer(Integer.MIN_VALUE));

        JLabel money=new JLabel("请输入转账金额");
        JTextField moneyText=new JTextField();
        money.setBounds(80,50,120,30);
        moneyText.setBounds(80,90,120,30);
        jp.add(money);
        jp.add(moneyText);

        JLabel toName=new JLabel("请输入转账用户");
        JTextField toNameText=new JTextField();
        toName.setBounds(80,130,120,30);
        toNameText.setBounds(80,170,120,30);
        jp.add(toName);
        jp.add(toNameText);

        JLabel returnMoney=new JLabel();
        returnMoney.setBounds(80,210,200,30);
        jp.add(returnMoney);

        JButton submit=new JButton("提交");
        submit.setBounds(40,280,100,30);
        jp.add(submit);
        submit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String money=moneyText.getText();
                if(Double.parseDouble(money)<0){
                    JOptionPane.showMessageDialog(null,"金额不能小于0","警告",JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                }else{
                    //JOptionPane.showMessageDialog(null, "转账用户不存在", "警告",JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                    toNameText.setText("");
                    returnMoney.setText(str.transfer(toNameText.getText(),moneyText.getText()));
                }
            }
        });

        JButton returnMenu=new JButton("返回菜单");
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
