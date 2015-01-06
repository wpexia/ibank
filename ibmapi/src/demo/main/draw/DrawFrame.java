package demo.main.draw;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import demo.main.account.list.detail.edit.QuerySubAccountMenu;
import gui.iBankGui;
import ibankapi.Transaction;

public class DrawFrame extends iBankGui{

	private static final long serialVersionUID = 1L;
	
	private JTextField textAccountNo;
	private JTextField textAmount;
	private JTextField textBalance;
	public DrawFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Draw Demo");
		JLabel lbAccountNo = CreateLable("账户号");
		JLabel lbAmount = CreateLable("取款金额");
		JLabel lbBalance = CreateLable("余额");
		
		textAccountNo = new JTextField();
		textAccountNo.setColumns(15);
		textAccountNo.addKeyListener(keyListener);
		
		textAmount = new JTextField();
		textAmount.setColumns(15);
		textAmount.addKeyListener(keyListener);
		
		textBalance = new JTextField();
		textBalance.setColumns(15);
		textBalance.addKeyListener(keyListener);
		textBalance.setEditable(false);
		
		SetFont(textAccountNo);
		SetFont(textBalance);
		SetFont(textAmount);
		
		lbTitle.setText("取款");
		lbTitle.addKeyListener(keyListener);
		btnOK.setText("取款");
		btnOK.addKeyListener(keyListener);
		
		AddInputComponent(lbAccountNo, 0, 0, 8, 1);
		AddInputComponent(textAccountNo, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAmount, 0, 1, 8, 1);
		AddInputComponent(textAmount, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 2, 8, 1);
		
		AddOutputComponent(lbBalance, 0, 0, 8, 1);
		AddOutputComponent(textBalance, 8, 0, GridBagConstraints.RELATIVE, 1);
	}
	
	protected void TransactionAction() {
		super.TransactionAction();

		if (textAccountNo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入账户号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (textAmount.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入金额", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		Transaction Trans = new Transaction("100053");

		data.put("ACCTNO",textAccountNo.getText());
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
