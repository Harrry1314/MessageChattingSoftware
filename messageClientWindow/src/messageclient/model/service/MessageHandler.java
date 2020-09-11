package messageclient.model.service;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import messageclient.model.entity.Message;

public class MessageHandler 
{
	String fileName;
	MessageProcessor first;
	public MessageHandler() throws Exception
	{
		MessageProcessor prev=null;
		Document doc;
		DocumentBuilder builder;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		builder=factory.newDocumentBuilder();
		System.out.println(fileName);
		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		InputStream ins=loader.getResourceAsStream("messageclient/processors.xml");
		doc=builder.parse(ins);
		Element root=doc.getDocumentElement();
		NodeList processorList=root.getElementsByTagName("Processor");
		for(int i=0;i<processorList.getLength();i++)
		{
			Element eleP=(Element) processorList.item(i);
			String name=eleP.getAttribute("name");
			String className=eleP.getAttribute("class");
			Class c=Class.forName(className);
			MessageProcessor mp=(MessageProcessor) c.newInstance();
			mp.setNext(null);
			if(prev==null)
			{
				first=mp;
			}
			else
			{
				prev.setNext(mp);
			}
			prev=mp;
			System.out.println(name);
		}
;	}
	public void handle(Message msg)
	{
		first.process(msg);
	}
}
