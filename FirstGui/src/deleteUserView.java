
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class deleteUserView {
	JFrame userframe = new JFrame("ɾ���û�����");
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
	    userPanel.setLayout(null);  //�������Ҫ
	    userLabel = new JLabel("ɾ�����û���:");
	    userLabel.setBounds(220,120,100,25);
	    deleteUser.setBounds(310,120,100,25);
	    userPanel.add(userLabel);
	    userPanel.add(deleteUser);
	    
	   // userframe.add(deleteUser);
        userframe.setVisible(true);
        deleteUserButton = new JButton("ȷ��ɾ��");
        deleteUserButton.setBounds(260, 220, 100, 25);
	    userPanel.add(deleteUserButton);
	    deleteUserButton.addActionListener(new ActionListener(){
	        //������ťִ�еķ���
	        public void actionPerformed(ActionEvent e) {
	        	String deletename = (String) deleteUser.getSelectedItem();  //������е��˻���
	        	//JOptionPane.showConfirmDialog(null, "ȷ��ɾ���û�"+ deletename +"��", "�Ƿ�ɾ����", JOptionPane.YES_NO_OPTION);
	        	int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ���û�"+ deletename +"��", "�Ƿ�ɾ����", JOptionPane.YES_NO_OPTION);
	        	if(result == 0)
	        	{
	        		deleteUser(deletename);
	        	}
	        	deleteUser.setModel(new DefaultComboBoxModel(queryUser()));
	        	//deleteUser = new JComboBox(queryUser());
	        }    
	});
	}
	
	private static String[] queryUser()  //��ѯ���ݿ��е��û�
    {  
        String sql="SELECT NAME FROM USERS"; 
        String[] str =new String[10]; //�ٶ�Ϊ10��
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
	
	private boolean deleteUser(String userName)  //ɾ��ָ���û������û�
    {  
        //String sql="DELETE FROM USERS WHERE USERS.NAME = ' " + userName + "'" ; 
		String sql="DELETE FROM USERS WHERE USERS.NAME = '" + userName + "'" ; //'"֮�䲻���пո񰡣� fuck
        try{  
        	Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
            System.out.println("open database successful!");
            Statement pstmt = conn.createStatement(); 
            pstmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "�ɹ�ɾ��"+ userName +"!");
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
