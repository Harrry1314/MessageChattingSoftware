package messageclient.controller;

import messageclient.model.entity.Message;
import messageclient.model.service.FileService;

@ActionName(value="sendFile")
public class ActionSendFile implements Action
{
	FileService fs;
	public ActionSendFile()
	{
		fs=new FileService();
	}
	@Override
	public void execute(Object p) throws Exception
	{
		Message msg=fs.transform((Object[])p);
		fs.sendFile(msg);
	}

}
