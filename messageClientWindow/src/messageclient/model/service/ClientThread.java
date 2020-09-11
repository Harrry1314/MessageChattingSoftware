package messageclient.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import messageclient.model.entity.Message;
import messageclient.model.utils.NetSocket;
import messageclient.view.MainWindow;

public class ClientThread extends Thread
{
	public boolean done = false;
	private static ClientThread instance;
	public static ClientThread getInstance()
	{
		if(instance==null || !instance.isAlive())
		{
			instance=new ClientThread();
		}
		return instance;
	}
	private ClientThread()
	{
		
	}
	@Override
	public void run()
	{
		MessageProcessor processor;
		while (done == false)
		{
			try
			{
				String m;
				m = NetSocket.getInstance().receiveMsg();
				Message msg = Message.encode(m);
				System.out.println(msg);
				MessageHandler handler;
				handler = new MessageHandler();
				handler.handle(msg);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}
