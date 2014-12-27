package demo;

import javax.swing.*;
import java.awt.*;

import gui.iBankLogon;
import ibankapi.ibankapi;


public class ibankguidemo extends iBankLogon 
{
	private static final long serialVersionUID = 1L;

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
	}
	
	protected void LogonAction()
	{
		String user  = username.getText();
		char [] pass = password.getPassword();
		
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
		
		dispose();
		ibankapi.Init(user);
		ibankMain ibankMain = new ibankMain();
		ibankMain.Display();
		ibankMain.pack();
	}
	
	protected void ExitAction()
	{
		System.exit(0);
	}
}
