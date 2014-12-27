package demo;

import gui.iBankGui;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import ibankapi.Transaction;

public final class AccountTransfer extends iBankGui
{
	private static final long serialVersionUID = 1L;
	private JTextField 		textOutAccount;
	private JTextField 		textInAccount;
	private JTextField 		textTransAmount;
	
	private JTextField 		textOutName;
	private JTextField 		textInName;
	private JTextField 		textTransFee;
	
	
	public AccountTransfer(JFrame parent)
	{
		super(parent);
		setTitle("iBank Account Transfer Transation Demo");

		// input panel

		JLabel lbOutAccount   = CreateLable("转出帐号：");
		JLabel lbInAccount    = CreateLable("转入帐号：");
		JLabel lbTransAmount  = CreateLable("转出金额：");
		
		textOutAccount        = new JTextField();
		textOutAccount.setColumns(15);
	
		textInAccount        = new JTextField();
		textInAccount.setColumns(15);
		
		textTransAmount        = new JTextField();
		textTransAmount.setColumns(15);
		
		lbTitle.setText("转账业务");
		btnOK.setText("转账");
		
		SetFont(textOutAccount);
		SetFont(textInAccount);
		SetFont(textTransAmount);
		
	
		AddInputComponent(lbOutAccount,    0,  0, 8, 1);
		AddInputComponent(textOutAccount,  8,  0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbInAccount,     0,  1, 8, 1);
		AddInputComponent(textInAccount,   8,  1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbTransAmount,   0,  2, 8, 1);
		AddInputComponent(textTransAmount, 8,  2,  GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK,           16,  2, GridBagConstraints.RELATIVE, 1);
		
				
		// output panel;
		
		JLabel lbOutName      = CreateLable("转出账户：");
		JLabel lbInName       = CreateLable("转入账户：");
		JLabel lbTransFee     = CreateLable("手续费：");
		
		textOutName        = new JTextField();
		textOutName.setColumns(15);
		textOutName.setEnabled(false);
		
		
		textInName        = new JTextField();
		textInName.setColumns(15);
		textInName.setEnabled(false);
		
		textTransFee        = new JTextField();
		textTransFee.setColumns(15);
		textTransFee.setEnabled(false);
		
		SetFont(textOutName);
		SetFont(textInName);
		SetFont(textTransFee);
				
		AddOutputComponent(lbOutName,    0, 0, 8, 1);
		AddOutputComponent(textOutName,  8, 0, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbInName,     0, 1, 8, 1);
		AddOutputComponent(textInName,   8, 1, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbTransFee,   0, 2, 8, 1);
		AddOutputComponent(textTransFee, 8, 2, GridBagConstraints.RELATIVE, 1);
		
	}
	
		
	protected void TransactionAction()
	{
		super.TransactionAction();
		// check input
		if (textOutAccount.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请输入转出帐号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (textInAccount.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请输入转入帐号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (textTransAmount.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请输入转账金额", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		
		Transaction Trans = new Transaction("900001");
		data.put("ACCTOUT",  textOutAccount.getText());
		data.put("ACCTIN",   textInAccount.getText());
		data.put("TRSAMT",   textTransAmount.getText());
		
		bRet = Trans.Init();
		
		if (!bRet)
		{
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}
		
		bRet = Trans.SendMessage(data);
		if (!bRet)
		{
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}
		
		String nameIn  = Trans.GetResponseValue("NAMEIN");
		String nameOut = Trans.GetResponseValue("NAMEOUT"); 
		String fee     = Trans.GetResponseValue("TRSFEE");
		
		textOutName.setText(nameOut);
		textInName.setText(nameIn);
		textTransFee.setText(fee);
		
		ShowStatusMessage(Trans.GetStatusMsg());
			
		Trans.Release();
			
	}
	

}
