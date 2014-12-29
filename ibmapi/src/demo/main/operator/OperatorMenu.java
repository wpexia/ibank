package demo.main.operator;


import gui.iBankMenu;

import javax.swing.*;

public class OperatorMenu extends iBankMenu
{
	private static final long serialVersionUID = 111L;

	private JLabel lbAddOperator;
	private JLabel lbSearchOperator;


	public OperatorMenu(JFrame parent)
	{
		super(parent);
		parentFrame = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle("Operator Menu");

		lbAddOperator = CreateLable(" 1. 增加操作员");
		lbSearchOperator = CreateLable(" 2. 查找操作员");

		AddMenuItem(lbAddOperator);
		AddMenuItem(lbSearchOperator);

		AddMenuItem(lbExit);


	}

	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择操作员业务菜单功能", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (menuItem.equals("1"))
		{
			AddOperatorFrame addOperatorFrame = new AddOperatorFrame(this);
			OpenTransWindow(addOperatorFrame);
		}
		else if (menuItem.equals("2"))
		{
			QueryOperatorFrame queryOperatorFrame = new QueryOperatorFrame(this);
			OpenTransWindow(queryOperatorFrame);
		}
		else if (menuItem.equals("90"))
		{
			returnMain();
		}
	}

}
