package ui.viewcontroller.common;

import javax.xml.soap.Detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.BillState;

public class MessageCellController {

	@FXML
	Label messageType, hint, time, detail,delete;
	
	StateBarController stateBarController;
	int order;
	
	@FXML
    public void initialize(){
		delete.setText("\ue698");
	}
	
	public void setMessage(BillState state, String time, String ID){
		this.time.setText(time);
		if(state == BillState.SUBMITTED){
			messageType.setText("\ue602");
			hint.setText("您有一张单据已提交");
			detail.setText("您的编号为"+ID+"已经提交，请耐心等待审批。");
		}
		else if(state == BillState.PASS){
			messageType.setText("\ue625");
			hint.setText("您有一张单据已通过");
			detail.setText("您的编号为"+ID+"已通过审批并生效，可在相关界面查看详情。");
		}
		else if(state == BillState.FAILED){
			messageType.setText("\ue624");
			hint.setText("您有一张单据未通过");
			detail.setText("您的编号为"+ID+"未能通过审批，可在相关界面重新制定。");
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
