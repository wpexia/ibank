package demo.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.iBankLogon;
import ibankapi.ibankapi;


public class ibankguidemo extends iBankLogon 
{
	private static final long serialVersionUID = 1L;
	protected KeyboardFocusManager manager;
	protected KeyEventPostProcessor postProcessor;
	protected KeyListener enterListener;
	boolean listenerFlag = false;

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
				if(!listenerFlag)
					addEnter();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};
		username.addKeyListener(enterListener);
		password.addKeyListener(enterListener);
	}
	
	protected void LogonAction()
	{
		String user  = username.getText();
		char [] pass = password.getPassword();
		manager.removeKeyEventPostProcessor(postProcessor);
		listenerFlag = false;
		
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
		ibankMainMenu ibankMainMenu = new ibankMainMenu();
		ibankMainMenu.Display();
		ibankMainMenu.pack();
	}
	
	protected void ExitAction()
	{
		System.exit(0);
	}

	protected void addEnter(){
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		listenerFlag = true;
		manager.addKeyEventPostProcessor(postProcessor = new KeyEventPostProcessor() {
			@Override
			public boolean postProcessKeyEvent(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					LogonAction();
				}
				return true;
			}
		});
	}
}
