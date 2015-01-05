package demo.main.account;

import gui.MD5Util;
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
		
		if(!(String.copyValueOf(pwd.getPassword()).equals(String.copyValueOf(pwdConfirm.getPassword())))){
			JOptionPane.showMessageDialog(null, "密码不一致", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();




		String ACCTNO = creatAcc(textOrganizationId.getText());
		if(ACCTNO.equals(""))
			return;

		Transaction Trans = new Transaction("100094");
		textAccountId.setText(ACCTNO);

		data.put("STATE","1");
		data.put("ACCTNO", ACCTNO);
		data.put("ACDATE", textDate.getText());
		data.put("CUSTID", textCustomerId.getText());
		data.put("ORGID", textOrganizationId.getText());
		data.put("PASSWD", String.copyValueOf(pwd.getPassword()));

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

	private String creatAcc(String ORGID)
	{
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		Transaction Trans = new Transaction("100062");

		data.put("ORGID",ORGID);
		bRet = Trans.Init();
		if (!bRet) {
			return "";
		}
		bRet = Trans.SendMessage(data);
		if (!bRet) {
			return "";
		}

		if(!Trans.GetStatus())
			return "";
		String MAXACC = String.format("%08d",Integer.parseInt(Trans.GetResponseValue("MAXACC")) + 1);
		Trans.Release();


		Trans = new Transaction("100061");
		data.put("MAXACC",MAXACC);
		bRet = Trans.Init();
		if (!bRet) {
			return "";
		}
		bRet = Trans.SendMessage(data);
		if (!bRet) {
			return "";
		}

		if(!Trans.GetStatus())
			return "";
		Trans.Release();


		String newAcc = ORGID + MAXACC + calc(ORGID + MAXACC);


		Trans = new Transaction("100056");
		data = new HashMap<String, String>();
		data.put("CUSTID",textCustomerId.getText());
		bRet = Trans.Init();

		if (!bRet) {
			return "";
		}

		bRet = Trans.SendMessage(data);
		if (!bRet) {
			return "";
		}

		String CUSACC = Trans.GetResponseValue("CUSACC");
		Trans.Release();


		if(CUSACC == null ||CUSACC.isEmpty())
		{
			CUSACC = newAcc;
		}
		else
		{
			CUSACC = CUSACC + "," + newAcc;
		}

		Trans = new Transaction("100055");
		data.put("CUSACC",CUSACC);
		bRet = Trans.Init();

		if (!bRet) {
			return "";
		}

		bRet = Trans.SendMessage(data);
		if (!bRet) {
			return "";
		}
		Trans.Release();

		return newAcc;
	}

	private String calc(String a)
	{
		String tmp = MD5Util.getMD5String(a);
		return String.format("%02d",Math.abs(tmp.hashCode()%100));
	}

}
