package messageclient.view.part;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ListItemPanel extends JPanel
{
	public static final int TEXT_TYPE=0;
	public static final int IMAGE_TYPE=1;
	public static final int FILE_TYPE=2;
	public JLabel labelWho;
	public JLabel labelText;
	public JPanel panel;
	public String fileName;
	public String who;
	private int type;
	private ListItemPanel(String who)
	{
		super();
		setLayout(new BorderLayout());
		this.who=who;
		labelWho=new JLabel();
		labelText=new JLabel();
		labelWho.setText(who);
		panel=new JPanel();
		if(who==null)
		{
			panel.add(labelText);
			this.add(panel,BorderLayout.EAST);
		}
		else
		{
			panel.add(labelWho);
			panel.add(labelText);
			this.add(panel,BorderLayout.WEST);
		}
	}
	public int getType()
	{
		return this.type;
	}
	public ListItemPanel(String who, String text)
	{
		this(who);
		this.type=ListItemPanel.TEXT_TYPE;
		labelText.setText(text);
	}
	public ListItemPanel(String who, Icon icon, String fileName)
	{
		this(who);
		this.fileName=fileName;
		this.type=ListItemPanel.IMAGE_TYPE;
		labelText.setIcon(icon);
	}
}

