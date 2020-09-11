import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientThread extends Thread
{
	BufferedReader reader;
	public boolean done=false;
	
	public ClientThread(BufferedReader reader)
	{
		this.reader=reader;
	}
	
	@Override
	public void run()
	{
		while(done==false)
		{
			try
			{
				String msg=reader.readLine();
				//System.out.println(msg);
				String[] str=msg.split(",");
				if(str[2].equals("username"))
				{
					String[] usernames=str[3].split("-");
//					MainWindow main=MainWindow.getInstance();
//					main.updateUsernames(usernames);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
