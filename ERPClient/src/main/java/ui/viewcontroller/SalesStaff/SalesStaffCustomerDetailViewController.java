package ui.viewcontroller.SalesStaff;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bl.customerbl.CustomerController;
import blservice.customerblservice.CustomerBLService;
import blservice.userblservice.UserBLService;
import blstubdriver.CustomerBLService_Stub;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.CustomerCategory;
import util.Level;
import util.UserLimits;
import vo.CustomerVO;
import vo.UserVO;

public class SalesStaffCustomerDetailViewController {
	private boolean hasEdited = false;
	UserBLService userBLService = new UserBLService_Stub();
	CustomerBLService customerBLService = new CustomerController();

	@FXML
	JFXTextField customerID;
	
	@FXML
	JFXComboBox<String> customerType;
	
	@FXML
	JFXComboBox<String> customerLevel;
	
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
	JFXComboBox<String> customerSalesman;
	
	@FXML
	JFXButton editButton;
	
	@FXML
	JFXButton returnButton;
	
	CustomerVO customer;
	SalesStaffCustomerCellController salesStaffCustomerCellController;
	
	@FXML
	public void initialize(){
		//初始化类别选择器
		customerType.getItems().addAll(CustomerCategory.PUR_AGENT.getValue(), CustomerCategory.SELLER.getValue());
				
		//初始化等级选择器
		customerLevel.getItems().addAll(Level.LEVEL_ONE.getValue(), Level.LEVEL_TWO.getValue(), Level.LEVEL_THREE.getValue(), 
				Level.LEVEL_FOUR.getValue(), Level.LEVEL_FIVE.getValue());
		
		//初始化业务员选择器
		ArrayList<String> salesmenName = new ArrayList<>();
		ArrayList<UserVO> salesmen = customerBLService.getAllSalesman();
		for(UserVO vo:salesmen){
			salesmenName.add(vo.name);
		}
		customerSalesman.getItems().addAll(salesmenName);
	}
	
	public void setCustomer(CustomerVO customer){
		this.customer = customer;
		customerID.setText(customer.customerID);
		customerType.getSelectionModel().select(customer.category.getValue());
		customerLevel.getSelectionModel().select(customer.level.getValue());
		customerName.setText(customer.customerName);
		customerPhone.setText(customer.phone);
		customerAddress.setText(customer.address);
		customerPostcode.setText(customer.postCode);
		customerMail.setText(customer.mail);
		customerReceivableLimit.setText(String.valueOf(customer.receivableLimit));
		customerReceive.setText(String.valueOf(customer.receive));
		customerPay.setText(String.valueOf(customer.pay));
		customerSalesman.getSelectionModel().select(customer.salesman);
		customerID.setEditable(false);
		customerType.setDisable(true);
		customerLevel.setDisable(true);
		customerName.setEditable(false);
		customerPhone.setEditable(false);
		customerAddress.setEditable(false);
		customerPostcode.setEditable(false);
		customerMail.setEditable(false);
		customerReceivableLimit.setEditable(false);
		customerReceive.setEditable(false);
		customerPay.setEditable(false);
		customerSalesman.setDisable(true);
	}
	
	public void setSalesStaffCustomerCellController(SalesStaffCustomerCellController salesStaffCustomerCellController){
		this.salesStaffCustomerCellController = salesStaffCustomerCellController;
	}
	
	public void clickReturnButton(){
		salesStaffCustomerCellController.clickReturnButton();
	}
	
	public void clickEditButton(){
		if(!hasEdited){
			customerType.setDisable(false);
			customerName.setEditable(true);
			customerPhone.setEditable(true);
			customerAddress.setEditable(true);
			customerPostcode.setEditable(true);
			customerMail.setEditable(true);
			customerSalesman.setDisable(false);
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
			customer.postCode = customerPostcode.getText();
			customer.mail = customerMail.getText();
			customer.salesman = customerSalesman.getValue();
			customer.receivableLimit = Double.parseDouble(customerReceivableLimit.getText());
			
			int index = customerType.getSelectionModel().getSelectedIndex();
			if(index==0){
				customer.category = CustomerCategory.PUR_AGENT;
			}
			else{
				customer.category = CustomerCategory.SELLER;
			}

			setCustomer(customer);
			editButton.setText("编    辑");
			customerBLService.updateCustomer(customer);
			hasEdited = false;
		}
	}
}
