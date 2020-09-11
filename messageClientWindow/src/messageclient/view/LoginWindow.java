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

public class LoginWindow extends JDialog
{
	JLabel label;
	JTextField tf;
	JButton bt;
	JPanel panel;
	GridBagLayout layout;
	
	private static LoginWindow instance=new LoginWindow();
	public static LoginWindow getInstance()
	{
		return instance;
	}
	private LoginWindow()
	{
		super();
		setTitle("Login");
		initUi();
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,200);
	}
	public void initUi()
	{
		label=new JLabel("Username:");
		tf=new JTextField(20);
		bt=new JButton("Login");
		LoginListener ll=new LoginListener();
		bt.addActionListener(ll);
		
		panel=(JPanel)super.getContentPane();
		layout=new GridBagLayout();
		panel.setLayout(layout);
		
		addComponent(label,10,10,10,0,0.1,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		addComponent(tf,20,10,10,0,0.8,1,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		addComponent(bt,30,10,10,0,0.1,1,GridBagConstraints.HORIZONTAL,new Insets(10,0,10,10));
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
	public boolean isLegal(String username)
	{
		boolean res=true;
		if(username==null || username.equals(""))
		{
			res=false;
		}
		return res;
	}
	class LoginListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String username=tf.getText();
			if(isLegal(username)==false)
			{
				JOptionPane.showMessageDialog(LoginWindow.this, "Illegal Username", "WARNING", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				try
				{
					Controller.getInstance().execute("login", tf.getText());
					close();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(LoginWindow.this, e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
}