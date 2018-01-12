package ui.viewcontroller.SalesStaff;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.component.DialogFactory;
import util.BillState;
import util.BillType;
import util.Level;
import vo.CustomerVO;
import vo.PurchaseVO;

public class SalesStaffCustomerCellController {

	CustomerVO customer;
	SalesStaffCustomerInfoViewController salesStaffCustomerInfoViewController;
	SalesStaffCustomerDetailViewController salesStaffCustomerDetailViewController;
	
	@FXML
	Label CustomerIcon;
	
	@FXML
	Label CustomerName;
	
	@FXML
	Label CustomerID;
	
	@FXML
	Label CustomerType;
	
	@FXML
	Label CustomerLevel;
	
	@FXML
	Label DetailIcon;
	
	@FXML
	Label deleteIcon;
	
    @FXML
    public void initialize(){
        CustomerIcon.setText("\ue671");
        DetailIcon.setText("\ue89d");
        deleteIcon.setText("\ue600");
    }
    
    public void setSalesStaffCustomerInfoViewController(SalesStaffCustomerInfoViewController salesStaffCustomerInfoViewController){
        this.salesStaffCustomerInfoViewController = salesStaffCustomerInfoViewController;
    }
    
    public void setCustomer(CustomerVO customer){
    	this.customer = customer;
    	CustomerName.setText(customer.customerName);
    	CustomerID.setText(customer.customerID);
    	CustomerType.setText(customer.category.getValue());
    	setLevel(customer.level);
    }
    
    public void setLevel(Level level){
    	if(level==Level.LEVEL_ONE){
	    	CustomerLevel.setText("VIP1");
	    	CustomerLevel.setStyle("-fx-background-color:#0066CC");
    	}
    	else if(level==Level.LEVEL_TWO){
	    	CustomerLevel.setText("VIP2");
	    	CustomerLevel.setStyle("-fx-background-color:#FFCCFF");
    	}
    	else if(level==Level.LEVEL_THREE){
	    	CustomerLevel.setText("VIP3");
	    	CustomerLevel.setStyle("-fx-background-color:#FFCC33");
    	}
    	else if(level==Level.LEVEL_FOUR){
	    	CustomerLevel.setText("VIP4");
	    	CustomerLevel.setStyle("-fx-background-color:#00FF99");
    	}
    	else if(level==Level.LEVEL_FIVE){
	    	CustomerLevel.setText("VIP5");
	    	CustomerLevel.setStyle("-fx-background-color:#CC99FF");
    	}
    }
    
    public void clickDetailButton(){
    	Pane customerDetail = null;
    	try{
	    	FXMLLoader customerDetailLoader = new FXMLLoader();
	    	customerDetailLoader.setLocation(getClass().getResource("/view/salesStaff/CustomerDetail.fxml"));
	        customerDetail = customerDetailLoader.load();
	        
	        salesStaffCustomerDetailViewController = customerDetailLoader.getController();
	        salesStaffCustomerDetailViewController.setSalesStaffCustomerCellController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	salesStaffCustomerDetailViewController.setCustomer(customer);
    	salesStaffCustomerInfoViewController.showCustomerDetail(customerDetail);
    }
    
    public void clickReturnButton(){
    	salesStaffCustomerInfoViewController.clickReturnButton();
    }
    
    public void clickDeleteButton(){
    	Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定删除该客户吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	if(customer.pay!=0||customer.receive!=0){
            		Dialog subDialog = DialogFactory.getConfirmationAlert();
                    subDialog.setHeaderText("该客户应收应付仍未清零，不可删除");
                    Optional subResult = subDialog.showAndWait();
            	}
            	salesStaffCustomerInfoViewController.clickDeleteButton(customer.customerID);
            }
        }
    }
}
