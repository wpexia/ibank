package gui;


import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class iBankGui extends JFrame
{
	private static final long serialVersionUID = 8621022770500719351L;

	protected JFrame parentFrame;
	
	protected JPanel contentPane;
	protected JPanel inputPane;
	protected JPanel outputPane;
	protected JPanel statusPane;
	
	protected JPanel		  headPane;
	protected JPanel		  InOutPane;


	protected BorderLayout       blContent;
	
	protected GridBagLayout      gblInput;
	protected GridBagConstraints gbcInput;
	
	protected GridBagLayout      gblOutput;
	protected GridBagConstraints gbcOutput;
	
	protected GridBagLayout      gblStatus;
	protected GridBagConstraints gbcStatus;
	
	protected JLabel lbTitle;
	protected JLabel lbStatus;
	protected JScrollPane    scrollStatus;
	protected JList<String>    taStatus;
	protected DefaultListModel<String> dlmStatus;
	
	protected JButton btnOK;
	
	protected KeyboardFocusManager manager;
	protected KeyEventPostProcessor postProcessor;
	public KeyListener keyListener;
	protected boolean listenFlag = false;
	
	public iBankGui(JFrame parent)
	{
		parentFrame =  parent;
		
		//setBounds(100, 100, 400, 320);
		setTitle("iBank GUI Title: Replace me");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		blContent = new BorderLayout();
		contentPane.setLayout(blContent);
		
		
		inputPane  = new JPanel();
		outputPane = new JPanel();
		statusPane = new JPanel();
		
		InOutPane   = new JPanel();
		headPane    = new JPanel();
		
		lbTitle = new JLabel("Replace me");
		SetFont(lbTitle, 24);
		
		headPane.add(lbTitle);
		
		contentPane.add(headPane,    BorderLayout.NORTH);	
		contentPane.add(InOutPane,   BorderLayout.CENTER);
		contentPane.add(statusPane,  BorderLayout.SOUTH);	
		
		GridLayout  glInOut = new GridLayout(2, 1);
		InOutPane.setLayout(glInOut);
		
		InOutPane.add(inputPane);
		InOutPane.add(outputPane);
		
		
		// input panel
		btnOK  = new JButton("ReplaceMe");
		SetFont(btnOK);
		
		gblInput        = new GridBagLayout();
		inputPane.setLayout(gblInput);
		
		gbcInput         = new GridBagConstraints();
		gbcInput.fill    = GridBagConstraints.NONE;
		gbcInput.anchor  = GridBagConstraints.NORTHWEST;
		gbcInput.weightx = 0;
		gbcInput.weighty = 0;
		gbcInput.insets  = new Insets(0, 0, 20, 10);
		
	
		
		// output panel;
		gblOutput        = new GridBagLayout();
		outputPane.setLayout(gblOutput);
		
		gbcOutput         = new GridBagConstraints();
		gbcOutput.fill    = GridBagConstraints.NONE;
		gbcOutput.anchor  = GridBagConstraints.NORTHWEST;
		gbcOutput.weightx = 0;
		gbcOutput.weighty = 0;
		gbcOutput.insets  = new Insets(0, 0, 20, 10);
			
		
		// status panel
		gblStatus        = new GridBagLayout();
		statusPane.setLayout(gblStatus);
		
		gbcStatus        = new GridBagConstraints();
		gbcStatus.fill   = GridBagConstraints.BOTH;
		gbcStatus.anchor = GridBagConstraints.NORTHWEST;
		gbcStatus.weightx = 1.0;
		gbcStatus.weighty = 1.0;
		
		
		lbStatus         = new JLabel("状态信息：");
		
		taStatus         = new JList<String>();
		scrollStatus     = new JScrollPane(taStatus);
		dlmStatus        = new DefaultListModel<String>();
		taStatus.setModel(dlmStatus);
		taStatus.setBackground(Color.black);
		taStatus.setForeground(Color.green);
		
		//statusPane.add(scrollStatus);
		
		AddStatusComponent(scrollStatus, 0, 1, GridBagConstraints.REMAINDER, 1);
		
		
		btnOK.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent Event)
			{
				TransactionAction();
			}
		}
		);

		keyListener = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if(!listenFlag)
					myAddKeylistener();
			}

			@Override
			public void keyPressed(KeyEvent e)
			{

			}

			@Override
			public void keyReleased(KeyEvent e)
			{

			}
		};

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				returnMain();
			}
		});
	}
	public void Display()
	{
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	protected void returnMain()
	{
		if(listenFlag)
		{
			manager.removeKeyEventPostProcessor(postProcessor);
			listenFlag = false;
		}
		dispose();
		setVisible(false);
		parentFrame.setVisible(true);
		parentFrame.pack();
	}

	protected void myAddKeylistener()
	{
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		listenFlag = true;
		manager.addKeyEventPostProcessor(postProcessor = new KeyEventPostProcessor()
				{
					@Override
					public boolean postProcessKeyEvent(KeyEvent e)
					{
						if (KeyEvent.VK_ESCAPE == e.getKeyCode())
						{
							returnMain();
						}
						else if(KeyEvent.VK_ENTER == e.getKeyCode())
						{
							TransactionAction();
						}
						return true;
					}
				}
		);
	}

	protected void TransactionAction()
	{
		if(listenFlag)
		{
			manager.removeKeyEventPostProcessor(postProcessor);
			listenFlag = false;
		}
		return;
	}
	
	protected void ShowStatusMessage(String [] Msg)
	{
		for (int i = 0; i < Msg.length; ++i)
			dlmStatus.add(0, Msg[i]);
	}
	
	private static void AddComponent(JPanel pane, Component c, GridBagConstraints constraints, int x, int y, int w, int h)
	{
		constraints.gridx      = x;
		constraints.gridy      = y;
		constraints.gridwidth  = w;
		constraints.gridheight = h;
		pane.add(c, constraints);
	}
	
	protected void AddInputComponent(Component c, int x, int y, int w, int h)
	{
		AddComponent(inputPane, c, gbcInput, x, y, w, h);
	}
	
	protected void AddOutputComponent(Component c, int x, int y, int w, int h)
	{
		AddComponent(outputPane, c, gbcOutput, x, y, w, h);
	}
	
	private void AddStatusComponent(Component c, int x, int y, int w, int h)
	{
		AddComponent(statusPane, c, gbcStatus, x, y, w, h);
	}
	
	protected void OpenTransWindow(iBankGui transFrame)
	{
		transFrame.Display();
		transFrame.pack();
		setVisible(false);
	}
	
	protected void OpenTransWindow(iBankMenu transFrame)
	{
		transFrame.Display();
		transFrame.pack();
		setVisible(false);
	}
	
	protected void SetConstraintsToDefault(GridBagConstraints constraints)
	{
		
	}
	protected static void SetFont(JComponent c)
	{
		Font f = new Font("宋体", Font.PLAIN, 18); 
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
		return lable;
	}
	
}
