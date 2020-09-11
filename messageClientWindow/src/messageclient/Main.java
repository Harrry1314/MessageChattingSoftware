package messageclient;
import messageclient.controller.ActionChangeSetting;
import messageclient.controller.ActionLogin;
import messageclient.controller.ActionLogout;
import messageclient.controller.ActionSendFile;
import messageclient.controller.ActionSendText;
import messageclient.controller.ActionShowHelp;
import messageclient.controller.ActionShowLogin;
import messageclient.controller.ActionShowMain;
import messageclient.controller.ActionShowSetting;
import messageclient.controller.Controller;

public class Main
{

	public static void main(String[] args)
	{
		Controller controller=Controller.getInstance();
		//initController(controller);
		try
		{
			controller.execute("showMain", null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/*
	public static void initController(Controller c)
	{
		c.register("showMain", new ActionShowMain());
		c.register("showLogin", new ActionShowLogin());
		c.register("showHelp", new ActionShowHelp());
		c.register("showSetting", new ActionShowSetting());
		c.register("login", new ActionLogin());
		c.register("sendText", new ActionSendText());
		c.register("logout", new ActionLogout());
		c.register("sendFile", new ActionSendFile());
		c.register("changeSetting", new ActionChangeSetting());
	}
	*/
	
}
