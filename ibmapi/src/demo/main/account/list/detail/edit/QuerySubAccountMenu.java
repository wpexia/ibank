package demo.main.account.list.detail.edit;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.iBankMenu;

public class QuerySubAccountMenu extends iBankMenu {

	private static final long serialVersionUID = 9063166698820167875L;
	private HashMap<String, String>data;
	public QuerySubAccountMenu(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		// TODO Auto-generated constructor stub
	}

}
