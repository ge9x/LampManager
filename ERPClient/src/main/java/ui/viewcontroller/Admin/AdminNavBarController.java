package ui.viewcontroller.Admin;

import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AdminNavBarController {

	@FXML
	Label UserIcon;
	
	@FXML
	Label userName;
	
	@FXML
    Circle avatar;
	
	@FXML
	Rectangle UserRec, UserSelectRec;
	
	@FXML
	AnchorPane UserNav;
	
	AdminViewController adminViewController;
	UserInfo userInfo = new UserController();
	
    @FXML
    public void initialize() {
        UserIcon.setText("\ue610");
        Image img = new Image("./images/avatar/admin.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
//        UserNav.setCursor(Cursor.HAND);
    }
    
    public void setAdminViewController(AdminViewController adminViewController){
    	this.adminViewController = adminViewController;
    }

    public void clickUserButton(){
    	adminViewController.showUserView();
    	showHighlight(UserRec, UserSelectRec);
    }
    
    public void showHighlight(Rectangle rec, Rectangle selectRec){
    	rec.setFill(Paint.valueOf("#000000"));
    	selectRec.setVisible(true);
    }
    
    public void setMouse(){
    	
    }
}
