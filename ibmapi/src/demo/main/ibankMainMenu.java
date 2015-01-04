package demo.main;

import demo.main.organization.OrganizationMenu;
import gui.iBankMenu;

import javax.swing.*;

import demo.main.operator.OperatorMenu;
import demo.main.account.AccountMenu;
import demo.main.dataSync.DataSyncFrame;
import demo.main.deposit.DepositFrame;
import demo.main.draw.DrawFrame;
import demo.main.transfer.TransferFrame;
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

	private JLabel lbDeposit;
	private JLabel lbDraw;
	private JLabel lbTransfer;

	private JLabel lbOrganization;

	public ibankMainMenu()
	{
		super(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("iBank Demo");

		lbDataSync = CreateLable(" 1. 数据同步");
		lbUser = CreateLable(" 2. 用户操作");
		lbAccount = CreateLable(" 3. 账户操作");
		lbOrganization = CreateLable(" 4. 机构操作");
		lbOperator = CreateLable(" 5. 操作员操作");
		lbDeposit = CreateLable(" 6. 存款");
		lbDraw = CreateLable(" 7. 取款");
		lbTransfer = CreateLable(" 8. 转账");
			


		AddMenuItem(lbDataSync);
		AddMenuItem(lbUser);
		AddMenuItem(lbAccount);
		AddMenuItem(lbOrganization);
		AddMenuItem(lbOperator);
		AddMenuItem(lbDeposit);
		AddMenuItem(lbDraw);
		AddMenuItem(lbTransfer);	


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
			OrganizationMenu organizationMenu = new OrganizationMenu(this);
			OpenMenuWindow(organizationMenu);
		} else if (menuItem.equals("5"))
		{
			OperatorMenu operatorMenu = new OperatorMenu(this);
			OpenMenuWindow(operatorMenu);
		}
		else if(menuItem.equals("6")){
			DepositFrame deposit = new DepositFrame(this);
			OpenTransWindow(deposit);
		}
		else if(menuItem.equals("7")){
			DrawFrame draw = new DrawFrame(this);
			OpenTransWindow(draw);
		}
		else if(menuItem.equals("8")){
			TransferFrame transfer = new TransferFrame(this);
			OpenTransWindow(transfer);
		}
		else if (menuItem.equals("90"))
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
