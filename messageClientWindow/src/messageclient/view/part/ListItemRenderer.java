package messageclient.view.part;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import messageclient.controller.Controller;
import messageclient.view.MainWindow;



public class ListItemRenderer implements ListCellRenderer
{
	Date d1=new Date();
	Date d2;
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JPanel p;
		ListModel model=list.getModel();
		for(int i=0;i<model.getSize();i++)
		{
			p=(JPanel) model.getElementAt(i);
			p.setBackground(Color.WHITE);
		}
		if(isSelected)
		{
			ListItemPanel lip=(ListItemPanel)value;
			lip.setBackground(Color.LIGHT_GRAY);
			if(list.getValueIsAdjusting())
			{
				d2=new Date();
				if(d2.getTime()-d1.getTime()>1000)
				{
					if(lip.getType()==ListItemPanel.IMAGE_TYPE);
					{
						d1=d2;
						try
						{
							Controller.getInstance().execute("showImage", lip.fileName);
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(MainWindow.getInstance(), e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
			}
		}
		return (Component)value;
	}
	
}
