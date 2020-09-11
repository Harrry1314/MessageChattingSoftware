package messageclient.model.utils;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public interface INetSocket
{
	Socket getSocket();
	void setSocket(Socket socket);
	BufferedReader getReader();
	void setReader(BufferedReader reader);
	PrintWriter getWriter();
	void setWriter(PrintWriter writer);
	
	void connect(String ip, int port) throws Exception;
	void close() throws Exception;
	void sendMsg(String msg) throws Exception;
	String receiveMsg() throws Exception;
}
