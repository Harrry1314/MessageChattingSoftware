package messageclient.controller;
import java.io.IOException;
import java.util.ArrayList;

import messageclient.model.service.SettingService;
import messageclient.view.SettingWindow;

@ActionName(value="showSetting")
public class ActionShowSetting implements Action
{
	String ip;
	String port;
	@Override
	public void execute(Object p) throws IOException
	{
		ArrayList<String> str=SettingService.getInstance().readFile();
		ip=str.get(0);
		port=str.get(1);
		SettingWindow setting;
		setting=SettingWindow.getInstance();
		setting.updateIpPort(ip, port);
		setting.display();
	}
}
