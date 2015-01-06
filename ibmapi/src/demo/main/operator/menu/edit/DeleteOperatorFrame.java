package demo.main.operator.menu.edit;

import gui.iBankGui;
import ibankapi.Transaction;

import javax.swing.*;
import java.util.HashMap;

public class DeleteOperatorFrame extends iBankGui
{
	private HashMap<String, String> mData;

	public DeleteOperatorFrame(JFrame parent, HashMap<String, String> data)
	{
		super(parent);

		mData = data;
		setTitle("Delete Operator");

		JLabel  lbConfirm = CreateLable("确定删除？");

		lbTitle.setText("删除操作员");
		btnOK.setText("删除");
		btnOK.addKeyListener(keyListener);

		AddInputComponent(lbConfirm, 0, 0, 8, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);
	}
	protected void TransactionAction()
	{
		super.TransactionAction();

		boolean bRet;
		HashMap<String, String> kdata = new HashMap<String, String>();

		Transaction Trans = new Transaction("100065");


		kdata.put("ORGID",mData.get("ORGID"));
		kdata.put("OPID",mData.get("OPID"));

		bRet = Trans.Init();

		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		bRet = Trans.SendMessage(kdata);
		if (!bRet) {
			ShowStatusMessage(Trans.GetStatusMsg());
			return;
		}

		ShowStatusMessage(Trans.GetStatusMsg());

		Trans.Release();
	}
}
