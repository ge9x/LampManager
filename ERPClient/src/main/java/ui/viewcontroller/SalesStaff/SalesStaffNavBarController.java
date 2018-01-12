package ui.viewcontroller.SalesStaff;

import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SalesStaffNavBarController {
	private SalesStaffViewController salesStaffViewController;
	UserInfo userInfo = UserBLFactory.getInfo();
	
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
	Rectangle CustomerRec, CustomerSelectRec, PurchaseOrderRec, PurchaseOrderSelectRec, ReturnOrderRec, ReturnOrderSelectRec, SalesRec,
				SalesSelectRec, SalesReturnRec, SalesReturnSelectRec;
	
    @FXML
    public void initialize() {
        CustomerIcon.setText("\ue634");
        PurchaseOrderIcon.setText("\ue689");
        ReturnOrderIcon.setText("\ue67f");
        SalesIcon.setText("\ue693");
        SalesReturnIcon.setText("\ue6e5");
        Image img = new Image("images/avatar/salesStaff.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }

    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void clickCustomerButton(){
    	showHighlight(CustomerRec, CustomerSelectRec);
    	salesStaffViewController.showCustomerList();
    }
    
    public void clickPurchaseOrderButton(){
    	showHighlight(PurchaseOrderRec, PurchaseOrderSelectRec);
    	salesStaffViewController.showPurchaseOrderView();
    }
    
    public void clickReturnOrderButton(){
    	showHighlight(ReturnOrderRec, ReturnOrderSelectRec);
    	salesStaffViewController.showReturnOrderView();
    }

    public void clickSalesOrderButton(){
    	showHighlight(SalesRec, SalesSelectRec);
    	salesStaffViewController.showSalesOrderView();
    }
    
    public void clickSalesReturnOrderButton(){
    	showHighlight(SalesReturnRec, SalesReturnSelectRec);
    	salesStaffViewController.showSalesReturnOrderView();
    }
    
    public void showHighlight(Rectangle rec, Rectangle selectRec){
    	CustomerRec.setFill(Paint.valueOf("#272727"));
    	CustomerSelectRec.setVisible(false);
    	PurchaseOrderRec.setFill(Paint.valueOf("#272727"));
    	PurchaseOrderSelectRec.setVisible(false);
    	ReturnOrderRec.setFill(Paint.valueOf("#272727"));
    	ReturnOrderSelectRec.setVisible(false);
    	SalesRec.setFill(Paint.valueOf("#272727"));
		SalesSelectRec.setVisible(false);
		SalesReturnRec.setFill(Paint.valueOf("#272727"));
		SalesReturnSelectRec.setVisible(false);
    	
    	rec.setFill(Paint.valueOf("#000000"));
    	selectRec.setVisible(true);
    }
}
