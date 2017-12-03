package ui.viewcontroller.SalesStaff;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SalesStaffSalesReturnOrderViewController {
	SalesStaffViewController salesStaffViewController;
	SalesStaffSalesReturnEditViewController salesStaffSalesReturnEditViewController;
	
	@FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showSalesReturnOrderList(){
    	salesStaffViewController.showSalesReturnOrderView();
    }
    
    public void clickAddButton(){
    	showSalesEditView();
    	salesStaffSalesReturnEditViewController.addSalesReturnOrder();
    }
    
    public void showSalesEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesReturnOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffSalesReturnEditViewController = pageLoader.getController();
            salesStaffSalesReturnEditViewController.setSalesStaffSalesReturnOrderViewController(this);
            salesStaffViewController.showSalesOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
