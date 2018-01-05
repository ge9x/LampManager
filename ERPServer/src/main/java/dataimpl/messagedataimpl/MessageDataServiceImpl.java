package dataimpl.messagedataimpl;

import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.messagedataservice.MessageDataService;
import po.MessagePO;
import util.ResultMessage;
import util.UserPosition;

/**
 * Created on 2018/1/2
 * 
 * @author 巽
 *
 */
public class MessageDataServiceImpl implements MessageDataService{
	private static MessageDataServiceImpl mesageDataServiceImpl;
	private DataHelper<MessagePO> messageDataHelper;
	
	public static MessageDataServiceImpl getInstance(){
		if(mesageDataServiceImpl == null){
			mesageDataServiceImpl = new MessageDataServiceImpl();
		}
		return mesageDataServiceImpl;
	}
	
	private MessageDataServiceImpl(){
		messageDataHelper = new HibernateDataHelper<MessagePO>(MessagePO.class);
	}

	@Override
	public ArrayList<MessagePO> show(UserPosition position) {
		return messageDataHelper.fullyQuery("position", position);
	}

	@Override
	public ResultMessage add(MessagePO po) {
		return messageDataHelper.save(po);
	}

	@Override
	public ResultMessage delete(int messageID) {
		MessagePO po = messageDataHelper.exactlyQuery("id", messageID);
		return messageDataHelper.delete(po);
	}

}
