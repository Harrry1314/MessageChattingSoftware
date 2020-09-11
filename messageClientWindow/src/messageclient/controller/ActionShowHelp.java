package messageclient.controller;

import javax.swing.JOptionPane;

import messageclient.view.MainWindow;

@ActionName(value="showHelp")
public class ActionShowHelp implements Action
{

	@Override
	public void execute(Object p)
	{
		MainWindow main;
		main=MainWindow.getInstance();
		JOptionPane.showMessageDialog(main, "MessageClient\nv:0.01", "About", JOptionPane.PLAIN_MESSAGE);
	}

}
