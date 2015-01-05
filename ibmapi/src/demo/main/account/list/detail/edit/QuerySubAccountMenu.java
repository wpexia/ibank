package demo.main.account.list.detail.edit;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import demo.main.account.list.detail.ShowAccountDetailMenu;
import demo.main.account.list.detail.edit.subdetail.ShowSubAccountDetailFrame;

import gui.iBankMenu;

public class QuerySubAccountMenu extends iBankMenu {

	private static final long serialVersionUID = 9063166698820167875L;
	private HashMap<String, String>data;
	public QuerySubAccountMenu(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		setTitle("Sub Account List");
		AddMenuItem(CreateLable("账户号：  " + data.get("ACCTNO")));
		AddMenuItem(CreateLable("该账户的子账户信息如下"));
		
		for(int i = 0; i < data.size() - 6;i ++){
			JLabel lbAccount = CreateLable((i + 1) + " : " + data.get(i + ""));
			AddMenuItem(lbAccount);
		}
		AddMenuItem(lbExit);
		// TODO Auto-generated constructor stub
	}
	
	protected void OpenTransFrame(String menuItem){
		super.OpenTransFrame(menuItem);
		if(menuItem.isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择子账户", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(menuItem.equals("90")){
			returnMain();
		}
		else {
			HashMap<String, String>mapDetail = new HashMap<String, String>();
			mapDetail.put("ACCTNO", data.get("ACCTNO"));
/*****************这部分是自己编的数据**************/
			mapDetail.put("SUBID", "789");
			mapDetail.put("CRDATE", "20141230");
			mapDetail.put("JISHU", "jishushishenme");
			mapDetail.put("SATYPE", "2");
			mapDetail.put("BALANCE", "233");
			mapDetail.put("RATE", "100%");
			mapDetail.put("EXTIME", "null");
			mapDetail.put("OPRATE", "2");
/********************************************/
			ShowSubAccountDetailFrame showSubAccountDetail = new ShowSubAccountDetailFrame(this, mapDetail);
			OpenTransWindow(showSubAccountDetail);
		}
	}

}
