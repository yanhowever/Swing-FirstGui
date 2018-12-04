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
//import src.managerView;

public class selectWindow {
	// ���� JFrame ʵ��
	static JFrame frame = new JFrame("�û���¼����"); 
	static JPanel panel;
	static JButton manageButton, userButton;
	//static JButton userButton;
	public static void init() {    
        
        // Setting the width and height of frame
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //ʹ���ھ���
        panel = new JPanel();    
        // ������
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
        
    }

    private static int placeComponents(JPanel panel) {
        /* ���ֲ���������߲���������
         * ������ò���Ϊ null
         */
        panel.setLayout(null);
        // ������¼��ť
        manageButton = new JButton("����Ա");
        manageButton.setBounds(200, 100, 100, 50);
        panel.add(manageButton);
        userButton = new JButton("�û�");
        userButton.setBounds(400, 100, 100, 50);
        panel.add(userButton);
        
      //��ӵ���¼��������������ʹ���κ�������������������ʲô����´����µĴ����ˣ�   ����Ա��¼����
        manageButton.addActionListener(new ActionListener(){
        //������ťִ�еķ���
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		//�����µĹ���Ա����
//        		managerLogin a = new managerLogin();
//        		a.init();
        		new managerLogin();
        	}
        });
        
      //��ӵ���¼��������������ʹ���κ�������������������ʲô����´����µĴ����ˣ�  �û���¼����
        userButton.addActionListener(new ActionListener(){
        //������ťִ�еķ���
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		//�����µĹ���Ա����
        		new userView();
        	}
        });
        return 0;
    } 
    
}
