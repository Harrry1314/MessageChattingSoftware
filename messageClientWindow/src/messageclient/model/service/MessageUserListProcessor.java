package messageclient.model.service;

import messageclient.model.entity.Message;
import messageclient.view.MainWindow;

public class MessageUserListProcessor extends AbstractMessageProcessor
{

	@Override
	public void process(Message msg)
	{
		if(msg.title.equals("usernames"))
		{
			String[] usernames=msg.body.split("-");
			MainWindow main=MainWindow.getInstance();
			main.updateUsernames(usernames);
		}
		else
		{
			next.process(msg);
		}
	}
	
}
