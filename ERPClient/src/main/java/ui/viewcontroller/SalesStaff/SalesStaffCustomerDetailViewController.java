package ui.viewcontroller.SalesStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.CustomerVO;

public class SalesStaffCustomerDetailViewController {

	@FXML
	Label customerID;
	
	@FXML
	Label customerType;
	
	@FXML
	Label customerLevel;
	
	@FXML
	Label customerName;
	
	@FXML
	Label customerPhone;
	
	@FXML
	Label customerAddress;
	
	@FXML
	Label customerPostcode;
	
	@FXML
	Label customerMail;
	
	@FXML
	Label customerReceivableLimit;
	
	@FXML
	Label customerReceive;
	
	@FXML
	Label customerPay;
	
	@FXML
	Label customerSalesman;
	
	CustomerVO customer;
	SalesStaffCustomerCellController salesStaffCustomerCellController;
	
	public void setCustomer(CustomerVO customer){
		this.customer = customer;
		customerID.setText(customer.customerID);
		customerType.setText(customer.category.getValue());
		customerLevel.setText(customer.level.getValue());
		customerName.setText(customer.customerName);
		customerPhone.setText(customer.phone);
		customerAddress.setText(customer.address);
		customerPostcode.setText(customer.postCode);
		customerMail.setText(customer.mail);
		customerReceivableLimit.setText(String.valueOf(customer.receivableLimit));
		customerReceive.setText(String.valueOf(customer.receive));
		customerPay.setText(String.valueOf(customer.pay));
		customerSalesman.setText(customer.salesman);
	}
	
	public void setSalesStaffCustomerCellController(SalesStaffCustomerCellController salesStaffCustomerCellController){
		this.salesStaffCustomerCellController = salesStaffCustomerCellController;
	}
}
