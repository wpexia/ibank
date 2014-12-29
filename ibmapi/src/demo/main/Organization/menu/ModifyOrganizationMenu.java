package demo.main.organization.menu;


import gui.iBankMenu;

import javax.swing.*;
import java.util.HashMap;

public class ModifyOrganizationMenu extends iBankMenu
{
	private JLabel lbDeleteOrganization;
	private JLabel lbUpdateOrganization;

	private HashMap<String, String> mData;

	public ModifyOrganizationMenu(JFrame parent, HashMap<String, String> data)
	{
		super(parent);

		mData = data;

		setTitle("Organization Modify");

		lbDeleteOrganization = CreateLable(" 1. 删除机构");
		lbUpdateOrganization = CreateLable(" 2. 修改机构");

		AddMenuItem(lbDeleteOrganization);
		AddMenuItem(lbUpdateOrganization);
		AddMenuItem(lbExit);
	}

	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);

		if(menuItem.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请选择机构编辑业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (menuItem.equals("1"))
		{

		}
		else if (menuItem.equals("2"))
		{

		}
		else if (menuItem.equals("90")){
			returnMain();
		} else
		{
			JOptionPane.showMessageDialog(null, "无效的机构编辑业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
