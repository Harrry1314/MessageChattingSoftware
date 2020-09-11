package messageclient.controller;
import messageclient.view.LoginWindow;
import messageclient.view.SettingWindow;
import messageclient.view.MainWindow;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

public class Controller
{
	Map<String,Action> actions;
	private static Controller instance=new Controller();
	public static Controller getInstance()
	{
		return instance;
	}
	private Controller()
	{
		actions=new HashMap<String,Action>();
		
		try
		{
			loadActions("messageclient.controller");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void register(String actionName, Action action)
	{
		actions.put(actionName, action);
	}
	public void execute(String actionName, Object p) throws Exception
	{
		Action action=actions.get(actionName);
		if(action!=null)
		{
			try
			{
				action.execute(p);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	private void newAction(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class c=Class.forName(className);
		if(!c.isInterface())
		{
			boolean isActionInterface=false;
			Class[] interfaces=c.getInterfaces();
			for(Class i:interfaces)
			{
				if(i.getName().equals("messageclient.controller.Action"))
				{
					isActionInterface=true;
					break;
				}
			}
			ActionName aName=(ActionName) c.getAnnotation(ActionName.class);
			if(aName!=null && isActionInterface)
			{
				String name=aName.value();
				Action action=(Action) c.newInstance();
				register(name, action);
			}
		}
	}
	private void loadActions(String packageName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException
	{
		Thread t=Thread.currentThread();
		ClassLoader loader=t.getContextClassLoader();
		String packagePath=packageName.replace('.', '/');
		URL url=loader.getResource(packagePath);
		String protocol=url.getProtocol();
		if(protocol.equals("file"))
		{
			String path=url.getPath();
			File file=new File(path);
			String[] subFiles=file.list();
			for(String s:subFiles)
			{
				String className=packageName+"."+s.substring(0,s.indexOf('.'));
				newAction(className);
			}
		}
		else if(protocol.equals("jar"))
		{
			String endClass=".class";
			JarFile jf;
			JarURLConnection connection=(JarURLConnection)url.openConnection();
			jf=connection.getJarFile();
			Enumeration<JarEntry> enu=jf.entries();
			while(enu.hasMoreElements())
			{
				JarEntry je=enu.nextElement();
				String jeName=je.getName();
				if(jeName.indexOf(packagePath)!=-1 && jeName.lastIndexOf(endClass)!=-1)
				{
					String className=jeName.substring(0,jeName.indexOf('.'));
					className=className.replace('/', '.');
					newAction(className);
				}
			}
		}
	}
	
}
