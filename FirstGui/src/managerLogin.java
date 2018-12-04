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

public class managerLogin {
	JFrame managerLoginFrame = new JFrame("����Ա��¼����");
	JPanel managerLoginPanel;
	JLabel managerLabel, passwordLabel;
	JTextField userText;
	JPasswordField passwordText;
	JButton loginButton;
	public managerLogin()
	{
		init();
	}
	private void init()
	{
	    // �����С
		managerLoginFrame.setSize(700,400);
	    // ��ʾ����
		managerLoginFrame.setVisible(true);
		managerLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		managerLoginFrame.setLocationRelativeTo(null); //ʹ���ھ���
		managerLoginPanel = new JPanel();    
	    // ������
	    
	    managerLoginFrame.add(managerLoginPanel);
	    managerLoginPanel.setLayout(null);
	    managerLabel = new JLabel("�û���:");
	    /* ������������������λ�á�
	     * setBounds(x, y, width, height)
	     * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
	     */
	    managerLabel.setBounds(110,120,80,25);
	    managerLoginPanel.add(managerLabel);

	    /* 
	     * �����ı��������û�����
	     */
	    userText = new JTextField(20);
	    userText.setBounds(200,120,165,25);
	    managerLoginPanel.add(userText);

	    // ����������ı���
	    passwordLabel = new JLabel("����:");
	    passwordLabel.setBounds(110,150,80,25);
	    managerLoginPanel.add(passwordLabel);

	    /* 
	     *�����������������ı���
	     * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
	     */
	    passwordText = new JPasswordField(20);
	    passwordText.setBounds(200,150,165,25);
	    managerLoginPanel.add(passwordText);

	    // ������¼��ť
	    loginButton = new JButton("login");
	    loginButton.setBounds(200, 200, 80, 25);
	    managerLoginPanel.add(loginButton);
	    loginButton.addActionListener(new ActionListener(){
	        //������ťִ�еķ���
	        public void actionPerformed(ActionEvent e) {
	        	String getPasswordString = new String(passwordText.getPassword()); //����ȡ��������char[]ת����string
	        	if (userText.getText().trim().length() == 0 || new String(passwordText.getPassword()).trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�û����������벻��Ϊ��!");
	        	}
				else if(validate(userText.getText(),getPasswordString) == true)
				{
						JOptionPane.showMessageDialog(null, "��½�ɹ���");
						managerLoginFrame.dispose();
						new managerView();
				}
				else{
					JOptionPane.showMessageDialog(null, "����");
					// ����
					userText.setText("");
					passwordText.setText("");
				}
	        	}
	    });
	}
	private boolean validate(String userName, String userPass)  //������֤���ݿ��е��˺�����
    {  
        String sql="select *from MANAGERS where MANAGERS.NAME ='"+userName+"' and MANAGERS.PASSWORD='"+userPass+"'";  
        try{  
        	Class.forName("org.sqlite.JDBC");         
            Connection conn = DriverManager.getConnection("jdbc:sqlite:administor.db");
        	//System.out.println("Opened database successfully11");	
            Statement pstmt = conn.createStatement(); 
            ResultSet rs=pstmt.executeQuery(sql);  
            //pstmt.close(); //rs ʹ�ý�������ܹر�
            
           //�����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�  
           if (rs.next())  {  
        	   System.out.println("��ѯ�ɹ�");	
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
