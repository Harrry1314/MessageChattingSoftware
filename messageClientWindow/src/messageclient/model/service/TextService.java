package messageclient.model.service;

import messageclient.model.entity.Message;
import messageclient.model.utils.INetSocket;
import messageclient.model.utils.NetSocket;
import messageclient.model.utils.NetSocketCryptoProxy;

public class TextService
{
	INetSocket socket;
	public TextService()
	{
		socket=NetSocketCryptoProxy.getInstance();
	}
	public void sendText(Message msg) throws Exception
	{
		socket.sendMsg(msg.decode());
	}
}
