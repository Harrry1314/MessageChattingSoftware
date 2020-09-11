package messageclient.model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetSocket implements INetSocket
{
	public Socket socket;
	public BufferedReader reader;
	public PrintWriter writer;
	private static NetSocket instance=new NetSocket();
	public static NetSocket getInstance()
	{
		return instance;
	}
	private NetSocket()
	{
	}
	public void connect(String ip,int port) throws UnknownHostException, IOException
	{
		socket=new Socket(ip,port);
		reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer=new PrintWriter(socket.getOutputStream());
	}
	public void close() throws IOException
	{
		reader.close();
		writer.close();
		socket.close();
	}
	public void sendMsg(String msg)
	{
		writer.println(msg);
		writer.flush();
	}
	public String receiveMsg() throws IOException
	{
		String msg=reader.readLine();
		return msg;
	}
	@Override
	public Socket getSocket()
	{
		return socket;
	}
	@Override
	public void setSocket(Socket socket)
	{
		this.socket=socket;
	}
	@Override
	public BufferedReader getReader()
	{
		return reader;
	}
	@Override
	public void setReader(BufferedReader reader)
	{
		this.reader=reader;
	}
	@Override
	public PrintWriter getWriter()
	{
		return writer;
	}
	@Override
	public void setWriter(PrintWriter writer)
	{
		this.writer=writer;
	}
}
