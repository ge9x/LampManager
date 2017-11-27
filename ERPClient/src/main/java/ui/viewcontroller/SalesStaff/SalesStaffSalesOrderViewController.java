package ui.viewcontroller.SalesStaff;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SalesStaffSalesOrderViewController {
	
	SalesStaffViewController salesStaffViewController;
	SalesStaffSalesEditViewController salesStaffSalesEditViewController;
	
	@FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showSalesOrderList(){
    	salesStaffViewController.showSalesOrderView();
    }
    
    public void clickAddButton(){
    	showSalesEditView();
    	salesStaffSalesEditViewController.addSalesOrder();
    }
    
    public void showSalesEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffSalesEditViewController = pageLoader.getController();
            salesStaffSalesEditViewController.setSalesStaffSalesOrderViewController(this);
            salesStaffViewController.showSalesOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
