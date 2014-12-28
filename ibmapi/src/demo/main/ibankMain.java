package demo.main;

import gui.iBankMenu;

import javax.swing.*;

import demo.OperatorMenu;
import demo.UserMenu;
import demo.main.account.AccountMenu;
import demo.main.dataSync.DataSyncDemo;
import demo.main.user.AddUserDemo;
import demo.main.user.QueryDemo;
import ibankapi.ibankapi;

public class ibankMain extends iBankMenu
{
	
	private static final long serialVersionUID = 1L;
	private JLabel         lbAccountTransfer;  
	private JLabel         lbDataSync;
	private JLabel         lbMQ;
	private JLabel         lbAU;
	private JLabel         lbQuery;
	private JLabel         lbUser;
	private JLabel         lbOperator;
	
	
	public ibankMain()
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
			DataSyncDemo dataSync = new DataSyncDemo(this);
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
			AddUserDemo addUserDemo = new AddUserDemo(this);
			OpenTransWindow(addUserDemo);
		}
		else if( menuItem.equals("5")){
			OperatorMenu operatorMenu = new OperatorMenu(this);
			OpenMenuWindow(operatorMenu);
		}
		else if ( menuItem.equals("7")){
			QueryDemo queryDemo = new QueryDemo(this);
			OpenTransWindow(queryDemo);
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
