package ui.viewcontroller.Admin;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.component.DialogFactory;
import util.UserPosition;
import vo.UserVO;

public class AdminUserCellController {
	
	@FXML
	Circle circle;
	
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
	AdminUserDetailViewController adminUserDetailViewController;
	
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
		setPosition(user.position);
	}
	
	public void setPosition(UserPosition position){
		if(position==UserPosition.INVENTORY_STAFF){
			userPosition.setText("库");
			userPosition.setTextFill(Color.web("#33FFFF"));
			circle.setStyle("-fx-Stroke:#33FFFF");
		}
		else if(position==UserPosition.SALES_STAFF){
			userPosition.setText("销");
			userPosition.setTextFill(Color.web("#FF99CC"));
			circle.setStyle("-fx-Stroke:#FF99CC");
		}
		else if(position==UserPosition.FINANCIAL_STAFF){
			userPosition.setText("财");
			userPosition.setTextFill(Color.web("#33CC66"));
			circle.setStyle("-fx-Stroke:#33CC66");
		}
		else if(position==UserPosition.GENERAL_MANAGER){
			userPosition.setText("总");
			userPosition.setTextFill(Color.web("#FF9933"));
			circle.setStyle("-fx-Stroke:#FF9933");
		}
		else if(position==UserPosition.ADMIN){
			userPosition.setText("管");
			userPosition.setTextFill(Color.web("#000000"));
			circle.setStyle("-fx-Stroke:#000000");
		}
	}
	
	public void clickDetailButton(){
		Pane userDetail = null;
    	try{
	    	FXMLLoader userDetailLoader = new FXMLLoader();
	    	userDetailLoader.setLocation(getClass().getResource("/view/admin/UserDetail.fxml"));
	        userDetail = userDetailLoader.load();
	        
	        adminUserDetailViewController = userDetailLoader.getController();
	        adminUserDetailViewController.setAdminUserCellController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	adminUserDetailViewController.setUser(user);
    	adminUserViewController.showUserDetail(userDetail);
	}
	
	public void clickReturnButton(){
		adminUserViewController.clickReturnButton();
	}
	
	public void clickDeleteButton(){
		Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定删除该用户吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	adminUserViewController.clickDeleteButton(user);
            }
        }
	}
}
