package messageclient.controller;

import javax.swing.JOptionPane;

import messageclient.model.entity.Message;
import messageclient.model.service.TextService;
import messageclient.view.MainWindow;

@ActionName(value="sendText")
public class ActionSendText implements Action
{
	TextService ts;
	public ActionSendText()
	{
		ts=new TextService();
	}
	@Override
	public void execute(Object p) throws Exception
	{
		Message msg=(Message)p;
		ts.sendText(msg);
	}
}
