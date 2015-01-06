package demo.main.organization;

import gui.iBankMenu;

import javax.swing.*;

public class OrganizationMenu extends iBankMenu
{
	private JLabel lbAddOrg;
	private JLabel lbQueryOrg;

	public OrganizationMenu(JFrame parent)
	{
		super(parent);

		setTitle("Organization Menu");

		lbAddOrg = CreateLable(" 1. 添加机构");
		lbQueryOrg = CreateLable(" 2. 查询机构");

		AddMenuItem(lbAddOrg);
		AddMenuItem(lbQueryOrg);

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
			AddOrganizationFrame addOrganizationFrame = new AddOrganizationFrame(this);
			OpenTransWindow(addOrganizationFrame);
		} else if (menuItem.equals("2"))
		{
			QueryOrganizationFrame queryOrganizationFrame = new QueryOrganizationFrame(this);
			OpenTransWindow(queryOrganizationFrame);
		} else if (menuItem.equals("90"))
		{
			returnMain();
		} else
		{
			JOptionPane.showMessageDialog(null, "请选择用户业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
