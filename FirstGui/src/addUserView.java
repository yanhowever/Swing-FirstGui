/*
 * Ϊ�û���¼����
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
	JFrame userframe = new JFrame("�����û�����");
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
	    // �����С
	    userframe.setSize(700,400);
	    // ��ʾ����
	    userframe.setVisible(true);
	    userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    userframe.setLocationRelativeTo(null); //ʹ���ھ���
	    userPanel = new JPanel();    
	    // ������
	    
	    userframe.add(userPanel);
	    userPanel.setLayout(null);
	    userLabel = new JLabel("�û���:");
	    userLabel.setBounds(110,120,80,25);
	    /* ������������������λ�á�
	     * setBounds(x, y, width, height)
	     * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
	     */
	    userPanel.add(userLabel);

	    /* 
	     * �����ı��������û�����
	     */
	    userText = new JTextField(20);
	    userText.setBounds(200,120,165,25);
	    userPanel.add(userText);

	    // ����������ı���
	    passwordLabel = new JLabel("����:");
	    passwordLabel.setBounds(110,150,80,25);
	    userPanel.add(passwordLabel);
	    /* 
	     *�����������������ı���
	     * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
	     */
	    passwordText = new JPasswordField(20);
	    passwordText.setBounds(200,150,165,25);
	    userPanel.add(passwordText);
	    
	    // ����ȷ��������ı���
	    passwordLabel1 = new JLabel("ȷ������:");
	    passwordLabel1.setBounds(110,180,80,25);
	    userPanel.add(passwordLabel1);
	    /* 
	     *�����������������ı���
	     * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
	     */
	    passwordText1 = new JPasswordField(20);
	    passwordText1.setBounds(200,180,165,25);
	    userPanel.add(passwordText1);

	    // ������¼��ť
	    addUserButton = new JButton("ȷ��");
	    addUserButton.setBounds(200, 230, 80, 25);
	    userPanel.add(addUserButton);
	    addUserButton.addActionListener(new ActionListener(){
	        //������ťִ�еķ���
	        public void actionPerformed(ActionEvent e) {
	        	String getPasswordString = new String(passwordText.getPassword()); //����ȡ��������char[]ת����string
	        	String getPasswordString1 = new String(passwordText1.getPassword()); //����ȡ��������char[]ת����string
	        	if(userText.getText().trim().length() == 0 || getPasswordString.trim().length() == 0 || getPasswordString1.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "�û����������벻��Ϊ��!");
					} 
	        	else if(getPasswordString.equals(getPasswordString1)!=true)
	        	{
	        		JOptionPane.showMessageDialog(null, "������������벻��ͬ!");
	        	}
	        	else if(validateUsername(userText.getText()) && getPasswordString.equals(getPasswordString1)==true)
	        	{
					if(addUser(userText.getText(),getPasswordString)) //�����û�
					{
						JOptionPane.showMessageDialog(null, "�ɹ������û�!");
					}
					
					else {
						JOptionPane.showMessageDialog(null, "����");
						// ����
						userText.setText("");
						passwordText.setText("");
					}
					
				}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(null, "�û����Ѵ��ڣ�");
	        	}

	        }
	    });
	}
	
	private  boolean validateUsername(String userName)  //������֤���ݿ��е��˺��Ƿ����
    {  
        String sql="select *from USERS where USERS.NAME ='"+userName+"' ";  
        try{  
        	Class.forName("org.sqlite.JDBC");      
        	String exepath1 = null;
        	String exepath = null;
			exepath1 = Class.class.getClass().getResource("/").getPath(); //���ַΪFirstGui\bin\administor.db
			exepath = exepath1 + "administor.db ";				
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + exepath); //��·��Ϊeclipse�İ�װĿ¼
            Statement pstmt = conn.createStatement(); 
            ResultSet rs=pstmt.executeQuery(sql);  
            
           //�����ѯ��ResultSet���г���һ���ļ�¼�����˺Ŵ��� 
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
	
	private  boolean addUser(String userName, String userPass)  //�����µ��û�
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
