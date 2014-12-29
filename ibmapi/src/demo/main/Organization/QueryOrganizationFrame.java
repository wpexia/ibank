package demo.main.organization;


import gui.iBankGui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class QueryOrganizationFrame extends iBankGui
{
	private JTextField textOrgId;

	public QueryOrganizationFrame(JFrame parent)
	{
		super(parent);

		JLabel lbOrgId = CreateLable("机构编号");

		textOrgId = new JTextField();
		textOrgId.setColumns(15);
		textOrgId.addKeyListener(keyListener);

		lbTitle.setText("查询机构");
		btnOK.setText("查询");
		btnOK.addKeyListener(keyListener);

		SetFont(textOrgId);

		AddInputComponent(lbOrgId, 0, 0, 8, 1);
		AddInputComponent(textOrgId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 2, 8, 1);
	}
	protected void TransactionAction()
	{
		super.TransactionAction();

		if (textOrgId.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入机构ID","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}

		HashMap<String, String> data = new HashMap<String, String>();




	}
}
