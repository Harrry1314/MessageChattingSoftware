package messageclient.controller;

import messageclient.view.ImageWindow;

@ActionName(value="showImage")
public class ActionShowImage implements Action
{

	@Override
	public void execute(Object p) throws Exception
	{
		ImageWindow window=new ImageWindow((String) p);
		window.display();
	}

}
