/*
 * 为用户登录界面
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class addUserView {
	JFrame userframe = new JFrame("增加用户窗口");
	JPanel userPanel;
	JLabel userLabel, passwordLabel, passwordLabel1;
	JTextField userText;
	JPasswordField passwordText, passwordText1;
	JButton addUserButton;
	public addUserView()
	{
		init();
	}
	private void init()
	{
	    // 窗体大小
	    userframe.setSize(700,400);
	    // 显示窗体
	    userframe.setVisible(true);
	    userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    userframe.setLocationRelativeTo(null); //使窗口居中
	    userPanel = new JPanel();    
	    // 添加面板
	    
	    userframe.add(userPanel);
	    userPanel.setLayout(null);
	    userLabel = new JLabel("用户名:");
	    userLabel.setBounds(110,120,80,25);
	    /* 这个方法定义了组件的位置。
	     * setBounds(x, y, width, height)
	     * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
	     */
	    userPanel.add(userLabel);

	    /* 
	     * 创建文本域用于用户输入
	     */
	    userText = new JTextField(20);
	    userText.setBounds(200,120,165,25);
	    userPanel.add(userText);

	    // 输入密码的文本域
	    passwordLabel = new JLabel("密码:");
	    passwordLabel.setBounds(110,150,80,25);
	    userPanel.add(passwordLabel);
	    /* 
	     *这个类似用于输入的文本域
	     * 但是输入的信息会以点号代替，用于包含密码的安全性
	     */
	    passwordText = new JPasswordField(20);
	    passwordText.setBounds(200,150,165,25);
	    userPanel.add(passwordText);
	    
	    // 输入确认密码的文本域
	    passwordLabel1 = new JLabel("确认密码:");
	    passwordLabel1.setBounds(110,180,80,25);
	    userPanel.add(passwordLabel1);
	    /* 
	     *这个类似用于输入的文本域
	     * 但是输入的信息会以点号代替，用于包含密码的安全性
	     */
	    passwordText1 = new JPasswordField(20);
	    passwordText1.setBounds(200,180,165,25);
	    userPanel.add(passwordText1);

	    // 创建登录按钮
	    addUserButton = new JButton("确定");
	    addUserButton.setBounds(200, 230, 80, 25);
	    userPanel.add(addUserButton);
	    addUserButton.addActionListener(new ActionListener(){
	        //单击按钮执行的方法
	        public void actionPerformed(ActionEvent e) {
	        	String getPasswordString = new String(passwordText.getPassword()); //将获取的密码由char[]转换成string
	        	String getPasswordString1 = new String(passwordText1.getPassword()); //将获取的密码由char[]转换成string
	        	if(userText.getText().trim().length() == 0 || getPasswordString.trim().length() == 0 || getPasswordString1.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "用户名或者密码不能为空!");
					} 
	        	else if(getPasswordString.equals(getPasswordString1)!=true)
	        	{
	        		JOptionPane.showMessageDialog(null, "两次输入的密码不相同!");
	        	}
	        	else if(validateUsername(userText.getText()) && getPasswordString.equals(getPasswordString1)==true)
	        	{
					if(addUser(userText.getText(),getPasswordString)) //新增用户
					{
						JOptionPane.showMessageDialog(null, "成功增加用户!");
					}
					
					else {
						JOptionPane.showMessageDialog(null, "错误");
						// 清零
						userText.setText("");
						passwordText.setText("");
					}
					
				}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(null, "用户名已存在！");
	        	}

	        }
	    });
	}
	
	private  boolean validateUsername(String userName)  //用于验证数据库中的账号是否存在
    {  
        String sql="select *from USERS where USERS.NAME ='"+userName+"' ";  
        try{  
        	Class.forName("org.sqlite.JDBC");      
        	String exepath1 = null;
        	String exepath = null;
			exepath1 = Class.class.getClass().getResource("/").getPath(); //其地址为FirstGui\bin\administor.db
			exepath = exepath1 + "administor.db ";				
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + exepath); //其路径为eclipse的安装目录
            Statement pstmt = conn.createStatement(); 
            ResultSet rs=pstmt.executeQuery(sql);  
            
           //如果查询的ResultSet里有超过一条的记录，则账号存在 
           if (rs.next())  { 
        	   pstmt.close();
               conn.close();
               return false; 
           }               
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
        return true;  
    }
	
	private  boolean addUser(String userName, String userPass)  //创建新的用户
    {  
		
        String sql="INSERT INTO USERS (NAME,PASSWORD) " +
	              "VALUES ('" + userName + "',  '" + userPass + "')" ; 
        try{  
        	Class.forName("org.sqlite.JDBC");      
            Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
            Statement pstmt = conn.createStatement(); 
            pstmt.executeUpdate(sql);
            pstmt.close();          
            conn.close();
            return true;                             
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
        return false;  
    }
	
}
