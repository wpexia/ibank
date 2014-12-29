package demo.main.account;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.iBankGui;

public class AddAccountFrame extends iBankGui{
	
	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textAccountId;
	private JTextField textDate;
	private JTextField textOrganizationId;
	private JPasswordField pwd;
	private JPasswordField pwdConfirm;
	private JTextField textCustomerId;

	public AddAccountFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Add Account Demo");
		JLabel lbAccountId = CreateLable("账户号");
		JLabel lbDate = CreateLable("创建日期");
		JLabel lbOrgId = CreateLable("开户机构号");
		JLabel lbPwd = CreateLable("密码");
		JLabel lbPwdConfirm = CreateLable("确认密码");
		JLabel lbCustomerID = CreateLable("客户号");
		
		textAccountId = new JTextField();
		textAccountId.setColumns(15);
		textAccountId.addKeyListener(keyListener);
		
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.addKeyListener(keyListener);
		
		textDate = new JTextField();
		textDate.setColumns(15);
		textDate.addKeyListener(keyListener);
		
		textOrganizationId = new JTextField();
		textOrganizationId.setColumns(15);
		textOrganizationId.addKeyListener(keyListener);
		
		pwd = new JPasswordField();
		pwd.setColumns(15);
		pwd.addKeyListener(keyListener);
		
		pwdConfirm = new JPasswordField();
		pwdConfirm.setColumns(15);
		pwdConfirm.addKeyListener(keyListener);
		
		lbTitle.setText("增加账户");
		btnOK.setText("增加");
		
		SetFont(textAccountId);
		SetFont(textCustomerId);
		SetFont(textDate);
		SetFont(textOrganizationId);
		SetFont(pwd);
		SetFont(pwdConfirm);
		
		AddInputComponent(lbDate, 0, 0, 8, 1);
		AddInputComponent(textDate, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbCustomerID, 0, 1, 8, 1);
		AddInputComponent(textCustomerId, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbOrgId, 0, 2, 8, 1);
		AddInputComponent(textOrganizationId, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbPwd, 0, 3, 8, 1);
		AddInputComponent(pwd, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbPwdConfirm, 0, 4, 8, 1);
		AddInputComponent(pwdConfirm, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 5, 8, 1);
		
		AddOutputComponent(lbAccountId, 0, 0, 8, 1);
		AddOutputComponent(textAccountId, 8, 0, GridBagConstraints.RELATIVE, 1);
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textCustomerId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入用户ID", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (textDate.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入日期", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (textOrganizationId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入机构号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (pwd.getPassword().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入密码", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (pwdConfirm.getPassword().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请确认密码", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!(pwd.getPassword().toString().equals(pwdConfirm.getPassword().toString()))){
			JOptionPane.showMessageDialog(null, "密码不一致", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("添加账户");

		data.put("日期", "" + textDate.getText());
		data.put("用户ID", textCustomerId.getText());
		data.put("机构号", textOrganizationId.getText());
		data.put("密码", pwd.getPassword().toString());

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

		String accountID = Trans.GetResponseValue("账户号");
		textAccountId.setText(accountID);

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}

}
