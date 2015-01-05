package demo.main.operator;


import gui.iBankGui;
import ibankapi.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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

		JLabel lbOperatorId   = CreateLable("操作员编号");
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


		AddInputComponent(lbOrgId, 0, 0, 8, 1);
		AddInputComponent(textOrgId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbName, 0, 1, 8, 1);
		AddInputComponent(textName, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbGender, 0, 2, 8, 1);
		AddInputComponent(comboBoxGender, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbConnec, 0, 3, 8, 1);
		AddInputComponent(textConnec, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbPassword, 0, 4, 8, 1);
		AddInputComponent(textPassword, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAuth, 0, 5, 8, 1);
		AddInputComponent(comboBoxAuth, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbType, 0, 6, 8, 1);
		AddInputComponent(comboBoxType, 8, 6, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 7, 8, 1);

		AddOutputComponent(lbOperatorId, 0, 0, 8, 1);
		AddOutputComponent(textOperatorId, 8, 0, GridBagConstraints.RELATIVE, 1);

	}

	protected void TransactionAction()
	{
		super.TransactionAction();

		if (textOrgId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入机构ID", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入操作员姓名", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textConnec.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入联系方式", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入操作员密码", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		boolean bRet;
		HashMap<String, String> kdata = new HashMap<String, String>();

		Transaction Trans = new Transaction("100060");

		kdata.put("ORGID",textOrgId.getText());

		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(kdata);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		String MAXOPR = Integer.toString(Integer.parseInt(Trans.GetResponseValue("MAXOPR")) + 1);
		Trans.Release();

		Trans = new Transaction("100066");
		kdata.put("OPID",MAXOPR);
		kdata.put("NAME1",textName.getText());
		kdata.put("GENDER",Integer.toString(comboBoxGender.getSelectedIndex()));
		kdata.put("PASSWD",textPassword.getText());
		kdata.put("CONNEC",textConnec.getText());
		kdata.put("TYPE",Integer.toString(comboBoxType.getSelectedIndex()));
		kdata.put("AUTH",Integer.toString(comboBoxAuth.getSelectedIndex()));


		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(kdata);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		ShowStatusMessage(Trans.GetStatusMsg());
		Trans.Release();

		textOperatorId.setText(MAXOPR);
	}

}
