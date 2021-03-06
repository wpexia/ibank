package demo.main.user.menu;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.iBankGui;

public class ShowUserFrame extends iBankGui{
	
	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textIdType;
	private JTextField textIdNumber;
	private JTextField textGender;
	private JTextField textAge;
	private JTextField textName1;
	private JTextField textName2;
	private JTextField textCustomerId;
	private JTextField textBirth;
	private JTextField textAddress;
	private JTextField textConnect;
	private HashMap<String, String>mData;

	public ShowUserFrame(JFrame parent, HashMap<String, String>data) {
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
		JLabel lbBirth = CreateLable("出生年月");
		JLabel lbAddress = CreateLable("地址");
		JLabel lbConnec = CreateLable("联系方式");

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
		
		textBirth = new JTextField();
		textBirth.setColumns(15);
		textBirth.setEditable(false);
		
		textAddress = new JTextField();
		textAddress.setColumns(15);
		textAddress.setEditable(false);
		
		textConnect = new JTextField();
		textConnect.setColumns(15);
		textConnect.setEditable(false);

		lbTitle.setText("编辑用户");
		btnOK.setText("操作");

		SetFont(textIdType);
		SetFont(textIdNumber);
		SetFont(textAge);
		SetFont(textGender);
		SetFont(textName1);
		SetFont(textName2);
		SetFont(textConnect);
		SetFont(textBirth);
		SetFont(textAddress);
		
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.setEditable(false);
		textCustomerId.addKeyListener(keyListener);

		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		textCustomerId.setText(data.get("CUSTID"));
		
		AddInputComponent(lbIdType, 0, 1, 8, 1);
		AddInputComponent(textIdType, 8, 1, GridBagConstraints.RELATIVE, 1);
		String type = "";
		switch (data.get("IDTYPE")) {
		case "A":
			type = "身份证";
			break;
		case "B":
			type = "军官证";
			break;
		case "C":
			type = "护照";
			break;
		default:
			break;
		}
		textIdType.setText(type);
		AddInputComponent(lbIdNumber, 0, 2, 8, 1);
		AddInputComponent(textIdNumber, 8, 2, GridBagConstraints.RELATIVE, 1);
		textIdNumber.setText(data.get("IDNO"));
		AddInputComponent(lbGender, 0, 3, 8, 1);
		AddInputComponent(textGender, 8, 3, GridBagConstraints.RELATIVE, 1);
		String gender = (Integer.parseInt(data.get("GENDER")) == 0)?"男":"女";
		textGender.setText(gender);
		AddInputComponent(lbAge, 0, 4, 8, 1);
		AddInputComponent(textAge, 8, 4, GridBagConstraints.RELATIVE, 1);
		textAge.setText(data.get("AGE"));
		AddInputComponent(lbName1, 0, 5, 8, 1);
		AddInputComponent(textName1, 8, 5, GridBagConstraints.RELATIVE, 1);
		textName1.setText(data.get("NAME1"));
		AddInputComponent(lbName2, 0, 6, 8, 1);
		AddInputComponent(textName2, 8, 6, GridBagConstraints.RELATIVE, 1);
		textName2.setText(data.get("NAME2"));
		AddInputComponent(lbBirth, 0, 7, 8, 1);
		AddInputComponent(textBirth, 8, 7, GridBagConstraints.RELATIVE, 1);
		textBirth.setText(data.get("BIRTH"));
		AddInputComponent(lbAddress, 0, 8, 8, 1);
		AddInputComponent(textAddress, 8, 8, GridBagConstraints.RELATIVE, 1);
		textAddress.setText(data.get("ADDRES"));
		AddInputComponent(lbConnec, 0, 9, 8, 1);
		AddInputComponent(textConnect, 8, 9, GridBagConstraints.RELATIVE, 1);
		textConnect.setText(data.get("CONNEC"));
		AddInputComponent(btnOK, 0, 10, 8, 1);
	}
	
	protected void TransactionAction(){
		super.TransactionAction();

		dispose();
		ModifyUserMenu modifyUser = new ModifyUserMenu(parentFrame, mData);
		OpenTransWindow(modifyUser);
		
	}

}



















