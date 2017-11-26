package ui.viewcontroller.SalesStaff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.CustomerCategory;
import util.UserLimits;
import vo.CustomerVO;

public class SalesStaffCustomerDetailViewController {
	private boolean hasEdited = false;
	UserBLService userBLService = new UserBLService_Stub();

	@FXML
	JFXTextField customerID;
	
	@FXML
	JFXTextField customerType;
	
	@FXML
	JFXTextField customerLevel;
	
	@FXML
	JFXTextField customerName;
	
	@FXML
	JFXTextField customerPhone;
	
	@FXML
	JFXTextField customerAddress;
	
	@FXML
	JFXTextField customerPostcode;
	
	@FXML
	JFXTextField customerMail;
	
	@FXML
	JFXTextField customerReceivableLimit;
	
	@FXML
	JFXTextField customerReceive;
	
	@FXML
	JFXTextField customerPay;
	
	@FXML
	JFXTextField customerSalesman;
	
	@FXML
	JFXButton editButton;
	
	@FXML
	JFXButton returnButton;
	
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
		customerID.setEditable(false);
		customerType.setEditable(false);
		customerLevel.setEditable(false);
		customerName.setEditable(false);
		customerPhone.setEditable(false);
		customerAddress.setEditable(false);
		customerPostcode.setEditable(false);
		customerMail.setEditable(false);
		customerReceivableLimit.setEditable(false);
		customerReceive.setEditable(false);
		customerPay.setEditable(false);
		customerSalesman.setEditable(false);
	}
	
	public void setSalesStaffCustomerCellController(SalesStaffCustomerCellController salesStaffCustomerCellController){
		this.salesStaffCustomerCellController = salesStaffCustomerCellController;
	}
	
	public void clickReturnButton(){
		salesStaffCustomerCellController.clickReturnButton();
	}
	
	public void clickEditButton(){
		if(!hasEdited){
			customerID.setEditable(true);
			customerType.setEditable(true);
			customerName.setEditable(true);
			customerPhone.setEditable(true);
			customerAddress.setEditable(true);
			customerPostcode.setEditable(true);
			customerMail.setEditable(true);
			customerSalesman.setEditable(true);
			if(userBLService.findUserByID(userBLService.getCurrentUserID()).limit==UserLimits.MANAGER){
				customerReceivableLimit.setEditable(true);
			}
			
			editButton.setText("完    成");
			hasEdited = true;
		}
		else{
			customer.customerID = customerID.getText();
			customer.customerName = customerName.getText();
			customer.phone = customerPhone.getText();
			customer.address = customerAddress.getText();
			customer.phone = customerPostcode.getText();
			customer.mail = customerMail.getText();
			customer.salesman = customerSalesman.getText();
			customer.receivableLimit = Double.parseDouble(customerReceivableLimit.getText());
			
			if(customerType.getText().equals("进货商")){
				customer.category = CustomerCategory.PUR_AGENT;
			}
			else if(customerType.getText().equals("销售商")){
				customer.category = CustomerCategory.SELLER;
			}

			setCustomer(customer);
			editButton.setText("编    辑");
			hasEdited = false;
		}
	}
}
