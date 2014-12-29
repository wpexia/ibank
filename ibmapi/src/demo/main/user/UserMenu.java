package demo.main.user;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.iBankMenu;

public class UserMenu extends iBankMenu
{
	private static final long serialVersionUID = 1L;
	private JLabel lbAddUser;
	private JLabel lbQueryUser;

	public UserMenu(JFrame parent)
	{
		super(parent);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		setTitle("User Menu");

		lbAddUser = CreateLable(" 1. 添加用户");
		lbQueryUser = CreateLable(" 2. 查询用户");

		AddMenuItem(lbAddUser);
		AddMenuItem(lbQueryUser);

		AddMenuItem(lbExit);

	}

	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);
		if (menuItem.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请选择用户业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (menuItem.equals("1"))
		{
			AddUserFrame addUser = new AddUserFrame(this);
			OpenTransWindow(addUser);
		} else if (menuItem.equals("2"))
		{
			QueryUserFrame queryUser = new QueryUserFrame(this);
			OpenTransWindow(queryUser);
		} else if (menuItem.equals("90"))
		{
			returnMain();
		} else
		{
			JOptionPane.showMessageDialog(null, "无效的用户业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}
}