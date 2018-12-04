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
	// 创建 JFrame 实例
	static JFrame frame = new JFrame("用户登录界面"); 
	static JPanel panel;
	static JButton manageButton, userButton;
	//static JButton userButton;
	public static void init() {    
        
        // Setting the width and height of frame
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //使窗口居中
        panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
        
    }

    private static int placeComponents(JPanel panel) {
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);
        // 创建登录按钮
        manageButton = new JButton("管理员");
        manageButton.setBounds(200, 100, 100, 50);
        panel.add(manageButton);
        userButton = new JButton("用户");
        userButton.setBounds(400, 100, 100, 50);
        panel.add(userButton);
        
      //添加点击事件监听器（你可以使用任何其他监听，看你想在什么情况下创建新的窗口了）   管理员登录窗口
        manageButton.addActionListener(new ActionListener(){
        //单击按钮执行的方法
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		//创建新的管理员窗口
//        		managerLogin a = new managerLogin();
//        		a.init();
        		new managerLogin();
        	}
        });
        
      //添加点击事件监听器（你可以使用任何其他监听，看你想在什么情况下创建新的窗口了）  用户登录窗口
        userButton.addActionListener(new ActionListener(){
        //单击按钮执行的方法
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		//创建新的管理员窗口
        		new userView();
        	}
        });
        return 0;
    } 
    
}
