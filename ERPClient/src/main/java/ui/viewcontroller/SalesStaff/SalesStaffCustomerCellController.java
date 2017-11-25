package ui.viewcontroller.SalesStaff;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.Level;
import vo.CustomerVO;

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
    public void initialize(){
        CustomerIcon.setText("\ue671");
        DetailIcon.setText("\ue89d");
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
}
