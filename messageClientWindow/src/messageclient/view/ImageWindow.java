package messageclient.view;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messageclient.model.utils.ImageUtil;

public class ImageWindow extends JFrame
{
	public JLabel label;
	public Icon icon;
	public String filePath;
	public ImageWindow(String filePath)
	{
		this.filePath=filePath;
		icon=ImageUtil.createFullIcon(filePath);
		label=new JLabel(icon);
		JPanel panel=(JPanel) getContentPane();
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(icon.getIconWidth(), icon.getIconHeight()+100);
	}
	public void display()
	{
		setVisible(true);
	}
}
