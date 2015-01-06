package demo.main.account.list.detail.edit;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import demo.main.account.list.detail.edit.subdetail.ShowSubAccountDetailFrame;

import gui.iBankMenu;
import ibankapi.Transaction;

public class QuerySubAccountMenu extends iBankMenu
{

	private static final long serialVersionUID = 9063166698820167875L;
	private HashMap<String, String> data;

	public QuerySubAccountMenu(JFrame parent, HashMap<String, String> mData)
	{
		super(parent);
		data = mData;
		setTitle("Sub Account List");
		AddMenuItem(CreateLable("账户号：  " + data.get("ACCTNO")));
		AddMenuItem(CreateLable("该账户的子账户信息如下"));

		for (int i = 1; i <= data.size() - 6; i++)
		{
			JLabel lbAccount = CreateLable((i) + " : " + data.get(Integer.toString(i)));
			AddMenuItem(lbAccount);
		}
		AddMenuItem(lbExit);
		// TODO Auto-generated constructor stub
	}

	protected void OpenTransFrame(String menuItem)
	{
		super.OpenTransFrame(menuItem);
		if (menuItem.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请选择子账户", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (menuItem.equals("90"))
		{
			returnMain();
		} else
		{
			boolean bRet;
			Transaction Trans = new Transaction("100088");
			HashMap<String, String> mapDetail = new HashMap<String, String>();
			mapDetail.put("ACCTNO", data.get("ACCTNO"));
			mapDetail.put("SUBID", String.format("%04d", Integer.parseInt(data.get(menuItem))));
			bRet = Trans.Init();

			if (!bRet)
			{
				return;
			}

			bRet = Trans.SendMessage(mapDetail);
			if (!bRet)
			{
				return;
			}

			String[] tmp = {"SUBID", "CRDATE", "JISHU", "SATYPE", "BALANCE", "RATE", "EXTIME", "OPRATE"};

			for (String x : tmp)
			{
				mapDetail.put(x, Trans.GetResponseValue(x));
			}

			ShowSubAccountDetailFrame showSubAccountDetail = new ShowSubAccountDetailFrame(this, mapDetail);
			OpenTransWindow(showSubAccountDetail);
		}
	}

}
