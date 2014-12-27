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

public class UpdateUserDemo extends iBankGui{

	private static final long serialVersionUID = 9063166698820167875L;
	private JComboBox<String> comboIdType;
	private JTextField textIdNumber;
	private JComboBox<String> comboGender;
	private JTextField textAge;
	private JTextField textName1;
	private JTextField textName2;
	private JTextField textCustomerId;
	private JTextField textNewCustomerId;

	public UpdateUserDemo(JFrame parent) {
		super(parent);

		setTitle("iBank Update User Demo");

		JLabel lbIdType = CreateLable("证件类型");
		JLabel lbIdNumber = CreateLable("证件号码");
		JLabel lbGender = CreateLable("性别");
		JLabel lbAge = CreateLable("年龄");
		JLabel lbName1 = CreateLable("姓名");
		JLabel lbName2 = CreateLable("曾用名");

		String[] idTypes = {"身份证", "军官证", "护照"};
		comboIdType = new JComboBox<String>();
		for (int i = 0; i < idTypes.length; ++i) {
			comboIdType.addItem(idTypes[i]);
		}

		textIdNumber = new JTextField();
		textIdNumber.setColumns(15);

		String[] genders = {"男", "女"};
		comboGender = new JComboBox<String>();
		for (int i = 0; i < genders.length; ++i) {
			comboGender.addItem(genders[i]);
		}

		textAge = new JTextField();
		textAge.setColumns(15);

		textName1 = new JTextField();
		textName1.setColumns(15);

		textName2 = new JTextField();
		textName2.setColumns(15);

		lbTitle.setText("修改用户");
		btnOK.setText("修改");

		SetFont(textIdNumber);
		SetFont(textAge);
		SetFont(textName1);
		SetFont(textName2);

		AddInputComponent(lbIdType, 0, 1, 8, 1);
		AddInputComponent(comboIdType, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbIdNumber, 0, 2, 8, 1);
		AddInputComponent(textIdNumber, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbGender, 0, 3, 8, 1);
		AddInputComponent(comboGender, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAge, 0, 4, 8, 1);
		AddInputComponent(textAge, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbName1, 0, 5, 8, 1);
		AddInputComponent(textName1, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbName2, 0, 6, 8, 1);
		AddInputComponent(textName2, 8, 6, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 7, 8, 1);


		JLabel lbCustomerID = CreateLable("用户ID");
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);

		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		
		JLabel lbNewCustomerID = CreateLable("修改后用户ID");
		textNewCustomerId = new JTextField();
		textNewCustomerId.setColumns(15);

		AddOutputComponent(lbNewCustomerID, 0, 0, 8, 1);
		AddOutputComponent(textNewCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);

	}

	protected void TransactionAction() {
		super.TransactionAction();

		if (textIdNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入证件号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (textAge.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入年龄", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (textName1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入姓名", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100097");
		data.put("CUSTID", textCustomerId.getText());
		data.put("IDTYPE", "" + (char) ('A' + comboIdType.getSelectedIndex()));
		data.put("IDNO", textIdNumber.getText());
		data.put("GENDER", Integer.toString(comboGender.getSelectedIndex()));
		data.put("AGE", textAge.getText());
		data.put("NAME1", textName1.getText());
		data.put("NAME2", textName2.getText());

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
		textNewCustomerId.setText(customerID);

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}

}
