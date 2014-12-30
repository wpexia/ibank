package demo.main.account.detail;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import demo.main.account.list.detail.ShowAccountDetailMenu;
import gui.iBankMenu;

public class ShowAccountsMenu extends iBankMenu {
	private HashMap<String, String>data;
	public ShowAccountsMenu(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		setTitle("Account List");
		AddMenuItem(CreateLable("客户号：   " + data.get("CUSTID")));
		AddMenuItem(CreateLable("该用户账户信息如下"));
		
		for(int i = 0; i < data.size() - 1;i ++){
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
			HashMap<String, String>mapDetail = new HashMap<>();
			mapDetail.put("CUSTID", data.get("CUSTID"));
/*****************这部分是自己编的数据**************/
			mapDetail.put("ACCTNO", "A123001");
			mapDetail.put("ACDATE", "20141228");
			mapDetail.put("ORGID", "000003");
			mapDetail.put("STATE", "0");
			mapDetail.put("PASSWD", "123456");
/********************************************/
			ShowAccountDetailMenu showAccountDetail = new ShowAccountDetailMenu(this, mapDetail);
			OpenMenuWindow(showAccountDetail);
		}
	}

}










