package demo;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import demo.main.user.menu.ModifyUserDemo;
import gui.iBankGui;

public class ShowUserDemo extends iBankGui{
	
	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textIdType;
	private JTextField textIdNumber;
	private JTextField textGender;
	private JTextField textAge;
	private JTextField textName1;
	private JTextField textName2;
	private JTextField textCustomerId;
	private HashMap<String, String>mData;

	public ShowUserDemo(JFrame parent, HashMap<String, String>data) {
		super(parent);
		mData = data;

		setTitle("iBank Show User Demo");
		
		
		JLabel lbCustomerID = CreateLable("用户ID");
		JLabel lbIdType = CreateLable("证件类型");
		JLabel lbIdNumber = CreateLable("证件号码");
		JLabel lbGender = CreateLable("性别");
		JLabel lbAge = CreateLable("年龄");
		JLabel lbName1 = CreateLable("姓名");
		JLabel lbName2 = CreateLable("曾用名");

		textIdType = new JTextField();
		textIdType.setColumns(20);
		textIdType.setEditable(false);
		

		textIdNumber = new JTextField();
		textIdNumber.setColumns(20);
		textIdNumber.setEditable(false);
		
		textGender = new JTextField();
		textGender.setColumns(15);
		textGender.setEditable(false);

		textAge = new JTextField();
		textAge.setColumns(15);
		textAge.setEditable(false);

		textName1 = new JTextField();
		textName1.setColumns(15);
		textName1.setEditable(false);

		textName2 = new JTextField();
		textName2.setColumns(15);
		textName2.setEditable(false);

		lbTitle.setText("编辑用户");
		btnOK.setText("操作");

		SetFont(textIdType);
		SetFont(textIdNumber);
		SetFont(textAge);
		SetFont(textGender);
		SetFont(textName1);
		SetFont(textName2);
		
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.setEditable(false);
		textCustomerId.addKeyListener(keyListener);

		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		textCustomerId.setText(data.get("CUSTID"));
		AddInputComponent(btnOK, 0, 7, 8, 1);
		AddInputComponent(lbIdType, 0, 1, 8, 1);
		AddInputComponent(textIdType, 8, 1, GridBagConstraints.RELATIVE, 1);
		textIdType.setText(data.get("IDTYPE"));
		AddInputComponent(lbIdNumber, 0, 2, 8, 1);
		AddInputComponent(textIdNumber, 8, 2, GridBagConstraints.RELATIVE, 1);
		textIdNumber.setText(data.get("IDNO"));
		AddInputComponent(lbGender, 0, 3, 8, 1);
		AddInputComponent(textGender, 8, 3, GridBagConstraints.RELATIVE, 1);
		textGender.setText(data.get("GENDER"));
		AddInputComponent(lbAge, 0, 4, 8, 1);
		AddInputComponent(textAge, 8, 4, GridBagConstraints.RELATIVE, 1);
		textAge.setText(data.get("AGE"));
		AddInputComponent(lbName1, 0, 5, 8, 1);
		AddInputComponent(textName1, 8, 5, GridBagConstraints.RELATIVE, 1);
		textName1.setText(data.get("NAME1"));
		AddInputComponent(lbName2, 0, 6, 8, 1);
		AddInputComponent(textName2, 8, 6, GridBagConstraints.RELATIVE, 1);
		textName2.setText(data.get("NAME2"));
	}
	
	protected void TransactionAction(){
		super.TransactionAction();

		ModifyUserDemo modifyUser = new ModifyUserDemo(parentFrame, mData);
		OpenTransWindow(modifyUser);
		
	}

}



















