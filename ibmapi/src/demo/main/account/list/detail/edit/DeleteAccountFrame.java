package demo.main.account.list.detail.edit;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.iBankGui;

public class DeleteAccountFrame extends iBankGui{
	
	private JTextField textAccountNo;
	private HashMap<String, String>data;
	public DeleteAccountFrame(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		
		setTitle("iBank Delete Account Demo");
		JLabel lbAccountNo = CreateLable("账户号");
		
		textAccountNo = new JTextField();
		textAccountNo.setColumns(15);
		textAccountNo.addKeyListener(keyListener);
		textAccountNo.setText(data.get("ACCTNO"));
		SetFont(textAccountNo);
		textAccountNo.setEditable(false);
		
		lbTitle.setText("是否删除该账户");
		btnOK.setText("确认删除");
		
		AddInputComponent(lbAccountNo, 0, 0, 8, 1);
		AddInputComponent(textAccountNo, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);
		
		
	}

}
