package demo.main.user.edit;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.iBankGui;

public class DeleteUserFrame extends iBankGui
{

	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textCustomerId;

	public DeleteUserFrame(JFrame parent, HashMap<String, String> mData)
	{
		super(parent);

		setTitle("iBank Delete User Demo");
		JLabel lbCustomerId = CreateLable("用户ID");

		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.setText(mData.get("CUSTID"));

		lbTitle.setText("删除用户");
		btnOK.setText("删除");
		btnOK.addKeyListener(keyListener);

		SetFont(textCustomerId);

		AddInputComponent(lbCustomerId, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 2, 8, 1);
	}

	protected void TransactionAction()
	{
		super.TransactionAction();

		if (textCustomerId.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请输入用户ID", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100098");

		data.put("CUSTID", textCustomerId.getText());

		bRet = Trans.Init();

		if (!bRet)
		{
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(data);
		if (!bRet)
		{
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		String customerID = Trans.GetResponseValue("CUSTID");
		textCustomerId.setText(customerID);

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}


}
