/*
 * 为管理员操作界面
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
	JFrame manageframe = new JFrame("管理员窗口");
	JButton addUserButton, changeUserButton, resetUserButton, deleteUserButton;
	JPanel managePanel;
	public managerView()
	{
		init();
	}
	private void init()
	{
		manageframe.setSize(700,400);
		// 显示窗体
		manageframe.setVisible(true);
	    manageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    manageframe.setLocationRelativeTo(null); //使窗口居中
	    managePanel = new JPanel();    
		    // 添加面板
	    manageframe.add(managePanel);
	    managePanel.setLayout(null);
		    // 创建登录按钮
	    addUserButton = new JButton("新增用户");
	    addUserButton.setBounds(150, 75, 100, 50);
	    managePanel.add(addUserButton);
	    changeUserButton = new JButton("修改用户信息");
	    changeUserButton.setBounds(450, 75, 100, 50);
	    managePanel.add(changeUserButton);
	    resetUserButton = new JButton("重置用户口令");
	    resetUserButton.setBounds(150, 225, 100, 50);
	    managePanel.add(resetUserButton);
	    deleteUserButton = new JButton("删除用户");
	    deleteUserButton.setBounds(450, 225, 100, 50);
	    managePanel.add(deleteUserButton);
	    
	    //添加 新增用户按钮 点击事件监听器
	    addUserButton.addActionListener(new ActionListener(){
        //单击按钮执行的方法
        	public void actionPerformed(ActionEvent e) {
        		manageframe.dispose();
        		//创建新的管理员窗口
        		new addUserView();
        	}
        });
	    
	    //添加 删除用户按钮 点击事件监听器
	    deleteUserButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e) {
	        		manageframe.dispose();
	        		//创建新的管理员窗口
	        		new deleteUserView();
	        	}
	        });
	    
	    //添加重置用户口令按钮 点击事件监听器
	    resetUserButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e) {
	        		manageframe.dispose();
	        		//创建新的管理员窗口
	        		new resetUserView();
	        	}
	        });
	    
	    
	 }
    // 窗体大小
    
}
