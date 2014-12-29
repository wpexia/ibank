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
	private JLabel lbDataSync;
	private JLabel lbAccount;
	private JLabel lbUser;
	private JLabel lbOperator;


	public ibankMainMenu()
	{
		super(null);
		setTitle("iBank Demo");

		lbDataSync = CreateLable(" 1. 数据同步");
		lbUser = CreateLable(" 2. 用户操作");
		lbAccount = CreateLable(" 3. 账户操作");
		lbOperator = CreateLable(" 5. 操作员操作");


		AddMenuItem(lbDataSync);
		AddMenuItem(lbUser);
		AddMenuItem(lbAccount);
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
		} else if (menuItem.equals("1"))
		{
			DataSyncFrame dataSync = new DataSyncFrame(this);
			OpenTransWindow(dataSync);
		} else if (menuItem.equals("2"))
		{
			UserMenu userMenu = new UserMenu(this);
			OpenMenuWindow(userMenu);
		} else if (menuItem.equals("3"))
		{
			AccountMenu accountMenu = new AccountMenu(this);
			OpenMenuWindow(accountMenu);
		} else if (menuItem.equals("4"))
		{

		} else if (menuItem.equals("5"))
		{
			OperatorMenu operatorMenu = new OperatorMenu(this);
			OpenMenuWindow(operatorMenu);
		} else if (menuItem.equals("90"))
		{
			ibankapi.Release();
			System.exit(0);
		} else
		{
			JOptionPane.showMessageDialog(null, "无效的业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

}
