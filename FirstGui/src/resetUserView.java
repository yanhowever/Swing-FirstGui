
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class resetUserView {
	JFrame userframe = new JFrame("�����û��������");
	JPanel userPanel;
	JLabel userLabel;
	JTextField userText;
	JButton resetUserButton;
	
	
	JComboBox deleteUser = new JComboBox(queryUser());
	
	public resetUserView()
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
	    userframe.add(userPanel);
	    userPanel.setLayout(null);  //�������Ҫ
	    userLabel = new JLabel("���õ��û���:");
	    userLabel.setBounds(220,120,100,25);
	    deleteUser.setBounds(310,120,100,25);
	    userPanel.add(userLabel);
	    userPanel.add(deleteUser);
	    
	   // userframe.add(deleteUser);
        userframe.setVisible(true);
        resetUserButton = new JButton("ȷ������");
        resetUserButton.setBounds(260, 220, 100, 25);
	    userPanel.add(resetUserButton);
	    resetUserButton.addActionListener(new ActionListener(){
	        //������ťִ�еķ���
	        public void actionPerformed(ActionEvent e) {
	        	String deletename = (String) deleteUser.getSelectedItem();  //������е��˻���
	        	//JOptionPane.showConfirmDialog(null, "ȷ��ɾ���û�"+ deletename +"��", "�Ƿ�ɾ����", JOptionPane.YES_NO_OPTION);
	        	int result = JOptionPane.showConfirmDialog(null, "ȷ�������û�:"+ deletename +" �Ŀ��", "�Ƿ����ã�", JOptionPane.YES_NO_OPTION);
	        	if(result == 0)
	        	{
	        		resetUser(deletename);
	        	}
	        	deleteUser.setModel(new DefaultComboBoxModel(queryUser()));
	        	//deleteUser = new JComboBox(queryUser());
	        }    
	});
	}
	
	private  String[] queryUser()  //��ѯ���ݿ��е��û�
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
	
	private boolean resetUser(String userName)  //����ָ���û��Ŀ��� �����������Ϊ123456
    {   
		String sql="UPDATE USERS SET PASSWORD = '123456' WHERE NAME = '" + userName + "'" ; //
        try{  
        	Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
            System.out.println("open database successful!");
            Statement pstmt = conn.createStatement(); 
            pstmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "�ɹ������û�����"+ userName +"!");
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
