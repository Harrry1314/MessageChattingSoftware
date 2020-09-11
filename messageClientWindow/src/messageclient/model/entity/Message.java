package messageclient.model.entity;

import messageclient.model.utils.Crypto;

public class Message
{
	public String from, to, title, body;
	public Message()
	{
		from="default";
		to="default";
		title="default";
		body="default";
	}
	public static Message encode(String msgR) throws Exception
	{
		Message msg=new Message();
		System.out.println(msgR);
		msgR=Crypto.decrypt(msgR);
		String[] strs=msgR.split(",");
		System.out.println(msgR);
		msg.from=strs[0];
		msg.to=strs[1];
		msg.title=strs[2];
		msg.body=strs[3];
		return msg;
	}
	public String decode()
	{
		String res=from+","+to+","+title+","+body;
		return res;
	}
}
