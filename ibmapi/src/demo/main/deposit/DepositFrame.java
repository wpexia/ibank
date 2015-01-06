package demo.main.deposit;

import ibankapi.Transaction;

import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.iBankGui;

public class DepositFrame extends  iBankGui{

	private static final long serialVersionUID = 1L;
	private JTextField textAccountNo ;
	private JComboBox<String>cbType;
	private JComboBox<String>cbTime;
	private JTextField textAmount;
	
	private JTextField textBalance;
	public DepositFrame(JFrame parent) {
		super(parent);
		setTitle("iBank Desposit Demo");
		JLabel lbAccountNo = CreateLable("账户号");
		JLabel lbType = CreateLable("存款类型");
		JLabel lbAmount = CreateLable("存款金额￥");
		JLabel lbBalance = CreateLable("余额");
		JLabel lbTime = CreateLable("存款期限");
		
		textAccountNo = new JTextField();
		textAccountNo.setColumns(15);
		textAccountNo.addKeyListener(keyListener);
		
		String[] strType = {"活期","定期"};
		cbType = new JComboBox<String>();
		for(int i = 0; i < strType.length;i ++){
			cbType.addItem(strType[i]);
		}
		cbType.addKeyListener(keyListener);
		
		String[] strTime = {"活期（空）","三个月", "六个月", "一年", "二年", "三年", "五年"};
		cbTime = new JComboBox<String>();
		for (int i = 0; i < strTime.length; i++) {
			cbTime.addItem(strTime[i]);
		}
		cbType.addKeyListener(keyListener);
		
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
		
		lbTitle.setText("存款");
		lbTitle.addKeyListener(keyListener);
		btnOK.setText("存入");
		btnOK.addKeyListener(keyListener);
		
		AddInputComponent(lbAccountNo, 0, 0, 8, 1);
		AddInputComponent(textAccountNo, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbType, 0, 1, 8, 1);
		AddInputComponent(cbType, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbAmount, 0, 2, 8, 1);
		AddInputComponent(textAmount, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbTime, 0, 3, 8, 1);
		AddInputComponent(cbTime, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK, 0, 4, 8, 1);
		
		
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

		if(cbType.getSelectedIndex() == 0)
		{
			live();
			return;
		}else{
			noLive(textAccountNo.getText());
		}

	}

	private void live(){
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		Transaction Trans = new Transaction("100054");

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

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}

	private void noLive(String ACCTNO)
	{
		boolean bRet;
		Transaction Trans = new Transaction("100058");
		HashMap<String,String> kdata = new HashMap<>();

		kdata.put("ACCTNO",ACCTNO);

		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(kdata);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}
		ShowStatusMessage(Trans.GetStatusMsg());
		String MAXSUB = String.format("%04d", Integer.parseInt(Trans.GetResponseValue("MAXSUB")) + 1);
		Trans.Release();



		Trans = new Transaction("100090");

		kdata.put("SUBID",MAXSUB);
		kdata.put("CRDATE",new SimpleDateFormat("yyyymmdd").format(new Date()));
		kdata.put("JISHU","000000000000");
		kdata.put("SATYPE","2");
		kdata.put("BALANC","000000000000");
		kdata.put("RATE","000000000000");
		kdata.put("EXTIME","");
		kdata.put("OPRATE","");


		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(kdata);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}
		ShowStatusMessage(Trans.GetStatusMsg());
		Trans.Release();

		Trans = new Transaction("100057");

		kdata.clear();
		kdata.put("ACCTNO",ACCTNO);
		kdata.put("MAXSUB",MAXSUB);

		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(kdata);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}
		ShowStatusMessage(Trans.GetStatusMsg());
		Trans.Release();



	}
}
