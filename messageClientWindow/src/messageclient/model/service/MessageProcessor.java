package messageclient.model.service;

import messageclient.model.entity.Message;

public interface MessageProcessor
{
	void process(Message msg);
	void setNext(MessageProcessor next);
}
