package demo.main.organization;

import gui.iBankGui;

import javax.swing.*;
import java.awt.*;

public class AddOrganizationFrame extends iBankGui
{
	private JTextField textOrgId;
	private JTextField textCode;
	private JTextField textAddress;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxAuth;
	private JTextField textConnec;

	public AddOrganizationFrame(JFrame parent)
	{
		super(parent);

		setTitle("Add Organization");

		JLabel lbOrgId = CreateLable("机构编号");
		JLabel lbCode = CreateLable("代号");
		JLabel lbAddress = CreateLable("地址");
		JLabel lbType = CreateLable("类型");
		JLabel lbAuth = CreateLable("权限");
		JLabel lbConnec = CreateLable("联系方式");


		textOrgId = new JTextField();
		textOrgId.setColumns(15);
		textOrgId.addKeyListener(keyListener);

		textCode = new JTextField();
		textCode.setColumns(15);
		textCode.addKeyListener(keyListener);

		textAddress = new JTextField();
		textAddress.setColumns(15);
		textAddress.addKeyListener(keyListener);

		String[] types = {"柜员", "非柜员"};
		comboBoxType = new JComboBox<String>();
		for (int i = 0; i < types.length; ++i)
		{
			comboBoxType.addItem(types[i]);
		}
		comboBoxType.addKeyListener(keyListener);

		String[] auth = {"柜员", "管理"};
		comboBoxAuth = new JComboBox<String>();
		for (int i = 0; i < auth.length; ++i)
		{
			comboBoxAuth.addItem(auth[i]);
		}
		comboBoxAuth.addKeyListener(keyListener);


		textConnec = new JTextField();
		textConnec.setColumns(15);
		textConnec.addKeyListener(keyListener);


		lbTitle.setText("增加机构");
		btnOK.setText("增加");
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
		AddInputComponent(comboBoxType, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAuth, 0, 4, 8, 1);
		AddInputComponent(comboBoxAuth, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbConnec, 0, 5, 8, 1);
		AddInputComponent(textConnec, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 6, 8, 1);
	}

}
