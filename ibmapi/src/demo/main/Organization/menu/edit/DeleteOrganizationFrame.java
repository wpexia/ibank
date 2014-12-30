package demo.main.organization.menu.edit;

		import gui.iBankGui;

		import javax.swing.*;
		import java.util.HashMap;

public class DeleteOrganizationFrame extends iBankGui
{
	private HashMap<String, String> mData;

	public DeleteOrganizationFrame(JFrame parent, HashMap<String, String> data)
	{
		super(parent);
		mData = data;

		setTitle("Delete Organization");

		JLabel  lbConfirm = CreateLable("确定删除？");

		lbTitle.setText("删除机构");
		btnOK.setText("删除");
		btnOK.addKeyListener(keyListener);

		AddInputComponent(lbConfirm, 0, 0, 8, 1);
		AddInputComponent(btnOK, 0, 1, 8, 1);
	}
}
