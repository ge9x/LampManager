package ui.viewcontroller.SalesStaff;

import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SalesStaffNavBarController {
	private SalesStaffViewController salesStaffViewController;
	UserInfo userInfo = new UserController();
	
	@FXML
	Label CustomerIcon;
	
	@FXML
	Label PurchaseOrderIcon;
	
	@FXML
	Label ReturnOrderIcon;
	
	@FXML
	Label SalesIcon;
	
	@FXML
	Label SalesReturnIcon;
	
	@FXML
	Label userName;
	
	@FXML
    Circle avatar;
	
    @FXML
    public void initialize() {
        CustomerIcon.setText("\ue634");
        PurchaseOrderIcon.setText("\ue689");
        ReturnOrderIcon.setText("\ue67f");
        SalesIcon.setText("\ue693");
        SalesReturnIcon.setText("\ue6e5");
        Image img = new Image("./images/avatar/salesStaff.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }

    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void clickCustomerButton(){
    	salesStaffViewController.showCustomerList();
    }
    
    public void clickPurchaseOrderButton(){
    	salesStaffViewController.showPurchaseOrderView();
    }
    
    public void clickReturnOrderButton(){
    	salesStaffViewController.showReturnOrderView();
    }

    public void clickSalesOrderButton(){
    	salesStaffViewController.showSalesOrderView();
    }
    
    public void clickSalesReturnOrderButton(){
    	salesStaffViewController.showSalesReturnOrderView();
    }
}
