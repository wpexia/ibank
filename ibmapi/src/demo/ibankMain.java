package demo;

import gui.iBankMenu;

import javax.swing.*;

import ibankapi.ibankapi;

public class ibankMain extends iBankMenu
{
	
	private static final long serialVersionUID = 1L;
	private JLabel         lbAccountTransfer;  
	private JLabel         lbDataSync;
	private JLabel         lbMQ;
	private JLabel         lbUser;


	public ibankMain()
	{
		super();
		setTitle("iBank Demo");
	
		lbDataSync          = CreateLable(" 1. 数据同步");
		lbAccountTransfer   = CreateLable(" 2. 转账");
		lbMQ                = CreateLable(" 3. IBANK多记录查询示例");
		lbUser              = CreateLable(" 4. dy用户管理");

		AddMenuItem(lbDataSync);
		AddMenuItem(lbAccountTransfer);
		AddMenuItem(lbMQ);
		AddMenuItem(lbUser);

		
		AddMenuItem(lbExit);
		
	}
	
	protected void OpenTransFrame(String menuItem)
	{
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
		else if (menuItem.equals("2"))
		{
			AccountTransfer accountTrans = new AccountTransfer(this);
			OpenTransWindow(accountTrans);
		}
		else if (menuItem.equals("3"))
		{
			MQDemo mqdemo = new MQDemo(this, "900000", 10);
			OpenTransWindow(mqdemo);
		}
		else if(menuItem.equals("4"))
		{
			UserMenu userMenu = new UserMenu(this);
			userMenu.Display();
			userMenu.pack();
			this.Hidden();
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
