package demo;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.iBankGui;

public class DeleteUserDemo extends iBankGui{

	private static final long serialVersionUID = 9063166698820167875L;
	private JComboBox<String> comboIdType;
	private JTextField textIdNumber;
	private JTextField textCustomerId;
	public DeleteUserDemo(JFrame parent) {
		super(parent);

		setTitle("iBank Delete User Demo");
		JLabel lbIdType = CreateLable("证件类型");
		JLabel lbIdNumber = CreateLable("证件号码");
		
		String[] idTypes = {"身份证", "军官证", "护照"};
		comboIdType = new JComboBox<String>();
		for (int i = 0; i < idTypes.length; ++i) {
			comboIdType.addItem(idTypes[i]);
		}
		
		textIdNumber = new JTextField();
		textIdNumber.setColumns(15);
		
		lbTitle.setText("删除用户");
		btnOK.setText("删除");
		SetFont(textIdNumber);
		
		AddInputComponent(lbIdType, 0, 0, 8, 1);
		AddInputComponent(comboIdType, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbIdNumber, 0, 1, 8, 1);
		AddInputComponent(textIdNumber, 8, 1, GridBagConstraints.RELATIVE, 1);
		
		AddInputComponent(btnOK, 0, 2, 8, 1);
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textIdNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入证件号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100098");

		data.put("IDTYPE", "" + (char) ('A' + comboIdType.getSelectedIndex()));
		data.put("IDNO", textIdNumber.getText());

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

		String customerID = Trans.GetResponseValue("CUSTID");
		textCustomerId.setText(customerID);

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}

	

}
