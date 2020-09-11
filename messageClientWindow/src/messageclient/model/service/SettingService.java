package messageclient.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import messageclient.model.utils.PathUtil;

public class SettingService
{
	public String ip;
	public int port;
	public String fileName;
	public String filePath;
	public static SettingService instance=new SettingService();
	public static SettingService getInstance()
	{
		return instance;
	}
	private SettingService()
	{
		try
		{
			fileName=PathUtil.getRoot()+"/ipport.txt";
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		ArrayList<String> list;
		try
		{
			list = readFile();
			ip=list.get(0);
			port=Integer.parseInt(list.get(1));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			filePath=PathUtil.getRoot()+"/Client_File";
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		File file=new File(filePath);
		if(!file.exists())
		{
			file.mkdir();
		}
	}
	public ArrayList<String> readFile() throws IOException
	{
		ArrayList<String> stringList=new ArrayList<String>();

		File ipfile=new File(fileName);
		if(ipfile.exists())
		{
			FileInputStream fin = new FileInputStream(fileName);
	
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
		}
		else
		{
			stringList.add("127.0.0.1");
			stringList.add("3000");
		}
		return stringList;
	}
	public void changeSetting(String ip, String port) throws FileNotFoundException
	{
		System.out.println(fileName);
		FileOutputStream fout = new FileOutputStream(fileName);

		BufferedWriter writer;
		OutputStreamWriter outWriter;

		outWriter = new OutputStreamWriter(fout);
		writer = new BufferedWriter(outWriter);
		
		try
		{
			writer.write(ip);
			writer.write("\n");
			writer.write(port);
			
			writer.close();
			System.out.println("Setting Changed");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
