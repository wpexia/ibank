package demo.main.account.list.detail.edit;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.*;

import gui.iBankGui;
import ibankapi.Transaction;

public class UpdateAccPwd extends iBankGui {

	private HashMap<String, String>data;;
	private JPasswordField pswdOldPwd;
	private JPasswordField pswdNewPwd;
	private JPasswordField pswdNewPwdConf;
	public UpdateAccPwd(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		setTitle("iBank Update Acc Pwd");
		JLabel lbAccountNo = CreateLable(data.get("ACCTNO"));
		JLabel lbOldPwd = CreateLable("旧密码");
		JLabel lbNewPwd = CreateLable("新密码");
		JLabel lbNewPwdConf = CreateLable("重复新密码");
		
		pswdOldPwd = new JPasswordField();
		pswdOldPwd.setColumns(15);
		pswdOldPwd.addKeyListener(keyListener);
		
		pswdNewPwd = new JPasswordField();
		pswdNewPwd.setColumns(15);
		pswdNewPwd.addKeyListener(keyListener);
		
		pswdNewPwdConf = new JPasswordField();
		pswdNewPwdConf.setColumns(15);
		pswdNewPwdConf.addKeyListener(keyListener);
		
		lbTitle.setText("修改账户密码");
		btnOK.setText("修改");
		
		SetFont(pswdNewPwdConf);
		SetFont(pswdNewPwd);
		SetFont(pswdOldPwd);
		
		AddInputComponent(lbAccountNo, 0, 0, 20, 1);
		AddInputComponent(lbOldPwd, 0, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(pswdOldPwd, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbNewPwd, 0, 2, 8, 1);
		AddInputComponent(pswdNewPwd, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbNewPwdConf, 0, 3, 8, 1);
		AddInputComponent(pswdNewPwdConf, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 4, 8, 1);
		
	}

	protected void TransactionAction()
	{
		super.TransactionAction();

		if (String.copyValueOf(pswdOldPwd.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入旧密码", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (String.copyValueOf(pswdOldPwd.getPassword()).equals(data.get("PASSWD"))) {
			JOptionPane.showMessageDialog(null, "旧密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (String.copyValueOf(pswdNewPwd.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入新密码", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (String.copyValueOf(pswdNewPwdConf.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "请确认新密码", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> kdata = new HashMap<String, String>();


		Transaction Trans = new Transaction("100092");

		kdata.put("ACCTNO", data.get("ACCTNO"));
		kdata.put("ACDATE", data.get("ACDATE"));
		kdata.put("CUSTID", data.get("CUSTID"));
		kdata.put("ORGID", data.get("ORGID"));
		kdata.put("STATE",data.get("STATE"));
		kdata.put("PASSWD",String.copyValueOf(pswdNewPwd.getPassword()));

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
