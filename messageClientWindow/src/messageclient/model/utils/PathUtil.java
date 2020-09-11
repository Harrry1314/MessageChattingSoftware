package messageclient.model.utils;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarFile;

public class PathUtil
{
	public static String getRoot() throws IOException
	{
		String path="";
		String packagePath="messageclient";
		Thread t=Thread.currentThread();
		ClassLoader loader=t.getContextClassLoader();
		URL url=loader.getResource(packagePath);
		String protocol=url.getProtocol();
		if(protocol.equals("file"))
		{
			path=url.getPath();
		}
		else if(protocol.equals("jar"))
		{
			JarURLConnection connection=(JarURLConnection) url.openConnection();
			JarFile jar=connection.getJarFile();
			path=jar.getName();
		}
		path=path.substring(0,path.lastIndexOf('/'));
		return path;
	}
}
