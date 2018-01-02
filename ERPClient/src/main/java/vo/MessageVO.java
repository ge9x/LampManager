package vo;

import util.BillState;
import util.UserPosition;

public class MessageVO {

	public int messageID;
	public BillState state;
	public String messageTime;
	public String billID;
	public String content;
	public String title;
	public UserPosition position;
	
	public MessageVO(int messageID, BillState state, String ID, String messageTime, UserPosition position){
		this.state = state;
		this.messageTime = messageTime;
		billID = ID;
		this.position = position;
		this.messageID = messageID;
		
		if(state == BillState.SUBMITTED){
			title = "您有一张单据已提交";
			content = "您的编号为"+ID+"已经提交，请耐心等待审批。";
		}
		else if(state == BillState.PASS){
			title = "您有一张单据已通过";
			content = "您的编号为"+ID+"已通过审批并生效，可在相关界面查看详情。";
		}
		else if(state == BillState.FAILED){
			title = "您有一张单据未通过";
			content = "您的编号为"+ID+"未能通过审批，可在相关界面重新制定。";
		}
	}
}
