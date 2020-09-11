package messageclient.controller;

import messageclient.model.service.SettingService;

@ActionName(value = "changeSetting")
public class ActionChangeSetting implements Action
{
	String ip;
	String port;
	SettingService ss;
	public ActionChangeSetting()
	{
		ss=SettingService.getInstance();
	}
	@Override
	public void execute(Object p) throws Exception
	{
		String[] str=(String[])p;
		ip=str[0];
		port=str[1];
		ss.changeSetting(ip, port);
	}

}
