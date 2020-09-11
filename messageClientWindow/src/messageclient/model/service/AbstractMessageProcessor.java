package messageclient.model.service;

import messageclient.model.entity.Message;

abstract class AbstractMessageProcessor implements MessageProcessor
{
	MessageProcessor next;
	@Override
	public void setNext(MessageProcessor next)
	{
		this.next=next;
	}
}
