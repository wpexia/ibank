package demo;

import ibankapi.Transaction;

import java.util.HashMap;

import gui.iBankMQ;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MQDemo extends iBankMQ
{
	private static final long serialVersionUID = 9063166698820167775L;

	public MQDemo(JFrame parent, String transId, int row)
	{
		super(parent, transId, row);
		setTitle("iBank MQ Demo");
		lbTitle.setText("日志查询");
	}
	
	protected void TransactionAction()
	{
		super.TransactionAction();
		
		// check input
		if (queryDate.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请输入查询日期", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
			
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();
		
		MQTrans = new Transaction(TransID);
		data.put("TRSDTE",  queryDate.getText());

		
		bRet = MQTrans.Init();
		
		if (!bRet)
		{
			ShowStatusMessage(MQTrans.GetStatusMsg());
			return;
		}
		
		bRet = MQTrans.SendMessage(data);
		if (!bRet)
		{
			ShowStatusMessage(MQTrans.GetStatusMsg());
			return;
		}
		
		String strRecords  = MQTrans.GetResponseValue("OA_NUMBER");
	
		recordNum = Integer.parseInt(strRecords);
		
		ShowStatusMessage(MQTrans.GetStatusMsg());
		MQTrans.Release();
		
		NextPageAction();
		
			
	}

}
