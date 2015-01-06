package demo.main.account;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import demo.main.account.list.detail.ShowAccountDetailMenu;
import gui.iBankGui;

public class QueryAccountFrame extends iBankGui{

	private static final long serialVersionUID = 9063166698820167875L;

	private JTextField textCustomerId;
	
	public QueryAccountFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Query Account Demo");
		
		lbTitle.setText("查询账户");
		btnOK.setText("查询");
		
		JLabel lbCustomerID = CreateLable("账户号");
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.addKeyListener(keyListener);
		SetFont(textCustomerId);
		
		
		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);
		
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textCustomerId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入账户号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100091");

		data.put("ACCTNO",textCustomerId.getText());

		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(data);
		if(!bRet){
			return;
		}

		ShowStatusMessage(Trans.GetStatusMsg());

		if(!Trans.GetStatus())
			return;


		String[] tmp = {"CUSTID", "ACDATE", "ORGID", "STATE", "PASSWD"};
		for(String x : tmp)
		{
			data.put(x, Trans.GetResponseValue(x));
		}
		Trans.Release();

		ShowAccountDetailMenu showAccount = new ShowAccountDetailMenu(this, data);
		OpenTransWindow(showAccount);

	}

}
