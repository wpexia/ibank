package demo;

import gui.iBankGui;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import ibankapi.Transaction;

public final class QueryDemo extends iBankGui {

	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textIdType;
	private JTextField textIdNumber;
	private JTextField textGender;
	private JTextField textAge;
	private JTextField textName1;
	private JTextField textName2;
	private JTextField textCustomerId;

	public QueryDemo(JFrame parent) {
		super(parent);

		setTitle("iBank Query User Demo");

		JLabel lbIdType = CreateLable("证件类型");
		JLabel lbIdNumber = CreateLable("证件号码");
		JLabel lbGender = CreateLable("性别");
		JLabel lbAge = CreateLable("年龄");
		JLabel lbName1 = CreateLable("姓名");
		JLabel lbName2 = CreateLable("曾用名");

		textIdType = new JTextField();
		textIdType.setColumns(20);
		

		textIdNumber = new JTextField();
		textIdNumber.setColumns(15);

//		String[] genders = {"男", "女"};
//		comboGender = new JComboBox<String>();
//		for (int i = 0; i < genders.length; ++i) {
//			comboGender.addItem(genders[i]);
//		}
		textGender = new JTextField();
		textGender.setColumns(5);

		textAge = new JTextField();
		textAge.setColumns(15);

		textName1 = new JTextField();
		textName1.setColumns(15);

		textName2 = new JTextField();
		textName2.setColumns(15);

		lbTitle.setText("查询用户");
		btnOK.setText("查询");

		SetFont(textIdType);
		SetFont(textIdNumber);
		SetFont(textAge);
		SetFont(textGender);
		SetFont(textName1);
		SetFont(textName2);
		
		JLabel lbCustomerID = CreateLable("用户ID");
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);

		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 3, 8, 1);

		//AddOutputComponent(lbCustomerID, 0, 0, 8, 1);
		//AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbIdType, 0, 1, 8, 1);
		AddOutputComponent(textIdType, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbIdNumber, 0, 2, 8, 1);
		AddOutputComponent(textIdNumber, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbGender, 0, 3, 8, 1);
		AddOutputComponent(textGender, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbAge, 0, 4, 8, 1);
		AddOutputComponent(textAge, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbName1, 0, 5, 8, 1);
		AddOutputComponent(textName1, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbName2, 0, 6, 8, 1);
		AddOutputComponent(textName2, 8, 6, GridBagConstraints.RELATIVE, 1);
		
		
	}

	protected void TransactionAction() {
		super.TransactionAction();
		if(textCustomerId.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "请输入用户ID", "错误", JOptionPane.ERROR_MESSAGE);
		}

//		if (textIdNumber.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "请输入证件号", "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//
//
//		if (textAge.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "请输入年龄", "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//
//
//		if (textName1.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "请输入姓名", "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100096");
//
//		data.put("IDTYPE", "" + (char) ('A' + comboIdType.getSelectedIndex()));
//		data.put("IDNO", textIdNumber.getText());
//		data.put("GENDER", Integer.toString(comboGender.getSelectedIndex()));
//		data.put("AGE", textAge.getText());
//		data.put("NAME1", textName1.getText());
//		data.put("NAME2", textName2.getText());
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

		String customerID = Trans.GetResponseValue("CUSTID");
		textCustomerId.setText(customerID);
		
		String IDType = Trans.GetResponseValue("IDTYPE");
		textIdType.setText(IDType);
		
		String IDNumber = Trans.GetResponseValue("IDNO");
		textIdNumber.setText(IDNumber);
		
		String gender = Trans.GetResponseValue("GENDER");
		textGender.setText(gender);
		
		String age = Trans.GetResponseValue("AGE");
		textAge.setText(age);
		
		String name1 = Trans.GetResponseValue("NAME1");
		textName1.setText(name1);
		
		String name2 = Trans.GetResponseValue("NAME2");
		textName2.setText(name2);
		
//		String customerID = Trans.GetResponseValue("CUSTID");
//		textCustomerId.setText(customerID);

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}
}
