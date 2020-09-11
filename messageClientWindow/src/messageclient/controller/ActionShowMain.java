package messageclient.controller;

import messageclient.view.MainWindow;

@ActionName(value="showMain")
public class ActionShowMain implements Action
{
	@Override
	public void execute(Object p)
	{
		MainWindow main;
		main=MainWindow.getInstance();
		main.display();
	}
	
}
