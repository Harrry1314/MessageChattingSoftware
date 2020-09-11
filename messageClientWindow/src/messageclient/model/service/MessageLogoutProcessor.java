package messageclient.model.service;

import java.io.IOException;

import messageclient.model.entity.Message;
import messageclient.model.utils.NetSocket;

public class MessageLogoutProcessor extends AbstractMessageProcessor
{
	NetSocket ns=NetSocket.getInstance();
	@Override
	public void process(Message msg)
	{
		if(msg.title.equals("logout"))
		{
			ClientThread.getInstance().done=true;
			try
			{
				ns.reader.close();
				ns.writer.close();
				ns.socket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			next.process(msg);
		}
	}
	
}
