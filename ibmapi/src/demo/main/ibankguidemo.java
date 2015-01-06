package demo.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import gui.iBankLogon;
import ibankapi.Transaction;
import ibankapi.ibankapi;


public class ibankguidemo extends iBankLogon 
{
	private static final long serialVersionUID = 1L;
	protected KeyListener enterListener;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ibankguidemo frame = new ibankguidemo();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ibankguidemo()
	{
		setTitle("iBank Demo");
		enterListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					LogonAction();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};
		textOrgID.addKeyListener(enterListener);
		username.addKeyListener(enterListener);
		password.addKeyListener(enterListener);
	}
	
	protected void LogonAction()
	{
		ibankapi.Init("1");
		String orgID = textOrgID.getText();
		String user  = username.getText();
		char [] pass = password.getPassword();
		
		if(orgID.isEmpty()){
			JOptionPane.showMessageDialog(null, "请输入机构号", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (user.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "请输入用户名", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (pass.length == 0)
		{
			JOptionPane.showMessageDialog(null, "请输入口令", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean bRet;
		HashMap<String, String> data = new HashMap<String, String>();

		Transaction Trans = new Transaction("100063");
		
		data.put("ORGID", orgID);
		data.put("OPID", user);

		bRet = Trans.Init();


		if(!bRet){
			String retMsg = "";
			for (int i = 0; i < Trans.GetStatusMsg().length; ++i){
				retMsg += Trans.GetStatusMsg()[i];
				retMsg += "\n";
			}
			JOptionPane.showMessageDialog(null, retMsg, "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		bRet = Trans.SendMessage(data);
		if(!bRet || !Trans.GetStatus()){
			String retMsg = "";
			for (int i = 0; i < Trans.GetStatusMsg().length; ++i){
				retMsg += Trans.GetStatusMsg()[i];
				retMsg += "\n";
			}
			JOptionPane.showMessageDialog(null, retMsg, "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String[] tmp = {"NAME1", "GENDER", "PASSWD", "CONNEC", "TYPE", "AUTH"};
		for(String x: tmp){
			data.put(x, Trans.GetResponseValue(x));
			System.out.println(x + "  " +  Trans.GetResponseValue(x));
		}
		Trans.Release();

		if(!(data.get("PASSWD").equals(String.copyValueOf(pass)))){
			JOptionPane.showMessageDialog(null, "口令与用户名不匹配", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		dispose();
		ibankapi.Init(user);
		if(data.get("TYPE").equals("1")){
			ibankMainMenu ibankMainMenu = new ibankMainMenu();
			ibankMainMenu.Display();
			ibankMainMenu.pack();
		}
		else{
			ibankLMainMenu ibankLMainMenu = new ibankLMainMenu();
			ibankLMainMenu.Display();
			ibankLMainMenu.pack();
		}
		
	}
	
	protected void ExitAction()
	{
		System.exit(0);
	}

}
