package messageclient.controller;

import messageclient.view.LoginWindow;

@ActionName(value="showLogin")
public class ActionShowLogin implements Action
{

	@Override
	public void execute(Object p)
	{
		LoginWindow login;
		login=LoginWindow.getInstance();
		login.display();
	}

}
