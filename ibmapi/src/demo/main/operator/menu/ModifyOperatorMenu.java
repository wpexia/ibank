package demo.main.operator.menu;

import gui.iBankMenu;

import javax.swing.*;
import java.util.HashMap;

public class ModifyOperatorMenu extends iBankMenu
{

	private JLabel lbDeleteOperator;
	private JLabel lbUpdateOperator;

	private HashMap<String, String> mData;

	public ModifyOperatorMenu(JFrame parent, HashMap<String, String> data)
	{
		super(parent);

		mData = data;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle("Operator Modify");

		lbDeleteOperator = CreateLable(" 1. 删除操作员");
		lbUpdateOperator = CreateLable(" 2. 修改操作员");

		AddMenuItem(lbDeleteOperator);
		AddMenuItem(lbUpdateOperator);
		AddMenuItem(lbExit);
	}

	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);

		if(menuItem.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请选择操作员编辑业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
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
		}
	}
}
