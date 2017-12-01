package ui.viewcontroller.SalesStaff;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SalesStaffPurchaseOrderViewController {
	
	SalesStaffViewController salesStaffViewController;
	SalesStaffPurchaseEditViewController salesStaffPurchaseEditViewController;
	
	@FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showPurchaseOrderList(){
    	salesStaffViewController.showPurchaseOrderView();
    }
    
    public void clickAddButton(){
    	showPurchaseEditView();
    	salesStaffPurchaseEditViewController.addPurchaseOrder();
    }
    
    public void showPurchaseEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/PurchaseOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffPurchaseEditViewController = pageLoader.getController();
            salesStaffPurchaseEditViewController.setSalesStaffPurchaseOrderViewController(this);
            salesStaffViewController.showPurchaseOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
