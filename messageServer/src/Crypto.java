
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class Crypto
{
	static String algorithm;
	static RSAPublicKey pubkey;
	static RSAPrivateKey prikey;
	static RSAPublicKey peerPubkey;
	public static void generateKey()
	{
		algorithm="RSA";
		int keySize=512;
		try
		{
			KeyPairGenerator kpg=KeyPairGenerator.getInstance(algorithm);
			kpg.initialize(keySize);
			KeyPair kp=kpg.generateKeyPair();
			pubkey=(RSAPublicKey) kp.getPublic();
			prikey=(RSAPrivateKey) kp.getPrivate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void setPeerPubkey(String base64Pubkey) throws Exception
	{
		byte[] buffer=Base64.decode(base64Pubkey);
		ByteArrayInputStream byteIn=new ByteArrayInputStream(buffer);
		ObjectInputStream objIn=new ObjectInputStream(byteIn);
		peerPubkey=(RSAPublicKey) objIn.readObject();
		objIn.close();
	}
	public static String getMyPubkey() throws Exception
	{
		ByteArrayOutputStream byteOut=new ByteArrayOutputStream();
		ObjectOutputStream objOut=new ObjectOutputStream(byteOut);
		objOut.writeObject(pubkey);
		objOut.flush();
		byte[] buffer=byteOut.toByteArray();
		objOut.close();
		String base64Str=Base64.encode(buffer);
		return base64Str;
	}
	public static String encrypt(String data) throws Exception
	{
		StringBuilder strBuilder=new StringBuilder();
		byte[] dataBuffer=data.getBytes();
		Cipher cipher=Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, peerPubkey);
		ByteArrayInputStream byteIn=new ByteArrayInputStream(dataBuffer);
		byte[] buffer=new byte[32];
		int readBytes=byteIn.read(buffer);
		while(readBytes!=-1)
		{
			byte[] encryptedBuffer=cipher.doFinal(buffer,0,readBytes);
			strBuilder.append(Base64.encode(encryptedBuffer));
			strBuilder.append(":");
			readBytes=byteIn.read(buffer);
		}
		byteIn.close();
		String encryptedString=strBuilder.toString();
		return encryptedString;
	}
	public static String decrypt(String encryptedData) throws Exception
	{
		ByteArrayOutputStream byteOut=new ByteArrayOutputStream();
		Cipher cipher=Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, prikey);
		String[] parts=encryptedData.split(":");
		for(String part : parts)
		{
			byte[] encryptedBuffer=Base64.decode(part);
			byte[] dataBuffer=cipher.doFinal(encryptedBuffer);
			byteOut.write(dataBuffer);
			byteOut.flush();
		}
		String data=new String(byteOut.toByteArray());
		byteOut.close();
		return data;
	}
}
