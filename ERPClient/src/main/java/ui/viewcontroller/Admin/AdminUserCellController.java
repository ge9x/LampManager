package ui.viewcontroller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.UserPosition;
import vo.UserVO;

public class AdminUserCellController {
	
	@FXML
	Label userPosition;
	
	@FXML
	Label userID;
	
	@FXML
	Label userName;
	
	@FXML
	Label userLimit;
	
	@FXML
	Label deleteIcon;
	
	@FXML
	Label detailIcon;
	
	@FXML
	Label IDIcon;
	
	@FXML
	Label NameIcon;
	
	@FXML
	Label LimitIcon;
	
	UserVO user;
	AdminUserViewController adminUserViewController;
	
	@FXML
	public void initialize(){
		deleteIcon.setText("\ue600");
		detailIcon.setText("\ue89d");
		IDIcon.setText("\ue61a");
		NameIcon.setText("\ue85f");
		LimitIcon.setText("\ue62b");
	}
	
	public void setAdminUserViewController(AdminUserViewController adminUserViewController){
		this.adminUserViewController = adminUserViewController;
	}
	
	public void setUser(UserVO user){
		this.user = user;
		userID.setText(user.userID);
		userName.setText(user.name);
		userLimit.setText(user.limit.getValue());
	}
	
	public void setPosition(UserPosition position){
		if(position==UserPosition.INVENTORY_STAFF){
			userPosition.setText("库");
			userPosition.setStyle("-fx-background-color:#33FFFF");
		}
		else if(position==UserPosition.SALES_STAFF){
			userPosition.setText("销");
			userPosition.setStyle("-fx-background-color:#FF99CC");
		}
		else if(position==UserPosition.FINANCIAL_STAFF){
			userPosition.setText("财");
			userPosition.setStyle("-fx-background-color:#33CC66");
		}
		else if(position==UserPosition.GENERAL_MANAGER){
			userPosition.setText("总");
			userPosition.setStyle("-fx-background-color:#FF9933");
		}
	}
}
