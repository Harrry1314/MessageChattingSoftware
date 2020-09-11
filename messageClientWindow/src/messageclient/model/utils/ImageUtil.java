package messageclient.model.utils;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageUtil
{
	public static Icon createIcon(String fileName)
	{
		Image img=createImage(fileName);
		img=img.getScaledInstance(50, -1, Image.SCALE_FAST);
		ImageIcon icon=new ImageIcon(img);
		return icon;
	}
	public static Icon createFullIcon(String fileName)
	{
		Image img=createImage(fileName);
		ImageIcon icon=new ImageIcon(img);
		return icon;
	}
	public static Image createImage(String fileName)
	{
		Toolkit tool=Toolkit.getDefaultToolkit();
		Image img=tool.createImage(fileName);
		return img;
	}
}
