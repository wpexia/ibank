package demo.main.account.detail;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import demo.main.account.list.detail.ShowAccountDetailMenu;
import gui.iBankMenu;
import ibankapi.Transaction;

public class ShowAccountsMenu extends iBankMenu {
	private HashMap<String, String>data;
	public ShowAccountsMenu(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		setTitle("Account List");
		AddMenuItem(CreateLable("客户号：   " + data.get("CUSTID")));
		AddMenuItem(CreateLable("该用户账户信息如下"));
		
		for(int i = 0; i < data.size() - 10;i ++){
			JLabel lbAccount = CreateLable((i + 1) + " : " + data.get(i + ""));
			AddMenuItem(lbAccount);
		}
		AddMenuItem(lbExit);
	}
	
	protected void OpenTransFrame(String menuItem){
		super.OpenTransFrame(menuItem);
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择账户", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(menuItem.equals("90")){
			returnMain();
		}
		else {
			boolean bRet;
			HashMap<String, String>mapDetail = new HashMap<String, String>();
			Transaction Trans = new Transaction("100091");

			mapDetail.put("ACCTNO",data.get(Integer.toString(Integer.parseInt(menuItem)-1)));

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


			String[] tmp = {"CUSTID", "ACDATE", "ORGID", "STATE", "PASSWD"};
			for(String x : tmp)
			{
				mapDetail.put(x, Trans.GetResponseValue(x));
			}
			Trans.Release();

			ShowAccountDetailMenu showAccountDetail = new ShowAccountDetailMenu(this, mapDetail);
			OpenMenuWindow(showAccountDetail);
		}
	}

}










