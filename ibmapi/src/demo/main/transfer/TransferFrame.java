package demo.main.transfer;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import gui.iBankGui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import demo.main.account.list.detail.edit.QuerySubAccountMenu;

public class TransferFrame extends iBankGui {

	private static final long serialVersionUID = 1L;
	private JTextField textFromAccountNo ;
	private JTextField textToAccountNo;
	private JTextField textAmount;
	
	private JTextField textBalance;
	
	public TransferFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Transfer Demo");
		JLabel lbFromAccountNo = CreateLable("转出账户号");
		JLabel lbToAccountNo = CreateLable("转入账户号");
		JLabel lbAmount = CreateLable("转账金额");
		
		JLabel lbBalance = CreateLable("余额");
		
		textAmount = new JTextField();
		textAmount.setColumns(15);
		textAmount.addKeyListener(keyListener);
		
		textBalance = new JTextField();
		textBalance.setColumns(15);
		textBalance.addKeyListener(keyListener);
		textBalance.setEditable(false);
		
		textFromAccountNo = new JTextField();
		textFromAccountNo.setColumns(15);
		textFromAccountNo.addKeyListener(keyListener);
		
		textToAccountNo = new JTextField();
		textToAccountNo.setColumns(15);
		textToAccountNo.addKeyListener(keyListener);
		
		SetFont(textBalance);
		SetFont(textFromAccountNo);
		SetFont(textToAccountNo);
		SetFont(textAmount);
		
		lbTitle.setText("转账");
		lbTitle.addKeyListener(keyListener);
		btnOK.setText("转账");
		btnOK.addKeyListener(keyListener);
		
		AddInputComponent(lbFromAccountNo, 0, 0, 8, 1);
		AddInputComponent(textFromAccountNo, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbToAccountNo, 0, 1, 8, 1);
		AddInputComponent(textToAccountNo, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAmount, 0, 2, 8, 1);
		AddInputComponent(textAmount, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 3, 8, 1);
		
		AddOutputComponent(lbBalance, 0, 0, 8, 1);
		AddOutputComponent(textBalance, 8, 0, GridBagConstraints.RELATIVE, 1);
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textFromAccountNo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入转出账户号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (textToAccountNo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入转入账户号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (textAmount.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入金额", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		
		if(true){//余额不足
			JOptionPane.showMessageDialog(null, "转出账户活期余额不足，需从定期账户转", "错误", JOptionPane.ERROR_MESSAGE);
			//根据账户号找子账户列表，存到一个map里
			QuerySubAccountMenu querySubAccount = new QuerySubAccountMenu(this, data);
			OpenTransWindow(querySubAccount);
		}
//		Transaction Trans = new Transaction("100099");
//
//		data.put("IDTYPE", "" + (char) ('A' + comboIdType.getSelectedIndex()));
//		data.put("IDNO", textIdNumber.getText());
//		data.put("GENDER", Integer.toString(comboGender.getSelectedIndex()));
//		data.put("AGE", getAge(textBirth.getText()));
//		data.put("NAME1", textName1.getText());
//		data.put("NAME2", textName2.getText());
//		data.put("BIRTH", textBirth.getText());
//		data.put("ADDRES", textAddress.getText());
//		data.put("CONNEC", textConnect.getText());
//
//		bRet = Trans.Init();
//
//		if (!bRet) {
//			ShowStatusMessage(Trans.GetStatusMsg());
//			return;
//		}
//
//		bRet = Trans.SendMessage(data);
//		if (!bRet) {
//			ShowStatusMessage(Trans.GetStatusMsg());
//			return;
//		}
//
//		String customerID = Trans.GetResponseValue("CUSTID");
//		textCustomerId.setText(customerID);
//
//		ShowStatusMessage(Trans.GetStatusMsg());
//
//		Trans.Release();
	}
}
