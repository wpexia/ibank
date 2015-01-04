package demo.main.operator.menu;

import gui.iBankGui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ShowOperatorFrame extends iBankGui
{
	private JTextField textName;
	private JTextField textGender;
	private JTextField textPassword;
	private JTextField textOrgId;
	private JTextField textType;
	private JTextField textAuth;
	private JTextField textConnec;
	private JTextField textOperatorId;

	private HashMap<String, String> mdata;

	public ShowOperatorFrame(JFrame parent, HashMap<String, String> data)
	{
		super(parent);
		mdata = data;

		setTitle("iBank Show Operator Demo");

		JLabel lbOperator = CreateLable("操作员编号");
		JLabel lbName = CreateLable("姓名");
		JLabel lbGender = CreateLable("性别");
		JLabel lbOrgId = CreateLable("机构编号");
		JLabel lbType = CreateLable("类型");
		JLabel lbAuth = CreateLable("权限");
		JLabel lbPassword = CreateLable("密码");
		JLabel lbConnec = CreateLable("联系方式");

		textOperatorId = new JTextField();
		textOperatorId.setColumns(15);
		textOperatorId.setEditable(false);
		textOperatorId.addKeyListener(keyListener);

		textName = new JTextField();
		textName.setColumns(15);
		textName.setEditable(false);
		textName.addKeyListener(keyListener);

		textGender = new JTextField();
		textGender.setColumns(15);
		textGender.setEditable(false);
		textGender.addKeyListener(keyListener);

		textOrgId = new JTextField();
		textOrgId.setColumns(15);
		textOrgId.setEditable(false);
		textOrgId.addKeyListener(keyListener);

		textPassword = new JTextField();
		textPassword.setColumns(15);
		textPassword.setEditable(false);
		textPassword.addKeyListener(keyListener);

		textConnec = new JTextField();
		textConnec.setColumns(15);
		textConnec.setEditable(false);
		textConnec.addKeyListener(keyListener);

		textType = new JTextField();
		textType.setColumns(15);
		textType.setEditable(false);
		textType.addKeyListener(keyListener);

		textAuth = new JTextField();
		textAuth.setColumns(15);
		textAuth.setEditable(false);
		textAuth.addKeyListener(keyListener);


		lbTitle.setText("编辑操作员");
		btnOK.setText("编辑");
		btnOK.addKeyListener(keyListener);

		SetFont(textConnec);
		SetFont(textName);
		SetFont(textOperatorId);
		SetFont(textOrgId);
		SetFont(textPassword);
		SetFont(textAuth);
		SetFont(textType);
		SetFont(textGender);


		AddInputComponent(lbOperator, 0, 0, 8, 1);
		AddInputComponent(textOperatorId, 8, 0, GridBagConstraints.RELATIVE, 1);
		textOperatorId.setText(data.get(""));
		AddInputComponent(lbOrgId, 0, 1, 8, 1);
		AddInputComponent(textOrgId, 8, 1, GridBagConstraints.RELATIVE, 1);
		textOrgId.setText("");
		AddInputComponent(lbName, 0, 2, 8, 1);
		AddInputComponent(textName, 8, 2, GridBagConstraints.RELATIVE, 1);
		textOrgId.setText("");
		AddInputComponent(lbGender, 0, 3, 8, 1);
		AddInputComponent(textGender, 8, 3, GridBagConstraints.RELATIVE, 1);
		textGender.setText("");
		AddInputComponent(lbConnec, 0, 4, 8, 1);
		AddInputComponent(textConnec, 8, 4, GridBagConstraints.RELATIVE, 1);
		textConnec.setText("");
		AddInputComponent(lbPassword, 0, 5, 8, 1);
		AddInputComponent(textPassword, 8, 5, GridBagConstraints.RELATIVE, 1);
		textPassword.setText("");
		AddInputComponent(lbAuth, 0, 6, 8, 1);
		AddInputComponent(textAuth, 8, 6, GridBagConstraints.RELATIVE, 1);
		textAuth.setText("");
		AddInputComponent(lbType, 0, 7, 8, 1);
		AddInputComponent(textType, 8, 7, GridBagConstraints.RELATIVE, 1);
		textType.setText("");
		AddInputComponent(btnOK, 0, 8, 8, 1);
	}

	protected void TransactionAction()
	{
		super.TransactionAction();

		dispose();
		ModifyOperatorMenu modifyOperatorMenu = new ModifyOperatorMenu(parentFrame,mdata);
		OpenTransWindow(modifyOperatorMenu);
	}
}
