package demo.main;

import gui.iBankMenu;

import javax.swing.*;

import demo.main.operator.OperatorMenu;
import demo.main.account.AccountMenu;
import demo.main.dataSync.DataSyncFrame;
import demo.main.user.AddUserFrame;
import demo.main.user.QueryUserFrame;
import demo.main.user.UserMenu;
import ibankapi.ibankapi;

public class ibankMainMenu extends iBankMenu
{
	
	private static final long serialVersionUID = 12L;
	private JLabel         lbAccountTransfer;  
	private JLabel         lbDataSync;
	private JLabel         lbMQ;
	private JLabel         lbAU;
	private JLabel         lbQuery;
	private JLabel         lbUser;
	private JLabel         lbOperator;
	
	
	public ibankMainMenu()
	{
		super(null);
		setTitle("iBank Demo");
	
		lbDataSync          = CreateLable(" 1. 数据同步");
		//lbAccountTransfer   = CreateLable(" 2. 转账");
		lbUser              = CreateLable(" 2. 用户操作");
		lbMQ                = CreateLable(" 3. 账户操作");
		lbAU                = CreateLable(" 4. 添加用户");
		lbQuery             = CreateLable(" 7. 查询用户");
		lbOperator          = CreateLable(" 5. 操作员操作");
		

		AddMenuItem(lbDataSync);
		//AddMenuItem(lbAccountTransfer);
		AddMenuItem(lbUser);
		AddMenuItem(lbMQ);
		AddMenuItem(lbAU);
		AddMenuItem(lbQuery);
		AddMenuItem(lbOperator);
		
		
		AddMenuItem(lbExit);
		
	}
	protected void returnMain()
	{
		return;
	}

	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);
		if (menuItem.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请选择业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (menuItem.equals("1"))
		{
			DataSyncFrame dataSync = new DataSyncFrame(this);
			OpenTransWindow(dataSync);
		}
//		else if (menuItem.equals("2"))
//		{
//			AccountTransfer accountTrans = new AccountTransfer(this);
//			OpenTransWindow(accountTrans);
//		}
		else if(menuItem.equals("2")){
			UserMenu userMenu = new UserMenu(this);
			OpenMenuWindow(userMenu);
		}
		else if(menuItem.equals("3")){
			AccountMenu accountMenu = new AccountMenu(this);
			OpenMenuWindow(accountMenu);
		}
//		else if (menuItem.equals("3"))
//		{
//			MQDemo mqdemo = new MQDemo(this, "900000", 10);
//			OpenTransWindow(mqdemo);
//		}
		else if ( menuItem.equals("4")){
			AddUserFrame addUserFrame = new AddUserFrame(this);
			OpenTransWindow(addUserFrame);
		}
		else if( menuItem.equals("5")){
			OperatorMenu operatorMenu = new OperatorMenu(this);
			OpenMenuWindow(operatorMenu);
		}
		else if ( menuItem.equals("7")){
			QueryUserFrame queryUserFrame = new QueryUserFrame(this);
			OpenTransWindow(queryUserFrame);
		}

		else if (menuItem.equals("90"))
		{
			ibankapi.Release();
			System.exit(0);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "无效的业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
		
}
