import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MessageClient
{
	public static void main(String[] args) throws Exception
	{
		//System.out.println("client run1");
		String fileName="ipport.txt";
		ArrayList<String> list=readFile(fileName);
		String ip=list.get(0);
		int port=Integer.parseInt(list.get(1));
		Socket s = new Socket(ip, port);
		
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.println(args[0]);// login
		pw.flush();

		//System.out.println("client run2");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		if (args[0].equals("stopserver"))
		{
			reader.close();
			pw.close();
		}
		else
		{
			//System.out.println("client run3");
			ClientThread ct = new ClientThread(reader);
			//System.out.println("client run4");
			ct.start();
			BufferedReader rin = new BufferedReader(new InputStreamReader(System.in));

			while (true)// send
			{
				System.out.println("Please input the message.");
				String msg = rin.readLine();
				pw.println(msg);
				pw.flush();

				String[] msgList = msg.split(",");
				if (msgList[1].equals("logout"))
				{
					ct.done = true;
					break;
				}
			}
			try
			{
				reader.close();
				pw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
	public static ArrayList<String> readFile(String file) throws IOException
	{
		ArrayList<String> stringList=new ArrayList<String>();

		FileInputStream fin = new FileInputStream(file);

		BufferedReader reader;
		InputStreamReader inReader;

		inReader = new InputStreamReader(fin);
		reader = new BufferedReader(inReader);
		String line = reader.readLine();
		while (line != null)
		{
			stringList.add(line);
			line = reader.readLine();
		}
		reader.close();
		return stringList;
	}
}
