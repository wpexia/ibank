package demo.main.operator;


import gui.iBankGui;

import javax.swing.*;
import java.awt.*;

public class AddOperatorFrame extends iBankGui
{
	private static final long serialVersionUID = 90631L;

	private JTextField textName;
	private JComboBox<String> comboBoxGender;
	private JTextField textPassword;
	private JTextField textOrgId;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxAuth;
	private JTextField textConnec;
	private JTextField textOperatorId;

	public AddOperatorFrame(JFrame parent)
	{
		super(parent);

		setTitle("iBank Add Operator Demo");

		JLabel lbOperator   = CreateLable("操作员编号");
		JLabel lbName       = CreateLable("姓名");
		JLabel lbGender     = CreateLable("性别");
		JLabel lbOrgId      = CreateLable("机构编号");
		JLabel lbType       = CreateLable("类型");
		JLabel lbAuth       = CreateLable("权限");
		JLabel lbPassword   = CreateLable("密码");
		JLabel lbConnec     = CreateLable("联系方式");

		textOperatorId = new JTextField();
		textOperatorId.setColumns(15);
		textOperatorId.addKeyListener(keyListener);

		textName = new JTextField();
		textName.setColumns(15);
		textName.addKeyListener(keyListener);

		String[] genders = {"男", "女"};
		comboBoxGender = new JComboBox<String>();
		for (int i = 0; i < genders.length; ++i) {
			comboBoxGender.addItem(genders[i]);
		}
		comboBoxGender.addKeyListener(keyListener);

		textOrgId = new JTextField();
		textOrgId.setColumns(15);
		textOrgId.addKeyListener(keyListener);

		textPassword = new JTextField();
		textPassword.setColumns(15);
		textPassword.addKeyListener(keyListener);

		textConnec = new JTextField();
		textConnec.setColumns(15);
		textConnec.addKeyListener(keyListener);

		String[] types = {"柜员", "非柜员"};
		comboBoxType = new JComboBox<String>();
		for (int i = 0; i < types.length; ++i) {
			comboBoxType.addItem(types[i]);
		}
		comboBoxType.addKeyListener(keyListener);

		String[] auth = {"柜员","管理"};
		comboBoxAuth = new JComboBox<String>();
		for(int i = 0; i< auth.length; ++i){
			comboBoxAuth.addItem(auth[i]);
		}
		comboBoxAuth.addKeyListener(keyListener);


		lbTitle.setText("增加操作员");
		btnOK.setText("增加");
		btnOK.addKeyListener(keyListener);

		SetFont(textConnec);
		SetFont(textName);
		SetFont(textOperatorId);
		SetFont(textOrgId);
		SetFont(textPassword);


		AddInputComponent(lbOperator, 0, 0, 8, 1);
		AddInputComponent(textOperatorId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbOrgId, 0, 1, 8, 1);
		AddInputComponent(textOrgId, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbName, 0, 2, 8, 1);
		AddInputComponent(textName, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbGender, 0, 3, 8, 1);
		AddInputComponent(comboBoxGender, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbConnec, 0, 4, 8, 1);
		AddInputComponent(textConnec, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbPassword, 0, 5, 8, 1);
		AddInputComponent(textPassword, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAuth, 0, 6, 8, 1);
		AddInputComponent(comboBoxAuth, 8, 6, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbType, 0, 7, 8, 1);
		AddInputComponent(comboBoxType, 8, 7, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 8, 8, 1);
	}

}
