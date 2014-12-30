package demo.main.account.list.detail.edit.subdetail;

import java.awt.GridBagConstraints;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.iBankGui;

public class ShowSubAccountDetailFrame extends iBankGui{

	private HashMap<String, String>data;
	
	private static final long serialVersionUID = 9063166698820167875L;
	private JTextField textAccountId;
	private JTextField textSubId;
	private JTextField textDate;
	private JTextField textJiShu;
	private JTextField textType;
	private JTextField textBalance;
	private JTextField textRate;
	private JTextField textExDate;
	private JTextField textOprate;
	public ShowSubAccountDetailFrame(JFrame parent, HashMap<String, String>mData) {
		super(parent);
		data = mData;
		
		setTitle("iBank Sub Account Detail");
		
		JLabel lbAccountId = CreateLable("账户号");
		JLabel lbSubId = CreateLable("子账户号");
		JLabel lbDate = CreateLable("创建日期");
		JLabel lbJiShu = CreateLable("积数");
		JLabel lbType = CreateLable("子账号类型");
		JLabel lbBalance = CreateLable("余额");
		JLabel lbRate = CreateLable("执行汇率");
		JLabel lbExDate = CreateLable("预计到期时间");
		JLabel lbOprate = CreateLable("到期操作");
		
		textAccountId = setText(textAccountId, "ACCTNO");
		textBalance = setText(textBalance, "BALANCE");
		textDate = setText(textDate, "CRDATE");
		textExDate = setText(textExDate, "EXTIME");
		textJiShu = setText(textJiShu, "JISHU");
		textOprate = setText(textOprate, "OPRATE");
		textRate = setText(textRate, "RATE");
		textSubId = setText(textSubId, "SUBID");
		textType = setText(textType, "SATYPE");
		
		lbTitle.setText("子账户信息");
		
		AddInputComponent(lbAccountId, 0, 0, 8, 1);
		AddInputComponent(textAccountId, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbSubId, 0, 1, 8, 1);
		AddInputComponent(textSubId, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbDate, 0, 2, 8, 1);
		AddInputComponent(textDate, 8, 2, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbJiShu, 0, 3, 8, 1);
		AddInputComponent(textJiShu, 8, 3, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbType, 0, 4, 8, 1);
		AddInputComponent(textType, 8, 4, GridBagConstraints.RELATIVE, 1);
		AddInputComponent(lbBalance, 0, 5, 8, 1);
		AddInputComponent(textBalance, 8, 5, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbRate, 0, 0, 8, 1);
		AddOutputComponent(textRate, 8, 0, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbExDate, 0, 1, 8, 1);
		AddOutputComponent(textExDate, 8, 1, GridBagConstraints.RELATIVE, 1);
		AddOutputComponent(lbOprate, 0, 2, 8, 1);
		AddOutputComponent(textOprate, 8, 2, GridBagConstraints.RELATIVE, 1);
		
		
	}
	private JTextField setText(JTextField textAccountId2, String name) {
		textAccountId2 = new JTextField();
		textAccountId2.setColumns(15);
		textAccountId2.addKeyListener(keyListener);
		textAccountId2.setText(data.get(name));
		SetFont(textAccountId2);
		textAccountId2.setEditable(false);
		return textAccountId2;
		
	}

}
