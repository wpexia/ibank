package demo.main.user.menu;

import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import demo.main.account.detail.ShowAccountsMenu;
import demo.main.user.edit.DeleteUserFrame;
import demo.main.user.edit.UpdateUserFrame;
import gui.iBankMenu;

public class ModifyUserMenu extends iBankMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel         lbDeleteUser;
	private JLabel         lbUpdateUser;
	private JLabel         lbCustomerID;
	private JLabel         lbAccount;

	private HashMap<String, String> mData ;

    
    public ModifyUserMenu(JFrame parent, HashMap<String, String>data){
    	super(parent);

    	mData = data;
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	setTitle("User Menu");
    	
    	lbCustomerID       = CreateLable("用户号:  " + mData.get("CUSTID"));
    	lbDeleteUser       = CreateLable(" 1. 删除用户");
		lbUpdateUser       = CreateLable(" 2. 修改用户");
		lbAccount          = CreateLable(" 3. 查看用户账户");
		AddMenuItem(lbCustomerID);
		AddMenuItem(lbDeleteUser);
		AddMenuItem(lbUpdateUser);
		AddMenuItem(lbAccount);
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
			DeleteUserFrame deleteUser = new DeleteUserFrame(this, mData);
			OpenTransWindow(deleteUser);
		}
		else if(menuItem.equals("2")){
			UpdateUserFrame updateUser = new UpdateUserFrame(this, mData);
			OpenTransWindow(updateUser);
		}
		else if(menuItem.equals("3")){
/**************这边要写通过用户ID查找所有ACCOUNT的过程*****************/			
			ShowAccountsMenu showAccounts = new ShowAccountsMenu(this, mData);
			OpenMenuWindow(showAccounts);
		}
		else if(menuItem.equals("90")){
            returnMain();
		} else
		{
			JOptionPane.showMessageDialog(null, "无效的用户编辑业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}

}

















