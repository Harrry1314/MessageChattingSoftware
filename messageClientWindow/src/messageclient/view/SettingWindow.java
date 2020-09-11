package messageclient.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import messageclient.controller.Controller;

public class SettingWindow extends JDialog
{
	JLabel labelIp;
	JLabel labelPort;
	JTextField tfIp;
	JTextField tfPort;
	JButton bt;
	JPanel panel;
	GridBagLayout layout;
	
	private static SettingWindow instance=new SettingWindow();
	public static SettingWindow getInstance()
	{
		return instance;
	}
	private SettingWindow()
	{
		super();
		setTitle("Setting");
		initUi();
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,200);
	}
	public void updateIpPort(String ip, String port)
	{
		tfIp.setText(ip);
		tfPort.setText(port);
	}
	public void initUi()
	{
		labelIp=new JLabel("IP Address:");
		labelPort=new JLabel("Port:");
		tfIp=new JTextField(20);
		tfPort=new JTextField(20);
		
		bt=new JButton("Save");
		SettingListener sl=new SettingListener();
		bt.addActionListener(sl);
		
		panel=(JPanel)super.getContentPane();
		layout=new GridBagLayout();
		panel.setLayout(layout);
		
		addComponent(labelIp,10,10,10,10,0.1,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		addComponent(tfIp,20,10,10,10,0.9,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		addComponent(labelPort,10,20,10,10,0.1,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		addComponent(tfPort,20,20,10,10,0.9,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		addComponent(bt,20,30,10,10,0.9,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		
	}
	public void addComponent(JComponent comp, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, Insets insets)
	{
		GridBagConstraints cons=new GridBagConstraints();
		cons.gridx=gridx;
		cons.gridy=gridy;
		cons.gridwidth=gridwidth;
		cons.gridheight=gridheight;
		cons.weightx=weightx;
		cons.weighty=weighty;
		cons.fill=fill;
		cons.insets=insets;
		layout.setConstraints(comp, cons);
		panel.add(comp);
	}
	public void display()
	{
		setModal(true);
		setVisible(true);
	}
	public void close()
	{
		dispose();
	}
	public boolean isLegal(String ip, String port)
	{
		boolean res=true;
		if(ip==null || ip.equals(""))
		{
			res=false;
		}
		if(port==null || port.equals(""))
		{
			res=false;
		}
		return res;
	}
	class SettingListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(isLegal(tfIp.getText(), tfPort.getText())==false)
			{
				JOptionPane.showMessageDialog(SettingWindow.this, "Illegal IP Address or Port Number.", "WARNING", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				String ip=tfIp.getText();
				String port=tfPort.getText();
				String[] str= {ip,port};
				try
				{
					Controller.getInstance().execute("changeSetting", str);
					close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
