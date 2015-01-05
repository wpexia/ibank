package demo.main.account.list.detail;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import demo.main.account.list.detail.edit.DeleteAccountFrame;
import demo.main.account.list.detail.edit.QuerySubAccountMenu;
import demo.main.account.list.detail.edit.UpdateAccPwd;
import demo.main.account.list.detail.edit.UpdateAccountFrame;
import gui.iBankGui;
import gui.iBankMenu;
import ibankapi.Transaction;

public class ShowAccountDetailMenu extends iBankMenu {

	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textAccountId;
	private JTextField textDate;
	private JTextField textOrganizationId;
	private JTextField textCustomerId;
	
	private HashMap<String, String>data;


	public ShowAccountDetailMenu(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		setTitle("Show Account Detail Demo");
		JLabel lbCustNo      = CreateLable("客户号:      " + data.get("CUSTID"));
		JLabel lbAccountNo   = CreateLable("账户号:      " + data.get("ACCTNO"));
		JLabel lbAccountDate = CreateLable("开户日期:     " + data.get("ACDATE"));
		JLabel lbOrgNo       = CreateLable("开户机构号:   " + data.get("ORGID"));
		JLabel lbState       = CreateLable("账户状态:     " + data.get("STATE"));
		
		AddMenuItem(lbCustNo);
		AddMenuItem(lbAccountNo);
		AddMenuItem(lbAccountDate);
		AddMenuItem(lbOrgNo);
		AddMenuItem(lbState);
		AddMenuItem(new JLabel("/n"));
		
		JLabel lbUpdateAcc =  CreateLable(" 1. 修改账户信息");
		JLabel lbUpdatePwd =  CreateLable(" 2. 修改账户密码");
		JLabel lbSubAccount = CreateLable(" 3. 查看子账户");
		JLabel lbDeleteAcc =  CreateLable(" 4. 注销该账户");
		
		AddMenuItem(lbUpdateAcc);
		AddMenuItem(lbUpdatePwd);
		AddMenuItem(lbSubAccount);
		AddMenuItem(lbDeleteAcc);
	}
	
	protected void OpenTransFrame(String menuItem){
		super.OpenTransFrame(menuItem);
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择账户操作", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(menuItem.endsWith("90")){
			returnMain();
		}
		else if(menuItem.equals("1")){
			UpdateAccountFrame updateAccount = new UpdateAccountFrame(this, data);
			OpenTransWindow(updateAccount);
		}
		else if(menuItem.equals("2")){
			UpdateAccPwd updatePwd = new UpdateAccPwd(this, data);
			OpenTransWindow(updatePwd);
		}
		else if(menuItem.equals("3")){
			querySubAcc();
			QuerySubAccountMenu querySubAcc = new QuerySubAccountMenu(this, data);
			OpenMenuWindow(querySubAcc);
		}
		else if(menuItem.equals("4")){
			DeleteAccountFrame deleteAccount = new DeleteAccountFrame(this, data);
			OpenTransWindow(deleteAccount);
		}
	}

	private void querySubAcc()
	{
		boolean bRet;
		HashMap<String, String>mapDetail = new HashMap<String, String>();
		Transaction Trans = new Transaction("100058");

		mapDetail.put("ACCTNO",data.get("ACCTNO"));

		bRet = Trans.Init();

		if (!bRet) {
			return;
		}

		bRet = Trans.SendMessage(mapDetail);
		if(!bRet){
			return;
		}

		if(!Trans.GetStatus())
			return;


		int MAXSUB = Integer.parseInt(Trans.GetResponseValue("MAXSUB"));
		Trans.Release();


		for (int i=0; i< MAXSUB;i++)
		{
			Trans = new Transaction("100088");
			mapDetail.put("SUBID",Integer.toString(i));
			bRet = Trans.Init();

			if (!bRet) {
				continue;
			}

			bRet = Trans.SendMessage(mapDetail);
			if(!bRet){
				continue;
			}

			if(!Trans.GetStatus())
				continue;

			data.put(Integer.toString(i),Trans.GetResponseValue("SUBID"));

		}
	}
	
}
