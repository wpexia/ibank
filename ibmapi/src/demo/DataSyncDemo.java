package demo;

import gui.iBankGui;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import ibankapi.Transaction;

public final class DataSyncDemo extends iBankGui
{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbTableList;
	private JLabel    lbSyncNumData;



	public DataSyncDemo(JFrame parent)
	{
		super(parent);
		
		setTitle("iBank Data Sync Transation Demo");

		
		// input panel
		String [] tableNames  = {"*ALL", "DBFDZ0", "OTFDY1", "OTFDY2", "OTFDY3"};
		
		cbTableList = new JComboBox<String>();
		for (int i = 0; i < tableNames.length; ++i)
			cbTableList.addItem(tableNames[i]);
		
		JLabel lbTable   = CreateLable("同步表名：");
		
		lbTitle.setText("数据同步");
		btnOK.setText("同步");
	
		AddInputComponent(lbTable,      0, 0, 8, 1);
		AddInputComponent(cbTableList,  8, 0, 8, 1);
		AddInputComponent(btnOK,        16, 0, GridBagConstraints.REMAINDER, 1);
	
				
		// output panel;
		JLabel lbSyncNumInfo = CreateLable("同步记录数：");
		lbSyncNumData        = CreateLable("0");
		
		SetFont(cbTableList);
	
		
	
		AddOutputComponent(lbSyncNumInfo,   0, 0, 4, 1);
		AddOutputComponent(lbSyncNumData,   5, 0, GridBagConstraints.REMAINDER, 1);
	
	}
			
	protected void TransactionAction()
	{
		super.TransactionAction();
		String tableName = cbTableList.getSelectedItem().toString();
		boolean bRet;
		ArrayList<String> tableNames = new ArrayList<String>();
		if (tableName.equalsIgnoreCase("*ALL"))
		{
			String [] allTable  = {"DBFDZ0", "OTFDY1", "OTFDY2", "OTFDY3"};
			
			for (int i = 0; i < allTable.length; ++i)
				tableNames.add(allTable[i]);
		}
		else
			tableNames.add(tableName);

		int num = 0;
		
		for (int i = 0; i < tableNames.size(); ++i)
		{
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("FILENAME",  tableNames.get(i));
				
			Transaction Trans = new Transaction("900002", tableNames.get(i));
			
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
			
			bRet = Trans.SyncData();
			if (!bRet)
			{
				ShowStatusMessage(Trans.GetStatusMsg());
				return;
			}
			
			String strNum  = Trans.GetResponseValue("TOTNUM");
						

			ShowStatusMessage(Trans.GetStatusMsg());
		
										
			Trans.Release();
			
			num += Integer.parseInt(strNum);
		
		}
		
		Transaction.UpdateTransFmt();
		
		lbSyncNumData.setText(Integer.toString(num));
	
	}
	
}
