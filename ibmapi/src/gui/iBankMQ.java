package gui;

import ibankapi.Transaction;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class iBankMQ extends iBankGui
{
	private static final long serialVersionUID = 1L;
	
	protected JTextField     queryDate;
	
	protected JLabel lbRecordNumTitle;
	protected JLabel lbRecordNumValue;
	
	protected JLabel lbRowNumTitle;
	protected JLabel lbRowNumValue;
	
	protected JLabel lbPageNumTitle;
	protected JLabel lbPageNumValue;
	
	protected JLabel lbPageNoTitle;
	protected JLabel lbPageNoValue;
	
	protected JScrollPane    tableContainer;
	protected JTable         dataTable;
	
	protected JButton btnNext;
	protected JButton btnPrev;
	
	protected int rowNum    = 10;
	protected int recordNum = 0;
	
	protected int totalPage = 0;
	protected int currPage  = 0;
	protected boolean lastPage = false;
	
	
	protected String      TransID;
	protected Transaction MQTrans;
	
	
	protected Vector< Vector<String> > rowData     = null;
	protected Vector<String>           columnName  = null;
	
	public iBankMQ(JFrame parent, String transId, int row)
	{
		super(parent);
		setTitle("iBank MQ Demo, Replace me");
		TransID    = transId;
		rowNum     = row;
		
	
		// input 
		JLabel lbQueryDate   = new JLabel("查询日期：");
		
		queryDate        = new JTextField();
		queryDate.setColumns(10);
		btnOK.setText("查询");
		
		SetFont(lbQueryDate);
		SetFont(queryDate);
				
		AddInputComponent(lbQueryDate,    0,  0, 8, 1);
		AddInputComponent(queryDate,      8,  0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(btnOK,          16, 0, GridBagConstraints.RELATIVE, 1);
		
		// out put
		
		lbRecordNumTitle = CreateLable("总记录数:") ;
		lbRecordNumValue = CreateLable("0");
		
		lbRowNumTitle    = CreateLable("每页行数:");
		lbRowNumValue    = CreateLable(Integer.toString(row));
		
		lbPageNumTitle   = CreateLable("获取页数:");;
		lbPageNumValue   = CreateLable(Integer.toString(totalPage));
		
		lbPageNoTitle    = CreateLable("当前页:");
		lbPageNoValue    = CreateLable(Integer.toString(currPage));
		
		rowData     = new Vector< Vector<String> >();
		columnName  = new Vector<String>();
		
		Transaction.GetFmtTitle(columnName, transId);
		
		dataTable      = new JTable(rowData, columnName);
		dataTable.setPreferredScrollableViewportSize(new Dimension(1024, 160));
		tableContainer = new JScrollPane(dataTable);
		
		btnPrev  = new JButton("<<");
		SetFont(btnPrev);
		btnNext  = new JButton(">>");
		SetFont(btnNext);
		
		JPanel InfoPane    = new JPanel();
		JPanel ActionPane  = new JPanel();
		
		InfoPane.add(lbRecordNumTitle);
		InfoPane.add(lbRecordNumValue);
		InfoPane.add(lbRowNumTitle);
		InfoPane.add(lbRowNumValue);
		InfoPane.add(lbPageNumTitle);
		InfoPane.add(lbPageNumValue);
		InfoPane.add(lbPageNoTitle);
		InfoPane.add(lbPageNoValue);
		
		ActionPane.add(btnPrev);
		ActionPane.add(btnNext);
		
		AddOutputComponent(InfoPane,          0,  0, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(tableContainer,    0,  1, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(ActionPane,        0,  2, GridBagConstraints.RELATIVE, 1);
		
		btnPrev.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent Event)
			{
				PrevPageAction();
			}
		}
		);
		
		btnNext.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent Event)
			{
				NextPageAction();
			}
		}
		);
	}
	
	protected void Reset()
	{
		lastPage  = false;
		totalPage = 0;
		currPage  = 0;
		lastPage  = false;
		recordNum = 0;
	}
	
	protected void Refresh()
	{
		lbRecordNumValue.setText(Integer.toString(recordNum));
		lbPageNumValue.setText(Integer.toString(totalPage));
		lbPageNoValue.setText(Integer.toString(currPage));
		dataTable.updateUI();
	}
	
	protected void TransactionAction()
	{
		Reset();
	}
	
	protected void PrevPageAction()
	{
		if (totalPage <= 1 || currPage == 1)
			return;
		
		currPage--;
		rowData.removeAllElements();
		MQTrans.GetMQMessage(rowData, currPage);
		Refresh();
		
	}
	
	protected void NextPageAction()
	{
		int ret;
		
		if (recordNum <= 0)
		{
			lastPage = true;
			rowData.removeAllElements();
			Refresh();
			return;
		}
			
		if (currPage == totalPage)
		{
			if (!lastPage)
			{
				ret = MQTrans.SendMQMessage(rowNum);
				if (ret == 0)
					lastPage = true;
				totalPage++;
			}
			else
				return;
		}
			
		currPage++;
		rowData.removeAllElements();
		MQTrans.GetMQMessage(rowData, currPage);
		Refresh();
	
		return;			
	}
}
