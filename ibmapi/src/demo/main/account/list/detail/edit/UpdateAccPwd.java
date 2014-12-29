package demo.main.account.list.detail.edit;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.iBankGui;

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

}
