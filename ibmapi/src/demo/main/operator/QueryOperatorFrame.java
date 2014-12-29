package demo.main.operator;


import demo.main.operator.menu.ShowOperatorFrame;
import gui.iBankGui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class QueryOperatorFrame extends iBankGui
{
	private static final long serialVersionUID = 503L;
	private JTextField textOperatorId;


	public QueryOperatorFrame(JFrame parent)
	{
		super(parent);

		setTitle("iBank Query Operator Demo");

		JLabel lbOperatorId = CreateLable("操作员编号");


		textOperatorId = new JTextField();
		textOperatorId.setColumns(15);
		textOperatorId.addKeyListener(keyListener);

		lbTitle.setText("查询操作员");
		btnOK.setText("查询");
		btnOK.addKeyListener(keyListener);


		SetFont(textOperatorId);

		AddInputComponent(lbOperatorId, 0, 0, 8, 1);
		AddInputComponent(textOperatorId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);


	}

	protected void TransactionAction()
	{
		super.TransactionAction();

		if (textOperatorId.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入操作员ID","错误",JOptionPane.ERROR_MESSAGE);
		}

		HashMap<String, String> data = new HashMap<String, String>();


		ShowOperatorFrame showOperatorFrame = new ShowOperatorFrame(this,data);
		OpenTransWindow(showOperatorFrame);


	}
}
