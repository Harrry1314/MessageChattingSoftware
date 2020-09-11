

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageServer
{
	public static void main(String[] args) throws Exception
	{
		Crypto.generateKey();
		Map<String,Communication> map=new HashMap<String,Communication>();
		BufferedReader reader=null;
		PrintWriter writer=null;
		ServerSocket ss=null;
		ss=new ServerSocket(3000);
		Socket socket=new Socket();
		System.out.println("Server start");
		boolean done=false;
		while(done==false)
		{
			Socket s=ss.accept();//login
			reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer=new PrintWriter(s.getOutputStream());
			
			String base64PeerPubkey=reader.readLine();
			byte[] bytesPeerPubkey=Base64.decode(base64PeerPubkey);
			ByteArrayInputStream byteIn=new ByteArrayInputStream(bytesPeerPubkey);
			ObjectInputStream objIn=new ObjectInputStream(byteIn);
			RSAPublicKey peerPubkey=(RSAPublicKey)objIn.readObject();
			objIn.close();
			writer.println(Crypto.getMyPubkey());
			writer.flush();
			
			String msg=reader.readLine();
			msg=Crypto.decrypt(msg);
			String[] strs=split(msg);
			if(strs[2].equals("login"))
			{
				System.out.println(strs[0]+" logged");
				Communication c=new Communication(reader,writer,map,socket,peerPubkey);
				synchronized(map)
				{
					map.put(strs[0],c);
					sendUserList(map);
				}
				c.start();
			}
		}
		System.out.println("Server stop");
		ss.close();
	}
	public static void sendUserList(Map<String, Communication> map) throws Exception
	{
		String msg;
		String usernames="";
		Set<String> keys=map.keySet();
		Collection<Communication> values=map.values();
		for(String username : keys)
		{
			usernames=usernames+username+"-";
		}
		msg="server,all,usernames,"+usernames;
		System.out.println(msg);
		for(Communication c : values)
		{
			Crypto.peerPubkey=c.peerPubkey;
			msg=Crypto.encrypt(msg);
			c.writer.println(msg);
			c.writer.flush();
		}
	}
	public static String[] split(String msg)
	{
		return msg.split(",");
	}
}
