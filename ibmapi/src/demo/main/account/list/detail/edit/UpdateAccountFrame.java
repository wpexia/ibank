package demo.main.account.list.detail.edit;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.iBankGui;

public class UpdateAccountFrame extends iBankGui {
//修改账户信息，并不包括密码修改，也不包括修改账户状态（即删除某一账户）。
	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textAccountId;
	private JTextField textDate;
	private JTextField textOrganizationId;
	private JTextField textCustomerId;
	private HashMap<String, String>data ;
	public UpdateAccountFrame(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		setTitle("iBank Update Account");
		JLabel lbAccountId = CreateLable("账户号");
		JLabel lbDate = CreateLable("创建日期");
		JLabel lbOrgId = CreateLable("开户机构号");
		JLabel lbCustomerID = CreateLable("客户号");
		
		textAccountId = new JTextField();
		textAccountId.setColumns(15);
		textAccountId.addKeyListener(keyListener);
		textAccountId.setText(data.get("ACCTNO"));
		textAccountId.setEditable(false);
		
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.addKeyListener(keyListener);
		textCustomerId.setText(data.get("CUSTID"));
		
		textDate = new JTextField();
		textDate.setColumns(15);
		textDate.addKeyListener(keyListener);
		textDate.setText(data.get("ACDATE"));
		
		textOrganizationId = new JTextField();
		textOrganizationId.setColumns(15);
		textOrganizationId.addKeyListener(keyListener);
		textOrganizationId.setText(data.get("ORGID"));
		
		lbTitle.setText("修改账户");
		btnOK.setText("修改");
		
		SetFont(textAccountId);
		SetFont(textCustomerId);
		SetFont(textDate);
		SetFont(textOrganizationId);
		
		AddInputComponent(lbDate, 0, 0, 8, 1);
		AddInputComponent(textDate, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbCustomerID, 0, 1, 8, 1);
		AddInputComponent(textCustomerId, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbOrgId, 0, 2, 8, 1);
		AddInputComponent(textOrganizationId, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 5, 8, 1);
		

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
		
		boolean bRet;
		HashMap<String, String> kdata = new HashMap<String, String>();

		Transaction Trans = new Transaction("100092");

		kdata.put("ACCTNO",textAccountId.getText());
		kdata.put("ACDATE",textDate.getText());
		kdata.put("CUSTID",textCustomerId.getText());
		kdata.put("ORGID",textOrganizationId.getText());
		kdata.put("STATE",data.get("STATE"));
		kdata.put("PASSWD",data.get("PASSWD"));

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
