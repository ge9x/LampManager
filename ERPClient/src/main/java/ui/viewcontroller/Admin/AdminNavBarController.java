package ui.viewcontroller.Admin;

import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class AdminNavBarController {

	@FXML
	Label UserIcon;
	
	@FXML
	Label userName;
	
	@FXML
    Circle avatar;
	
	AdminViewController adminViewController;
	UserInfo userInfo = new UserController();
	
    @FXML
    public void initialize() {
        UserIcon.setText("\ue610");
        Image img = new Image("./images/avatar/admin.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }
    
    public void setAdminViewController(AdminViewController adminViewController){
    	this.adminViewController = adminViewController;
    }

    public void clickUserButton(){
    	adminViewController.showUserView();
    }
}
