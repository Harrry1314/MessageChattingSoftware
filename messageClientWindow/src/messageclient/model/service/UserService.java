package messageclient.model.service;

import java.io.IOException;
import java.net.UnknownHostException;

import messageclient.model.entity.Message;
import messageclient.model.utils.Crypto;
import messageclient.model.utils.INetSocket;
import messageclient.model.utils.NetSocket;
import messageclient.model.utils.NetSocketCryptoProxy;

public class UserService
{
	public static String me;
	INetSocket socket;
	public UserService()
	{
		socket=NetSocketCryptoProxy.getInstance();
	}
	public void login(String username) throws Exception
	{
		String ip=SettingService.getInstance().ip;
		int port=SettingService.getInstance().port;
		socket.connect(ip, port);
		
		Crypto.generateKey();
		String base64Pubkey=Crypto.getMyPubkey();
		NetSocket.getInstance().sendMsg(base64Pubkey);
		String peerPubkey=NetSocket.getInstance().receiveMsg();
		Crypto.setPeerPubkey(peerPubkey);
		
		Message msg=new Message();
		msg.from=username;
		msg.title="login";
		socket.sendMsg(msg.decode());
		me=username;
		ClientThread ct=ClientThread.getInstance();
		ct.start();
	}
	public void logout() throws Exception
	{
		Message msg=new Message();
		msg.from=me;
		msg.title="logout";
		socket.sendMsg(msg.decode());
	}
}
