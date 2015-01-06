package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ibankapi.SysConfig;

public class iBankLogon extends JFrame
{
	private static final long serialVersionUID = 1L;

	protected JPanel contentPane;
	
	protected JLabel         lbOrganization;
	protected JLabel         lbUser;  
	protected JLabel         lbPass;
	protected JTextField     textOrgID; 
	protected JTextField     username;
	protected JPasswordField password;
	protected JButton        btnLogin; 
	protected JButton        btnClose; 
	
	protected GridBagLayout 		gblInput;
	protected GridBagConstraints    gbcInput;
	
	
	
	public iBankLogon()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 400, 320);
		setTitle("iBank Demo Replace me");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
				
		gblInput = new GridBagLayout();
		gbcInput = new GridBagConstraints();
		gbcInput.fill   = GridBagConstraints.NONE;
		gbcInput.anchor = GridBagConstraints.EAST;
		gbcInput.insets = new Insets(0, 0, 20, 10);
		
		contentPane.setLayout(gblInput);
		
		lbOrganization = new JLabel("机构号：");
		lbUser   = new JLabel("用户名：");
		lbPass   = new JLabel("口令：");
		
		textOrgID    = new JTextField();
		textOrgID.setColumns(8);
		username = new JTextField();
		username.setColumns(8);
		password = new JPasswordField();
		password.setColumns(8);
		
		btnLogin  = new JButton("登录");
		btnClose  = new JButton("退出");
		
		AddComponent(contentPane, lbOrganization, gbcInput, 0, 0, 3, 1);
		AddComponent(contentPane, textOrgID,       gbcInput, 4, 0, GridBagConstraints.REMAINDER, 1);
		AddComponent(contentPane, lbUser,      gbcInput, 0,  1, 3, 1);
		AddComponent(contentPane, username,    gbcInput, 4,  1, GridBagConstraints.REMAINDER, 1);
		AddComponent(contentPane, lbPass,      gbcInput, 0,  2, 3, 1);
		AddComponent(contentPane, password,    gbcInput, 4,  2, GridBagConstraints.REMAINDER, 1);
		AddComponent(contentPane, btnLogin,    gbcInput, 0,  3, 3, 1);
		AddComponent(contentPane, btnClose,    gbcInput, 4,  3, GridBagConstraints.REMAINDER, 1);
		
		
		btnLogin.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent Event)
			{
				LogonAction();
			}
		}
		);
		
		btnClose.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent Event)
			{
				ExitAction();
			}
		}
		);
		
	}
	
	protected void LogonAction()
	{
		return;
	}
	
	protected void ExitAction()
	{
		return;
	}
	
	
	protected static void AddComponent(JPanel pane, Component c, GridBagConstraints constraints, int x, int y, int w, int h)
	{
		constraints.gridx      = GridBagConstraints.RELATIVE;
		constraints.gridy      = GridBagConstraints.RELATIVE;
		constraints.gridwidth  = w;
		constraints.gridheight = h;
		pane.add(c, constraints);
	}
	
}
