package messageclient.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import messageclient.model.entity.Message;
import messageclient.model.utils.Base64;
import messageclient.model.utils.INetSocket;
import messageclient.model.utils.NetSocket;
import messageclient.model.utils.NetSocketCryptoProxy;

public class FileService
{
	INetSocket socket;
	public FileService()
	{
		socket=NetSocketCryptoProxy.getInstance();
	}
	public void sendFile(Message msg) throws Exception
	{
		socket.sendMsg(msg.decode());
	}
	public Message transform(Object[] p) throws Exception
	{
		Message msg=(Message) p[0];
		File file=(File) p[1];
		msg.body=file.getName()+":";
		byte[] buffer=new byte[8192*1024];
		FileInputStream fis=new FileInputStream(file);
		int numOfBytes=fis.read(buffer);
		while(numOfBytes!=-1)
		{
			byte[] buffer2=new byte[numOfBytes];
			System.arraycopy(buffer, 0, buffer2, 0, numOfBytes);
			msg.body=msg.body+Base64.encode(buffer2)+":";
			numOfBytes=fis.read(buffer);
		}
		return msg;
	}
	public String save(String file) throws Exception
	{
		String[] strs=file.split(":");
		String fileName=strs[0];
		fileName=SettingService.getInstance().filePath+"/"+fileName;
		System.out.println("File Name: -----------------"+fileName);
		FileOutputStream fos=new FileOutputStream(fileName);
		for(int i=1;i<strs.length;i++)
		{
			byte[] buffer=Base64.decode(strs[i]);
			fos.write(buffer);
			fos.flush();
		}
		fos.close();
		return fileName;
	}
}
