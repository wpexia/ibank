package demo.main.transfer;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import gui.iBankGui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import demo.main.account.list.detail.edit.QuerySubAccountMenu;
import ibankapi.Transaction;

public class TransferFrame extends iBankGui {

	private static final long serialVersionUID = 1L;
	private JTextField textFromAccountNo ;
	private JTextField textToAccountNo;
	private JTextField textAmount;
	
	private JTextField textBalance;
	
	public TransferFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Transfer Demo");
		JLabel lbFromAccountNo = CreateLable("转出账户号");
		JLabel lbToAccountNo = CreateLable("转入账户号");
		JLabel lbAmount = CreateLable("转账金额");
		
		JLabel lbBalance = CreateLable("余额");
		
		textAmount = new JTextField();
		textAmount.setColumns(15);
		textAmount.addKeyListener(keyListener);
		
		textBalance = new JTextField();
		textBalance.setColumns(15);
		textBalance.addKeyListener(keyListener);
		textBalance.setEditable(false);
		
		textFromAccountNo = new JTextField();
		textFromAccountNo.setColumns(15);
		textFromAccountNo.addKeyListener(keyListener);
		
		textToAccountNo = new JTextField();
		textToAccountNo.setColumns(15);
		textToAccountNo.addKeyListener(keyListener);
		
		SetFont(textBalance);
		SetFont(textFromAccountNo);
		SetFont(textToAccountNo);
		SetFont(textAmount);
		
		lbTitle.setText("转账");
		lbTitle.addKeyListener(keyListener);
		btnOK.setText("转账");
		btnOK.addKeyListener(keyListener);
		
		AddInputComponent(lbFromAccountNo, 0, 0, 8, 1);
		AddInputComponent(textFromAccountNo, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbToAccountNo, 0, 1, 8, 1);
		AddInputComponent(textToAccountNo, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAmount, 0, 2, 8, 1);
		AddInputComponent(textAmount, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 3, 8, 1);
		
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textFromAccountNo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入转出账户号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (textToAccountNo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入转入账户号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (textAmount.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入金额", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		Transaction Trans = new Transaction("100053");

		data.put("ACCTNO",textFromAccountNo.getText());
		data.put("SUBID","0001");
		data.put("AMOUNT", String.format("%012.0f", Double.parseDouble(textAmount.getText()) * 1000));
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

		if(!Trans.GetStatus())
		{
			JOptionPane.showMessageDialog(null, "活期账户金额不足", "错误", JOptionPane.ERROR_MESSAGE);
			querySubAcc(data);
			QuerySubAccountMenu querySubAcc = new QuerySubAccountMenu(this, data);
			OpenTransWindow(querySubAcc);
		}
		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();



		data = new HashMap<String, String>();
		Trans = new Transaction("100054");

		data.put("ACCTNO",textToAccountNo.getText());
		data.put("SUBID","0001");
		data.put("AMOUNT", String.format("%012.0f", Double.parseDouble(textAmount.getText()) * 1000));
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

	private void querySubAcc(HashMap<String, String> data)
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

		data.put("num",Integer.toString(MAXSUB));
		for (int i=1; i<= MAXSUB;i++)
		{
			Trans = new Transaction("100088");
			mapDetail.put("SUBID",String.format("%04d",i));
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
