package demo.main.account;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.iBankMenu;

public class AccountMenu extends iBankMenu{

	private static final long serialVersionUID = 1L;
	private JLabel         lbAddAccount;
	private JLabel         lbQueryAccount;
	boolean listenerFlag =  false;
	protected JFrame parentFrame;
	
	public AccountMenu(JFrame parent) {
		super(parent);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		setTitle("User Menu");

		lbAddAccount          = CreateLable(" 1. 添加账户");
		lbQueryAccount        = CreateLable(" 2. 查询账户");
		
		AddMenuItem(lbAddAccount);
		AddMenuItem(lbQueryAccount);
		
		AddMenuItem(lbExit);
	}
	
	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择账户业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(menuItem.equals("1")){
			AddAccountDemo addAccount = new AddAccountrDemo(this);
			OpenTransWindow(addAccount);
		}
		else if(menuItem.equals("2")){
			QueryAccountDemo queryAccount = new QueryAccountDemo(this);
			OpenTransWindow(queryAccount);
		}

		else if(menuItem.equals("90")){
            returnMain();
		}

	}
	
}
