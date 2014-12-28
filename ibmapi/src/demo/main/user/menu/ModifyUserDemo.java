package demo.main.user.menu;

import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import demo.main.user.edit.DeleteUserDemo;
import demo.main.user.edit.UpdateUserDemo;
import gui.iBankMenu;

public class ModifyUserDemo extends iBankMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel         lbDeleteUser;
	private JLabel         lbUpdateUser;

    protected JFrame parentFrame;
	private HashMap<String, String> mData ;

    
    public ModifyUserDemo(JFrame parent, HashMap<String, String>data){
    	super(parent);
    	parentFrame = parent;
    	mData = data;
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	setTitle("User Menu");
    	
    	lbDeleteUser       = CreateLable(" 1. 删除用户");
		lbUpdateUser       = CreateLable(" 2. 修改用户");
		
		AddMenuItem(lbDeleteUser);
		AddMenuItem(lbUpdateUser);
		AddMenuItem(lbExit);
    }
    
    protected void OpenTransFrame(String menuItem){
    	super.OpenTransFrame(menuItem);
//        manager.removeKeyEventPostProcessor(postProcessor);
//        listenerFlag = false;
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择用户编辑业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(menuItem.equals("1")){
			DeleteUserDemo deleteUser = new DeleteUserDemo(this, mData);
			OpenTransWindow(deleteUser);
		}
		else if(menuItem.equals("2")){
			UpdateUserDemo updateUser = new UpdateUserDemo(this, mData);
			OpenTransWindow(updateUser);
		}
		else if(menuItem.equals("90")){
            returnMain();
		}
		
	}

}

















