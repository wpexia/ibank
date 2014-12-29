package demo.main.operator.menu.edit;

import gui.iBankGui;

import javax.swing.*;
import java.util.HashMap;

public class DeleteOperatorFrame extends iBankGui
{
	private HashMap<String, String> mData;

	public DeleteOperatorFrame(JFrame parent, HashMap<String, String> data)
	{
		super(parent);

		mData = data;
		setTitle("Delete Operator");

		JLabel  lbConfirm = CreateLable("确定删除？");

		lbTitle.setText("删除操作员");
		btnOK.setText("编辑");
		btnOK.addKeyListener(keyListener);

		AddInputComponent(lbConfirm, 0, 0, 8, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);
	}


}
