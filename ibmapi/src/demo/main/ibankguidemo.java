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
		
		Transaction trans = new Transaction("100063");
		
		data.put("ORGID", orgID);
		data.put("OPID", user);
		bRet = trans.Init();
		
		if(!bRet){
			String retMsg = "";
			for (int i = 0; i < trans.GetStatusMsg().length; ++i){
				retMsg += trans.GetStatusMsg()[i];
				retMsg += "\n";
			}
			JOptionPane.showMessageDialog(null, retMsg, "错误", JOptionPane.ERROR_MESSAGE);
			return;	
		}
		
		bRet = trans.SendMessage(data);
		if(!bRet){
			String retMsg = "";
			for (int i = 0; i < trans.GetStatusMsg().length; ++i){
				retMsg += trans.GetStatusMsg()[i];
				retMsg += "\n";
			}
			JOptionPane.showMessageDialog(null, retMsg, "错误", JOptionPane.ERROR_MESSAGE);
			return;	
		}
		
		String[] tmp = {"NAME", "GENDER", "PASWD", "CONNEC", "TYPE", "AUTH"};
		for(String x: tmp){
			data.put(x, trans.GetResponseValue(x));
		}
		trans.Release();
		
		if(!(data.get("PASSWD").equals(pass.toString()))){
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
