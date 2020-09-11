package messageclient.controller;

import messageclient.model.service.UserService;

@ActionName(value="logout")
public class ActionLogout implements Action
{
	UserService us;
	public ActionLogout()
	{
		us=new UserService();
	}
	@Override
	public void execute(Object p) throws Exception
	{
		us.logout();
	}
}
