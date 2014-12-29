package demo.main.user;

import gui.iBankGui;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.*;

import ibankapi.Transaction;

public final class AddUserFrame extends iBankGui {

	private static final long serialVersionUID = 9063166698820167875L;
	private JComboBox<String> comboIdType;
	private JTextField textIdNumber;
	private JComboBox<String> comboGender;
	private JTextField textName1;
	private JTextField textName2;
	private JTextField textCustomerId;
	private JTextField textBirth;
	private JTextField textAddress;
	private JTextField textConnect;

	public AddUserFrame(JFrame parent) {
		super(parent);

		setTitle("iBank Add User Demo");

		JLabel lbIdType = CreateLable("证件类型");
		JLabel lbIdNumber = CreateLable("证件号码");
		JLabel lbGender = CreateLable("性别");
		JLabel lbName1 = CreateLable("姓名");
		JLabel lbName2 = CreateLable("曾用名");
		JLabel lbBirth = CreateLable("出生年月");
		JLabel lbAddress = CreateLable("地址");
		JLabel lbConnec = CreateLable("联系方式");

		String[] idTypes = {"身份证", "军官证", "护照"};
		comboIdType = new JComboBox<String>();
		for (int i = 0; i < idTypes.length; ++i) {
			comboIdType.addItem(idTypes[i]);
		}
		comboIdType.addKeyListener(keyListener);

		textIdNumber = new JTextField();
		textIdNumber.setColumns(15);
		textIdNumber.addKeyListener(keyListener);

		String[] genders = {"男", "女"};
		comboGender = new JComboBox<String>();
		for (int i = 0; i < genders.length; ++i) {
			comboGender.addItem(genders[i]);
		}
		comboGender.addKeyListener(keyListener);


		textName1 = new JTextField();
		textName1.setColumns(15);
		textName1.addKeyListener(keyListener);

		textName2 = new JTextField();
		textName2.setColumns(15);
		textName2.addKeyListener(keyListener);


		textBirth = new JTextField();
		textBirth.setColumns(15);
		textBirth.addKeyListener(keyListener);

		textAddress = new JTextField();
		textAddress.setColumns(15);
		textAddress.addKeyListener(keyListener);

		textConnect = new JTextField();
		textConnect.setColumns(15);
		textConnect.addKeyListener(keyListener);

		lbTitle.setText("增加用户");
		btnOK.setText("增加");

		SetFont(textIdNumber);
		SetFont(textName1);
		SetFont(textName2);

		AddInputComponent(lbIdType, 0, 0, 8, 1);
		AddInputComponent(comboIdType, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbIdNumber, 0, 1, 8, 1);
		AddInputComponent(textIdNumber, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbGender, 0, 2, 8, 1);
		AddInputComponent(comboGender, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbName1, 0, 3, 8, 1);
		AddInputComponent(textName1, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbName2, 0, 4, 8, 1);
		AddInputComponent(textName2, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbBirth, 0, 5, 8, 1);
		AddInputComponent(textBirth, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAddress, 0, 6, 8, 1);
		AddInputComponent(textAddress, 8, 6, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbConnec, 0, 7, 8, 1);
		AddInputComponent(textConnect, 8, 7, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 8, 8, 1);


		JLabel lbCustomerID = CreateLable("用户ID");
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);

		AddOutputComponent(lbCustomerID, 0, 0, 8, 1);
		AddOutputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);

	}

	protected void TransactionAction() {
		super.TransactionAction();

		if (textIdNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入证件号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (textName1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入姓名", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100099");

		data.put("IDTYPE", "" + (char) ('A' + comboIdType.getSelectedIndex()));
		data.put("IDNO", textIdNumber.getText());
		data.put("GENDER", Integer.toString(comboGender.getSelectedIndex()));
		data.put("AGE", getAge(textBirth.getText()));
		data.put("NAME1", textName1.getText());
		data.put("NAME2", textName2.getText());
		data.put("BIRTH", textBirth.getText());
		data.put("ADDRES", textAddress.getText());
		data.put("CONNEC", textConnect.getText());

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

	public String getAge(String birth)
	{
		DateFormat format = new SimpleDateFormat("YYYYDD");
		try
		{
			Date birthDay = format.parse(birth);
			Calendar cal = Calendar.getInstance();

			if (cal.before(birthDay)) {
				throw new IllegalArgumentException(
						"The birthDay is before Now.It's unbelievable!");
			}

			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH)+1;
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

			cal.setTime(birthDay);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			int age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					//monthNow==monthBirth
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					//monthNow>monthBirth
					age--;
				}
			}

			return age +"";
		} catch (Exception e)
		{

		}
		return "";
	}
}
