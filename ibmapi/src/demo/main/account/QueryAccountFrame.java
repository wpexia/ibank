package demo.main.account;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import demo.main.account.detail.ShowAccountsMenu;
import gui.iBankGui;

public class QueryAccountFrame extends iBankGui{

	private static final long serialVersionUID = 9063166698820167875L;

	private JTextField textCustomerId;
	
	public QueryAccountFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Query Account Demo");
		
		lbTitle.setText("查询账户");
		btnOK.setText("查询");
		
		JLabel lbCustomerID = CreateLable("客户号");
		textCustomerId = new JTextField();
		textCustomerId.setColumns(15);
		textCustomerId.addKeyListener(keyListener);
		SetFont(textCustomerId);
		
		
		AddInputComponent(lbCustomerID, 0, 0, 8, 1);
		AddInputComponent(textCustomerId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);
		
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textCustomerId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入用户ID", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("CUSTID", textCustomerId.getText());
/************这段代码是自己编的数据**************/
//		data.put("ACCTNO", "A123001");
//		data.put("ACDATE", "20141228");
//		data.put("ORGID", "000003");
//		data.put("STATE", "0");
//		data.put("PASSWD", "123456");
		data.put("0", "A1230001");
		data.put("1", "A1230002");
		data.put("2", "A1230003");
		data.put("3", "A1230004");
/********************/

//		Transaction Trans = new Transaction("通过用户ID查询账户");
//		bRet = Trans.Init();
//
//		if (!bRet) {
//			ShowStatusMessage(Trans.GetStatusMsg());
//			return;
//		}
//
//		bRet = Trans.SendMessage(data);
//		if (!bRet) {
//			ShowStatusMessage(Trans.GetStatusMsg());
//			return;
//		}
//		ShowStatusMessage(Trans.GetStatusMsg());
//		if(!Trans.GetStatus())
//			return;

//		data.put("IDTYPE", Trans.GetResponseValue("IDTYPE"));
//		data.put("IDNO", Trans.GetResponseValue("IDNO"));
//		data.put("GENDER", Trans.GetResponseValue("GENDER"));
//		data.put("AGE", Trans.GetResponseValue("AGE"));
//		data.put("NAME1", Trans.GetResponseValue("NAME1"));
//		data.put("NAME2", Trans.GetResponseValue("NAME2"));
		
		ShowAccountsMenu showAccount = new ShowAccountsMenu(this, data);
		OpenTransWindow(showAccount);
		//Trans.Release();
	}

}
