/*
 * 为用户登录界面
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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



public class userView {
	JFrame userframe = new JFrame("用户登录窗口");
	JPanel userPanel;
	JLabel userLabel, passwordLabel;
	JTextField userText;
	JPasswordField passwordText;
	JButton loginButton;
	public userView()
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
	    /* 这个方法定义了组件的位置。
	     * setBounds(x, y, width, height)
	     * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
	     */
	    userLabel.setBounds(110,120,80,25);
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

	    // 创建登录按钮
	    loginButton = new JButton("login");
	    loginButton.setBounds(200, 200, 80, 25);
	    userPanel.add(loginButton);
	    loginButton.addActionListener(new ActionListener(){
	        //单击按钮执行的方法
	        public void actionPerformed(ActionEvent e) {
	        	String getPasswordString = new String(passwordText.getPassword()); //将获取的密码由char[]转换成string
	        	if (userText.getText().trim().length() == 0 || new String(passwordText.getPassword()).trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "用户名或者密码不能为空!");
	        	}
				else if(validate(userText.getText(),getPasswordString) == true)
				{
						JOptionPane.showMessageDialog(null, "登陆成功！");
						userframe.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "错误");
					// 清零
					userText.setText("");
					passwordText.setText("");
				}
	        	}
	    });
	}
	private boolean validate(String userName, String userPass)  //用于验证数据库中的账号密码
    {  
        String sql="select *from USERS where USERS.NAME ='"+userName+"' and USERS.PASSWORD='"+userPass+"'";  
        try{  
Class.forName("org.sqlite.JDBC");      
        	
        	String exepath1 = null;
        	String exepath = null;
        	//System.out.println("yyh" + Class.class.getClass().getResource("/").getPath());
        	
			
				
			exepath1 = Class.class.getClass().getResource("/").getPath(); //其地址为FirstGui\bin\administor.db
			exepath = exepath1 + "administor.db ";				
			
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + exepath); //其路径为eclipse的安装目录
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
        	//System.out.println("Opened database successfully11");	
            Statement pstmt = conn.createStatement(); 
            ResultSet rs=pstmt.executeQuery(sql);  
            //pstmt.close(); //rs 使用结束后才能关闭
            
           //如果查询的ResultSet里有超过一条的记录，则登录成功  
           if (rs.next())  {  
        	   System.out.println("查询成功");	
        	   pstmt.close();
        	   conn.close(); 
               return true; 
           }        
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
        return false;  
    }  

}
