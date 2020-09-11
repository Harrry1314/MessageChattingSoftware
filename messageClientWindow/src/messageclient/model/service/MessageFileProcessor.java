package messageclient.model.service;


import messageclient.model.entity.Message;
import messageclient.view.MainWindow;

public class MessageFileProcessor extends AbstractMessageProcessor
{
	FileService fs;
	public MessageFileProcessor()
	{
		fs=new FileService();
	}
	@Override
	public void process(Message msg)
	{
		if(msg.title.equals("file"))
		{
			try
			{
				MainWindow main=MainWindow.getInstance();
				String fileName=fs.save(msg.body);
				main.updateFileMessage(msg.from, fileName);
				
			}
			catch(Exception e)
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
