package messageclient.model.utils;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetSocketCryptoProxy implements INetSocket
{
	private INetSocket netSocket;
	
	private static NetSocketCryptoProxy instance=new NetSocketCryptoProxy();
	public static NetSocketCryptoProxy getInstance()
	{
		return instance;
	}
	private NetSocketCryptoProxy()
	{
		netSocket=NetSocket.getInstance();
	}
	@Override
	public void connect(String ip, int port) throws Exception
	{
		netSocket.connect(ip, port);
	}
	@Override
	public Socket getSocket()
	{
		return netSocket.getSocket();
	}
	@Override
	public void setSocket(Socket socket)
	{
		netSocket.setSocket(socket);
	}
	@Override
	public BufferedReader getReader()
	{
		return netSocket.getReader();
	}
	@Override
	public void setReader(BufferedReader reader)
	{
		netSocket.setReader(reader);
	}
	@Override
	public PrintWriter getWriter()
	{
		return netSocket.getWriter();
	}
	@Override
	public void setWriter(PrintWriter writer)
	{
		netSocket.setWriter(writer);
	}
	@Override
	public void close() throws Exception
	{
		netSocket.close();
	}
	@Override
	public void sendMsg(String msg) throws Exception
	{
		msg=Crypto.encrypt(msg);
		netSocket.sendMsg(msg);
	}
	@Override
	public String receiveMsg() throws Exception
	{
		String res=netSocket.receiveMsg();
		res=Crypto.decrypt(res);
		return res;
	}
}
