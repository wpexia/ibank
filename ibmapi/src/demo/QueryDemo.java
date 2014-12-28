package demo;

import gui.iBankGui;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import ibankapi.Transaction;

public final class QueryDemo extends iBankGui {

	private static final long serialVersionUID = 9063166698820167875L;

	private JTextField textCustomerId;

	public QueryDemo(JFrame parent) {
		super(parent);

		setTitle("iBank Query User Demo");


		lbTitle.setText("查询用户");
		btnOK.setText("查询");

		
		JLabel lbCustomerID = CreateLable("用户ID");
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.addKeyListener(keyListener);

		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 3, 8, 1);

	}

	protected void TransactionAction() {
		super.TransactionAction();
		if(textCustomerId.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "请输入用户ID", "错误", JOptionPane.ERROR_MESSAGE);
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100096");

		data.put("CUSTID", textCustomerId.getText());

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

		data.put("IDTYPE", Trans.GetResponseValue("IDTYPE"));
		data.put("IDNO", Trans.GetResponseValue("IDNO"));
		data.put("GENDER", Trans.GetResponseValue("GENDER"));
		data.put("AGE", Trans.GetResponseValue("AGE"));
		data.put("NAME1", Trans.GetResponseValue("NAME1"));
		data.put("NAME2", Trans.GetResponseValue("NAME2"));


		ShowUserDemo showUser = new ShowUserDemo(this, data);
		OpenTransWindow(showUser);



		Trans.Release();
	}
}
