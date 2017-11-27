package ui.viewcontroller.SalesStaff;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SalesStaffReturnOrderViewController {
	SalesStaffViewController salesStaffViewController;
	SalesStaffReturnEditViewController salesStaffReturnEditViewController;
	
	@FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showReturnOrderList(){
    	salesStaffViewController.showReturnOrderView();
    }
    
    public void clickAddButton(){
    	showPurchaseEditView();
    	salesStaffReturnEditViewController.addReturnOrder();
    }
    
    public void showPurchaseEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/ReturnOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffReturnEditViewController = pageLoader.getController();
            salesStaffReturnEditViewController.setSalesStaffReturnOrderViewController(this);
            salesStaffViewController.showPurchaseOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
