package messageclient.model.service;

import messageclient.model.entity.Message;
import messageclient.view.MainWindow;

public class MessageTextProcessor extends AbstractMessageProcessor
{

	@Override
	public void process(Message msg)
	{
		if(msg.title.equals("text"))
		{
			MainWindow main=MainWindow.getInstance();
			main.updateTextMessage(msg);
		}
		else
		{
			next.process(msg);
		}
	}

}
