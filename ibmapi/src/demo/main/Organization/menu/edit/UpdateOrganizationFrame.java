package demo.main.organization.menu.edit;


import gui.iBankGui;
import ibankapi.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class UpdateOrganizationFrame extends iBankGui
{
	private HashMap<String, String> mData;

	private JTextField textOrgId;
	private JTextField textCode;
	private JTextField textAddress;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxAuth;
	private JTextField textConnec;

	public UpdateOrganizationFrame(JFrame parent, HashMap<String, String> data)
	{
		super(parent);
		mData = data;

		JLabel lbOrgId = CreateLable("机构编号");
		JLabel lbCode = CreateLable("代号");
		JLabel lbAddress = CreateLable("地址");
		JLabel lbType = CreateLable("类型");
		JLabel lbAuth = CreateLable("权限");
		JLabel lbConnec = CreateLable("联系方式");

		textOrgId = new JTextField();
		textOrgId.setColumns(15);
		textOrgId.addKeyListener(keyListener);
		textOrgId.setText(data.get("ORGID"));
		textOrgId.setEditable(false);

		textCode = new JTextField();
		textCode.setColumns(15);
		textCode.addKeyListener(keyListener);
		textCode.setText(data.get("CODE"));

		textAddress = new JTextField();
		textAddress.setColumns(15);
		textAddress.addKeyListener(keyListener);
		textAddress.setText(data.get("ADDRES"));

		String[] types = {"柜员", "非柜员"};
		comboBoxType = new JComboBox<String>();
		for (int i = 0; i < types.length; ++i)
		{
			comboBoxType.addItem(types[i]);
		}
		comboBoxType.addKeyListener(keyListener);
		comboBoxType.setSelectedIndex(Integer.parseInt(data.get("TYPE")));

		String[] auth = {"柜员", "管理"};
		comboBoxAuth = new JComboBox<String>();
		for (int i = 0; i < auth.length; ++i)
		{
			comboBoxAuth.addItem(auth[i]);
		}
		comboBoxAuth.addKeyListener(keyListener);
		comboBoxAuth.setSelectedIndex(Integer.parseInt(data.get("AUTH")));


		textConnec = new JTextField();
		textConnec.setColumns(15);
		textConnec.addKeyListener(keyListener);
		textConnec.setText(data.get("CONNEC"));


		lbTitle.setText("更新机构");
		btnOK.setText("更新");
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

	protected void TransactionAction()
	{
		super.TransactionAction();

		if (textOrgId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入机构ID", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textCode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入代号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textAddress.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入地址", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textConnec.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入联系方式", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100067");

		data.put("ORGID",textOrgId.getText());
		data.put("CODE", textCode.getText());
		data.put("ADDRES",textAddress.getText());
		data.put("TYPE",Integer.toString(comboBoxType.getSelectedIndex()));
		data.put("AUTH",Integer.toString(comboBoxAuth.getSelectedIndex()));
		data.put("CONNEC",textConnec.getText());

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

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}
}
