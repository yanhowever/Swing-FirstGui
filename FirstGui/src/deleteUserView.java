
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class deleteUserView {
	JFrame userframe = new JFrame("删除用户界面");
	JPanel userPanel;
	JLabel userLabel;
	JTextField userText;
	JButton deleteUserButton;
	
	
	JComboBox deleteUser = new JComboBox(queryUser());
	
	public deleteUserView()
	{
		init();
	}
	
	private void init()
	{
		userframe.setSize(700,400);
	   
	    userframe.setVisible(true);
	    userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    userframe.setLocationRelativeTo(null); 
	    userPanel = new JPanel();  
	    //userframe.setContentPane(userPanel);
	    userframe.add(userPanel);
	    userPanel.setLayout(null);  //这个很重要
	    userLabel = new JLabel("删除的用户名:");
	    userLabel.setBounds(220,120,100,25);
	    deleteUser.setBounds(310,120,100,25);
	    userPanel.add(userLabel);
	    userPanel.add(deleteUser);
	    
	   // userframe.add(deleteUser);
        userframe.setVisible(true);
        deleteUserButton = new JButton("确定删除");
        deleteUserButton.setBounds(260, 220, 100, 25);
	    userPanel.add(deleteUserButton);
	    deleteUserButton.addActionListener(new ActionListener(){
	        //单击按钮执行的方法
	        public void actionPerformed(ActionEvent e) {
	        	String deletename = (String) deleteUser.getSelectedItem();  //获得已有的账户名
	        	//JOptionPane.showConfirmDialog(null, "确定删除用户"+ deletename +"？", "是否删除？", JOptionPane.YES_NO_OPTION);
	        	int result = JOptionPane.showConfirmDialog(null, "确定删除用户"+ deletename +"？", "是否删除？", JOptionPane.YES_NO_OPTION);
	        	if(result == 0)
	        	{
	        		deleteUser(deletename);
	        	}
	        	deleteUser.setModel(new DefaultComboBoxModel(queryUser()));
	        	//deleteUser = new JComboBox(queryUser());
	        }    
	});
	}
	
	private static String[] queryUser()  //查询数据库中的用户
    {  
        String sql="SELECT NAME FROM USERS"; 
        String[] str =new String[10]; //假定为10个
        int i = 0;
        try{  
        	Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
            Statement pstmt = conn.createStatement(); 
            ResultSet rs = pstmt.executeQuery(sql);
            while(rs.next()){
            	str[i] = rs.getString(1);
            	i++;
            }
            pstmt.close();          
            conn.close();                        
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }
		return str;         
    }
	
	private boolean deleteUser(String userName)  //删除指定用户名的用户
    {  
        //String sql="DELETE FROM USERS WHERE USERS.NAME = ' " + userName + "'" ; 
		String sql="DELETE FROM USERS WHERE USERS.NAME = '" + userName + "'" ; //'"之间不能有空格啊！ fuck
        try{  
        	Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
            System.out.println("open database successful!");
            Statement pstmt = conn.createStatement(); 
            pstmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "成功删除"+ userName +"!");
            //deleteUser = JComboBox(queryUser());
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
