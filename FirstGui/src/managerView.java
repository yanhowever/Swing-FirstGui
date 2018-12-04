/*
 * Ϊ����Ա��������
 */
import java.sql.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
 

public class managerView extends JFrame{
	JFrame manageframe = new JFrame("����Ա����");
	JButton addUserButton, changeUserButton, resetUserButton, deleteUserButton;
	JPanel managePanel;
	public managerView()
	{
		init();
	}
	private void init()
	{
		manageframe.setSize(700,400);
		// ��ʾ����
		manageframe.setVisible(true);
	    manageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    manageframe.setLocationRelativeTo(null); //ʹ���ھ���
	    managePanel = new JPanel();    
		    // ������
	    manageframe.add(managePanel);
	    managePanel.setLayout(null);
		    // ������¼��ť
	    addUserButton = new JButton("�����û�");
	    addUserButton.setBounds(150, 75, 100, 50);
	    managePanel.add(addUserButton);
	    changeUserButton = new JButton("�޸��û���Ϣ");
	    changeUserButton.setBounds(450, 75, 100, 50);
	    managePanel.add(changeUserButton);
	    resetUserButton = new JButton("�����û�����");
	    resetUserButton.setBounds(150, 225, 100, 50);
	    managePanel.add(resetUserButton);
	    deleteUserButton = new JButton("ɾ���û�");
	    deleteUserButton.setBounds(450, 225, 100, 50);
	    managePanel.add(deleteUserButton);
	    
	    //��� �����û���ť ����¼�������
	    addUserButton.addActionListener(new ActionListener(){
        //������ťִ�еķ���
        	public void actionPerformed(ActionEvent e) {
        		manageframe.dispose();
        		//�����µĹ���Ա����
        		new addUserView();
        	}
        });
	    
	    //��� ɾ���û���ť ����¼�������
	    deleteUserButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e) {
	        		manageframe.dispose();
	        		//�����µĹ���Ա����
	        		new deleteUserView();
	        	}
	        });
	    
	    //��������û����ť ����¼�������
	    resetUserButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e) {
	        		manageframe.dispose();
	        		//�����µĹ���Ա����
	        		new resetUserView();
	        	}
	        });
	    
	    
	 }
    // �����С
    
}
