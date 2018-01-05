package ui.viewcontroller.common;

import javax.xml.soap.Detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.BillState;
import vo.MessageVO;

public class MessageCellController {

	@FXML
	Label messageType, hint, time, detail,delete;
	
	StateBarController stateBarController;
	int order;
	
	@FXML
    public void initialize(){
		delete.setText("\ue698");
	}
	
	public void setMessage(MessageVO messageVO){
		this.time.setText(messageVO.messageTime);
		hint.setText(messageVO.title);
		detail.setText(messageVO.content);
		
		if(messageVO.state == BillState.SUBMITTED){
			messageType.setText("\ue602");
		}
		else if(messageVO.state == BillState.PASS){
			messageType.setText("\ue625");
		}
		else if(messageVO.state == BillState.FAILED){
			messageType.setText("\ue624");
		}
	}
	
	public void setStateBarController(StateBarController stateBarController){
		this.stateBarController = stateBarController;
	}
	
	public void setOrder(int num){
		order = num;
	}
	
	public void clickDeleteButton(){
		stateBarController.deleteMessageCell(order);
	}
}
