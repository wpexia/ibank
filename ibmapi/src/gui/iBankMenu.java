package gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ibankapi.SysConfig;



public class iBankMenu extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	protected JPanel contentPane;
	
	protected JLabel         lbTitle;
	
	protected JLabel         lbOprDisplay;
	protected JLabel         lbOprData;
	protected JLabel		 lbDateDisplay;
	protected JLabel		 lbDate;
		 
	protected JLabel         lbExit;
	protected JLabel         lbSelect;
	protected JLabel         lbPrompt;
	
	protected JTextField 	 textInput;
	
	
	protected BorderLayout    blContent;
	protected JPanel		  headPane;
	protected JPanel		  MenuPane;
	protected JPanel		  InputPane;
	
	protected GridLayout      glHead;
	protected GridLayout      glMenu;
	protected GridLayout      glInput;
	
	public iBankMenu()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("iBank Demo: Replace me");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.black);
		
		blContent	= new BorderLayout();
		contentPane.setLayout(blContent);
		
		headPane = new JPanel();
		headPane.setBackground(Color.black);
		
		MenuPane = new JPanel();
		MenuPane.setBackground(Color.black);
		
		InputPane = new JPanel();
		InputPane.setBackground(Color.black);
		
		glHead  = new GridLayout(0, 7, 2, 3);
		glMenu  = new GridLayout(0, 3);
		glInput = new GridLayout(0, 7, 2, 3);
		
		headPane.setLayout(glHead);
		MenuPane.setLayout(glMenu);
		//InputPane.setLayout(glInput);
		
		
		JPanel westPane   = new JPanel();
		westPane.setBackground(Color.black);
		JPanel eastPane   = new JPanel();
		eastPane.setBackground(Color.black);
		
		
		contentPane.add(headPane,   BorderLayout.NORTH);	
		contentPane.add(MenuPane,   BorderLayout.CENTER);
		contentPane.add(InputPane,  BorderLayout.SOUTH);	
		
		contentPane.add(westPane,   BorderLayout.WEST);	
		contentPane.add(eastPane,   BorderLayout.EAST);	
		
		
		lbTitle	            = CreateLable("iBank 业务菜单");
		lbOprDisplay        = CreateLable("操作员:");
		lbOprData           = CreateLable(SysConfig.GetOprID());
		lbDateDisplay       = CreateLable("系统日期:");
		lbDate              = CreateLable(SysConfig.GetServerDate());
		lbExit              = CreateLable("90. 退出");
		lbSelect            = CreateLable("选择：");
		lbPrompt            = CreateLable("===>");
		
		textInput = new JTextField();
		textInput.setColumns(4);
		textInput.setBackground(Color.black);
		textInput.setForeground(Color.green);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		textInput.setBorder(emptyBorder);
		SetFont(textInput);
		textInput.setCaretColor(Color.green);
		
		AddComponent(headPane, lbTitle, 3, 7);
		
	
		headPane.add(lbOprDisplay);
		headPane.add(lbOprData);
		AddBlank(headPane, 3);
		headPane.add(lbDateDisplay);
		headPane.add(lbDate);
		
		/*
		AddComponent(InputPane, lbSelect, 1, 7);
		AddBlank(InputPane, 1);
		InputPane.add(lbPrompt);
		InputPane.add(textInput);
		AddBlank(InputPane, 4);	
		// */
		InputPane.add(lbSelect);
		InputPane.add(lbPrompt);
		InputPane.add(textInput);
		
		textInput.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent Event)
			{
				OpenTransFrame(textInput.getText());
				textInput.setText("");
			}
		}
		);
	}
	
	protected void AddMenuItem(JLabel menuItem)
	{
		AddComponent(MenuPane, menuItem, 1, 3);
	}
	
	protected void OpenTransFrame(String menuItem)
	{
		return;
	}
	
	protected void OpenTransWindow(iBankGui transFrame)
	{
		transFrame.Display();
		transFrame.pack();
		setVisible(false);
	}
	

	public void Display()
	{
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void Hidden()
	{
		setVisible(false);
	}
	
	protected static void SetFont(JComponent c)
	{
		Font f = new Font("宋体", Font.PLAIN, 24); 
		c.setFont(f);
	}
	
	protected static void SetFont(JComponent c, int size)
	{
		Font f = new Font("宋体", Font.PLAIN, size); 
		c.setFont(f);
	}
	
	protected static JLabel CreateLable(String text)
	{
		JLabel lable = new JLabel();
		SetFont(lable);
		lable.setText(text);
		lable.setForeground(Color.green);
		
		return lable;
	}
	
	protected static void AddComponent(JPanel pane, Component c, int positionColumn, int Columns)
	{
		for (int i = 0; i < positionColumn; ++i)
		{
			JPanel paneblank = new JPanel();
			paneblank.setBackground(Color.black);
			pane.add(paneblank);
		}
		/*
		JPanel paneItem = new JPanel();
		paneItem.setBackground(Color.black);
		paneItem.add(c);
		pane.add(paneItem);
		// */
		pane.add(c);
		for (int i = positionColumn + 1; i < Columns; ++i)
		{
			JPanel paneblank = new JPanel();
			paneblank.setBackground(Color.black);
			pane.add(paneblank);
		}
	}
	

	protected static void AddBlank(JPanel pane, int Number)
	{
		for (int i = 0; i < Number; ++i)
		{
			JPanel paneblank = new JPanel();
			paneblank.setBackground(Color.black);
			pane.add(paneblank);
		}
	}

}
