

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class Communication extends Thread
{
	public Map<String,Communication> map;
	public BufferedReader reader;
	public PrintWriter writer;
	public Socket socket;
	public RSAPublicKey peerPubkey;
	public Communication(BufferedReader reader,PrintWriter writer,Map<String,Communication> map,Socket socket, RSAPublicKey peerPubkey)
	{
		this.reader=reader;
		this.writer=writer;
		this.map=map;
		this.socket=socket;
		this.peerPubkey=peerPubkey;
	}
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				String msg=reader.readLine();//Message format is "From,To,Title,Body"
				msg=Crypto.decrypt(msg);
				String[] elements=MessageServer.split(msg);
				String from=elements[0];
				String to=elements[1];
				String title=elements[2];
				String body=elements[3];
				if(title.equals("logout"))
				{
					System.out.println(from+" logout");
					Crypto.peerPubkey=this.peerPubkey;
					msg=Crypto.encrypt(msg);
					writer.println(msg);
					writer.flush();
					synchronized(map)
					{
						map.remove(from);
						MessageServer.sendUserList(map);
						break;
					}
				}
				else
				{
					Communication c;
					synchronized(map)
					{
						c=map.get(to);
					}
					if(c!=null)
					{
						Crypto.peerPubkey=c.peerPubkey;
						msg=Crypto.encrypt(msg);
						c.writer.println(msg);
						c.writer.flush();
					}
				}
			}
			catch(Exception err)
			{
				err.printStackTrace();
			}
		}
		try
		{
			reader.close();
			writer.close();
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
