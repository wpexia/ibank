package demo.main.organization.menu;


import demo.main.operator.menu.ModifyOperatorMenu;
import gui.iBankGui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ShowOrganizationFrame extends iBankGui
{
	private JTextField textOrgId;
	private JTextField textCode;
	private JTextField textAddress;
	private JTextField textType;
	private JTextField textAuth;
	private JTextField textConnec;

	private HashMap<String, String> mdata;

	public ShowOrganizationFrame(JFrame parent,HashMap<String, String> data)
	{
		super(parent);

		mdata = data;

		setTitle("Show Organization");

		JLabel lbOrgId = CreateLable("机构编号");
		JLabel lbCode = CreateLable("代号");
		JLabel lbAddress = CreateLable("地址");
		JLabel lbType = CreateLable("类型");
		JLabel lbAuth = CreateLable("权限");
		JLabel lbConnec = CreateLable("联系方式");

		textOrgId = new JTextField();
		textOrgId.setColumns(15);
		textOrgId.addKeyListener(keyListener);
		textOrgId.setEditable(false);
		textOrgId.setText(data.get("ORGID"));

		textCode = new JTextField();
		textCode.setColumns(15);
		textCode.addKeyListener(keyListener);
		textCode.setEditable(false);
		textCode.setText(data.get("CODE"));

		textAddress = new JTextField();
		textAddress.setColumns(15);
		textAddress.addKeyListener(keyListener);
		textAddress.setEditable(false);
		textAddress.setText(data.get("ADDRES"));

		textType = new JTextField();
		textType.setColumns(15);
		textType.addKeyListener(keyListener);
		textType.setEditable(false);
		textType.setText(data.get("TYPE"));


		textAuth = new JTextField();
		textAuth.setColumns(15);
		textAuth.addKeyListener(keyListener);
		textAuth.setEditable(false);
		textAuth.setText(data.get("AUTH"));


		textConnec = new JTextField();
		textConnec.setColumns(15);
		textConnec.addKeyListener(keyListener);
		textConnec.setEditable(false);
		textConnec.setText(data.get("CONNEC"));

		lbTitle.setText("操作机构");
		btnOK.setText("操作");
		btnOK.addKeyListener(keyListener);


		SetFont(textAddress);
		SetFont(textCode);
		SetFont(textOrgId);
		SetFont(textConnec);


		AddInputComponent(lbOrgId, 0, 0, 8, 1);
		AddInputComponent(textOrgId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbCode, 0, 1, 8, 1);
		AddInputComponent(textCode, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAddress, 0, 2, 8, 1);
		AddInputComponent(textAddress, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbType, 0, 3, 8, 1);
		AddInputComponent(textType, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAuth, 0, 4, 8, 1);
		AddInputComponent(textAuth, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbConnec, 0, 5, 8, 1);
		AddInputComponent(textConnec, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 6, 8, 1);
	}

	protected void TransactionAction()
	{
		super.TransactionAction();

		dispose();
		ModifyOrganizationMenu modifyOrganizationMenu = new ModifyOrganizationMenu(parentFrame,mdata);
		OpenTransWindow(modifyOrganizationMenu);
	}
}
