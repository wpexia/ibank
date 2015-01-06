package demo.main.organization;


import demo.main.organization.menu.ShowOrganizationFrame;
import gui.iBankGui;
import ibankapi.Transaction;

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

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100070");

		data.put("ORGID",textOrgId.getText());


		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(data);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}
		ShowStatusMessage(Trans.GetStatusMsg());

		if(!Trans.GetStatus())
			return;

		String[] tmp={"ORGID","CODE","ADDRES","TYPE","AUTH","CONNEC"};
		for(String x : tmp)
		{
			data.put(x, Trans.GetResponseValue(x));
		}

		Trans.Release();

		ShowOrganizationFrame showOrganizationFrame = new ShowOrganizationFrame(this,data);
		OpenTransWindow(showOrganizationFrame);

	}
}
