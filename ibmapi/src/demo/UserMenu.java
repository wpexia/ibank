package demo;

import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import ibankapi.ibankapi;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.iBankMenu;

public class UserMenu extends iBankMenu{
	private static final long serialVersionUID = 1L;
	private JLabel         lbAddUser;
	private JLabel         lbQueryUser;
	private JLabel         lbDeleteUser;
	private JLabel         lbUpdateUser;
	
	protected JFrame parentFrame;

    protected KeyboardFocusManager manager;
    protected KeyEventPostProcessor postProcessor;
	
	public UserMenu(JFrame parent){
		super();
		parentFrame = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(postProcessor = new KeyEventPostProcessor()
                {
                    @Override
                    public boolean postProcessKeyEvent(KeyEvent e)
                    {
                        if (KeyEvent.VK_ESCAPE == e.getKeyCode())
                        {
                            returnMain();
                        }
                        return true;
                    }
                }
        );
		
		setTitle("User Menu");
		
		lbAddUser          = CreateLable(" 1. 添加用户");
		lbQueryUser        = CreateLable(" 2. 查询用户");
		lbDeleteUser       = CreateLable(" 3. 删除用户");
		lbUpdateUser       = CreateLable(" 4. 修改用户");
		
		AddMenuItem(lbAddUser);
		AddMenuItem(lbQueryUser);
		AddMenuItem(lbDeleteUser);
		AddMenuItem(lbUpdateUser);
		
		AddMenuItem(lbExit);
	}
	
	protected void OpenTransFrame(String menuItem){
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择用户业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(menuItem.equals("1")){
			AddUserDemo addUser = new AddUserDemo(this);
			OpenTransWindow(addUser);
		}
		else if(menuItem.equals("2")){
			QueryDemo queryUser = new QueryDemo(this);
			OpenTransWindow(queryUser);
		}
		else if(menuItem.equals("3")){
			DeleteUserDemo deleteUser = new DeleteUserDemo(this);
			OpenTransWindow(deleteUser);
		}
		else if(menuItem.equals("4")){
			UpdateUserDemo updateUser = new UpdateUserDemo(this);
			OpenTransWindow(updateUser);
		}
		else if(menuItem.equals("90")){
			dispose();
		}
		
	}
	
	protected void returnMain()
    {
        manager.removeKeyEventPostProcessor(postProcessor);
        dispose();
        setVisible(false);
        parentFrame.setVisible(true);
    }
}
























