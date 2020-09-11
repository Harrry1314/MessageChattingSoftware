package messageclient.model.service;

import javax.swing.Icon;

import messageclient.model.entity.Message;
import messageclient.model.utils.ImageUtil;
import messageclient.view.MainWindow;

public class MessageImageProcessor extends AbstractMessageProcessor
{
	FileService fs;
	public MessageImageProcessor()
	{
		fs=new FileService();
	}
	@Override
	public void process(Message msg)
	{
		if(msg.title.equals("image"))
		{
			try
			{
				MainWindow main=MainWindow.getInstance();
				String fileName=fs.save(msg.body);
				Icon icon=ImageUtil.createIcon(fileName);
				main.updateImageMessage(msg.from, icon, fileName);
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
