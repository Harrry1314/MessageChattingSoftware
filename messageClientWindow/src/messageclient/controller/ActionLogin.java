package messageclient.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import messageclient.model.service.ClientThread;
import messageclient.model.service.UserService;
import messageclient.model.utils.NetSocket;
import messageclient.view.MainWindow;

@ActionName(value="login")
public class ActionLogin implements Action
{
	UserService us;
	public ActionLogin()
	{
		us=new UserService();
	}
	@Override
	public void execute(Object p) throws Exception
	{
		String username=(String)p;
		us.login(username);
	}
	
}
