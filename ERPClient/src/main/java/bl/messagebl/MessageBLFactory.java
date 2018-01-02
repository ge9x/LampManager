package bl.messagebl;

import bl.userbl.UserController;
import blservice.messageblservice.MessageBLService;
import blservice.messageblservice.MessageInfo;
import blservice.userblservice.UserBLService;
import blservice.userblservice.UserInfo;

public class MessageBLFactory {
	private static MessageController messageController;
	
	public synchronized static MessageBLService getBLService() {
		if (messageController == null) {
			messageController = new MessageController();
		}
		return messageController;
	}

	public synchronized static MessageInfo getInfo() {
		if (messageController == null) {
			messageController = new MessageController();
		}
		return messageController;
	}
}
