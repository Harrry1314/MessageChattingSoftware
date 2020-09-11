package messageclient.view;
import messageclient.controller.*;
import messageclient.model.entity.Message;
import messageclient.model.service.UserService;
import messageclient.model.utils.ImageUtil;
import messageclient.view.part.ListItemPanel;
import messageclient.view.part.ListItemRenderer;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainWindow extends JFrame
{
	JMenuBar menuBar;
	JMenu menuFile;
	JMenu menuWindow;
	public JMenuItem itemLogin;
	public JMenuItem itemLogout;
	JMenuItem itemExit;
	JMenuItem itemSetting;
	JMenuItem itemHelp;
	JPopupMenu popMenu;
	JMenuItem itemSendImage;
	JMenuItem itemSendFile;
	
	JComboBox<String> box;
	DefaultListModel listModel;
	JList msgList;
	JScrollPane scroPan;
	public JTextField tf;
	public JButton bt;
	JComboBox<String> userBox;
	DefaultComboBoxModel modelBox;
	
	JPanel mainPanel;
	GridBagLayout layout;
	private static MainWindow instance=new MainWindow();
	public static MainWindow getInstance()
	{
		return instance;
	}
	public void initUi()
	{
		menuFile=new JMenu("File");
		menuWindow=new JMenu("Window");
		menuBar=new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuWindow);
		itemLogin=new JMenuItem("Login");
		itemLogin.setEnabled(true);
		itemLogout=new JMenuItem("Logout");
		itemLogout.setEnabled(false);
		itemSetting=new JMenuItem("Setting");
		itemHelp=new JMenuItem("Help");
		itemSendImage=new JMenuItem("Image");
		itemSendFile=new JMenuItem("File");
		itemExit=new JMenuItem("Exit");
		itemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Controller.getInstance().execute("logout", null);
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
				MainWindow.this.close();
			}
		});
		
		LoginListener ll=new LoginListener();
		itemLogin.addActionListener(ll);
		SettingListener sl=new SettingListener();
		itemSetting.addActionListener(sl);
		HelpListener hl=new HelpListener();
		itemHelp.addActionListener(hl);
		LogoutListener lol=new LogoutListener();
		itemLogout.addActionListener(lol);
		PopupListener ppl=new PopupListener();
		SelectFileListener sfl=new SelectFileListener();
		itemSendImage.addActionListener(sfl);
		itemSendFile.addActionListener(sfl);
		
		menuFile.add(itemLogin);
		menuFile.add(itemLogout);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		menuWindow.add(itemSetting);
		menuWindow.add(itemHelp);
		setJMenuBar(menuBar);
		
		popMenu=new JPopupMenu();
		popMenu.add(itemSendImage);
		popMenu.add(itemSendFile);
		modelBox=new DefaultComboBoxModel();
		userBox=new JComboBox(modelBox);
		listModel=new DefaultListModel();
		msgList=new JList(listModel);
		msgList.setCellRenderer(new ListItemRenderer());
		scroPan=new JScrollPane(msgList);
		tf=new JTextField(14);
		tf.setEnabled(false);
		SendTextListener pel=new SendTextListener();
		tf.addActionListener(pel);
		bt=new JButton("+");
		bt.addActionListener(ppl);
		bt.setEnabled(false);
		
		mainPanel = (JPanel)super.getContentPane();
		layout=new GridBagLayout();
		mainPanel.setLayout(layout);
		
		addComponent(userBox,10,10,20,10,1,0.01,GridBagConstraints.BOTH,new Insets(10,10,10,10));
		addComponent(scroPan,10,20,20,10,1,1,GridBagConstraints.BOTH,new Insets(0,10,0,10));
		addComponent(tf,10,30,10,10,1,0.01,GridBagConstraints.BOTH,new Insets(10,10,10,10));
		addComponent(bt,20,30,10,10,1,0.01,GridBagConstraints.BOTH,new Insets(10,0,10,10));
		
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
		mainPanel.add(comp);
	}
	private MainWindow()
	{
		super();
		setTitle("Message Client");
		initUi();
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e)
			{
				try
				{
					Controller.getInstance().execute("logout", null);
				}
				catch(Exception err)
				{
				}
				MainWindow.this.close();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
		setSize(400,711);
	}
	public void display()
	{
		setVisible(true);
	}
	public void close()
	{
		dispose();
	}
	public void updateUsernames(String[] usernames)
	{
		
		modelBox.removeAllElements();
		for(int i=0;i<usernames.length;i++)
		{
			modelBox.addElement(usernames[i]);
		}
	}
	public void updateTextMessage(Message msg)
	{
		String from=null;
		if(msg.from!=null)
		{
			from=msg.from+": ";
		}
		ListItemPanel panel=new ListItemPanel(from, msg.body);
		listModel.addElement(panel);
	}
	public void updateImageMessage(String from, Icon icon, String fileName)
	{
		if(from!=null)
		{
			from=from+": ";
		}
		ListItemPanel panel=new ListItemPanel(from, icon, fileName);
		listModel.addElement(panel);
	}
	public void updateFileMessage(String from, String fileName)
	{
		if(from!=null)
		{
			from=from+": ";
		}
		ListItemPanel panel=new ListItemPanel(from, fileName);
		listModel.addElement(panel);
	}
	public void afterLogin()
	{
		itemLogin.setEnabled(false);
		itemLogout.setEnabled(true);
		tf.setEnabled(true);
		bt.setEnabled(true);
	}
	public void afterLogout()
	{
		itemLogin.setEnabled(true);
		itemLogout.setEnabled(false);
		tf.setEnabled(false);
		bt.setEnabled(false);
		modelBox.removeAllElements();
		listModel.removeAllElements();
		tf.setText("");
	}
	class LoginListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				Controller.getInstance().execute("showLogin", null);
				afterLogin();
				LoginWindow.getInstance().close();
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainWindow.this, e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class SettingListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				Controller.getInstance().execute("showSetting", null);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainWindow.this, e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class HelpListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				Controller.getInstance().execute("showHelp", null);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainWindow.this, e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class SendTextListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Message msg=new Message();
			msg.from=UserService.me;
			msg.to=(String)userBox.getSelectedItem();
			msg.title="text";
			msg.body=tf.getText();
			try
			{
				Controller.getInstance().execute("sendText", msg);
				msg.from=null;
				MainWindow.this.updateTextMessage(msg);
				tf.setText("");
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainWindow.this, e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class LogoutListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Controller.getInstance().execute("logout", null);
				afterLogout();
			}
			catch (Exception err)
			{
				JOptionPane.showMessageDialog(MainWindow.this, err.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class PopupListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			popMenu.show(MainWindow.this, MainWindow.this.bt.getX(), MainWindow.this.bt.getY());
		}
	}
	class SelectFileListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser chooser=new JFileChooser();
			int choice=chooser.showOpenDialog(MainWindow.this);
			if(choice!=JFileChooser.CANCEL_OPTION)
			{
				File file=chooser.getSelectedFile();
				Message msg=new Message();
				msg.from=UserService.me;
				msg.to=(String) userBox.getSelectedItem();
				Object[] p=new Object[2];
				if(arg0.getSource()==itemSendImage)
				{
					msg.title="image";
					Icon icon=ImageUtil.createIcon(file.getPath());
					MainWindow.this.updateImageMessage(null, icon, file.getPath());
				}
				else if(arg0.getSource()==itemSendFile)
				{
					msg.title="file";
					MainWindow.this.updateFileMessage(null, file.getPath());
				}
				p[0]=msg;
				p[1]=file;
				try
				{
					Controller.getInstance().execute("sendFile", p);
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(MainWindow.this, e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}	
	}
}
