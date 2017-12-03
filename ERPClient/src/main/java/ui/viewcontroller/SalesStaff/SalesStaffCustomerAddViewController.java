package ui.viewcontroller.SalesStaff;

import java.util.Date;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import blservice.customerblservice.CustomerBLService;
import blstubdriver.CustomerBLService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import ui.component.DialogFactory;
import util.BillState;
import util.BillType;
import util.CustomerCategory;
import util.Level;
import util.Money;
import vo.CashBillVO;
import vo.CustomerVO;

public class SalesStaffCustomerAddViewController {
	
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
	JFXTextField customerSalesman;
	
	@FXML
	JFXButton OKButton;
	
	@FXML
	JFXButton cancelButton;

	SalesStaffCustomerInfoViewController salesStaffCustomerInfoViewController;
	CustomerBLService customerBLService = new CustomerBLService_Stub();
	CustomerVO customer;
	
	@FXML
    public void initialize(){
		customerReceive.setText(Money.getMoneyString(0));
		customerPay.setText(Money.getMoneyString(0));
		
		//初始化类别选择器
		customerType.getItems().addAll(CustomerCategory.PUR_AGENT.getValue(), CustomerCategory.SELLER.getValue());
		customerType.getSelectionModel().select(0);
		
		//初始化等级选择器
		customerLevel.getItems().addAll(Level.LEVEL_ONE.getValue(), Level.LEVEL_TWO.getValue(), Level.LEVEL_THREE.getValue(), 
				Level.LEVEL_FOUR.getValue(), Level.LEVEL_FIVE.getValue());
		customerLevel.getSelectionModel().select(0);

		customerReceive.setEditable(false);
		customerPay.setEditable(false);
    }
	
	public void setSalesStaffCustomerInfoViewController(SalesStaffCustomerInfoViewController salesStaffCustomerInfoViewController){
		this.salesStaffCustomerInfoViewController = salesStaffCustomerInfoViewController;
	}
	
	public void clickOKButton(){
		if(isCompleted()){
			setCustomerInfo();
			customerBLService.addCustomer(customer);
			salesStaffCustomerInfoViewController.clickReturnButton();
		}
		else{
			Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("客户信息填写不完整");
	        Optional result = dialog.showAndWait();
		}
	}
	
	public void clickCancelButton(){
		Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定放弃添加客户吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	salesStaffCustomerInfoViewController.clickReturnButton();
            }
        }
	}
	
	public void setCustomerInfo(){
		customer = new CustomerVO(customerID.getText(), getCustomerType(), getCustomerLevel(), customerName.getText(), customerPhone.getText(),
				customerAddress.getText(), customerPostcode.getText(), customerMail.getText(), Double.parseDouble(customerReceivableLimit.getText()), 0, 0, 
				customerSalesman.getText(), 0, 0);
	}
	
	public CustomerCategory getCustomerType(){
		int index = customerType.getSelectionModel().getSelectedIndex();
		if(index==0){
			return CustomerCategory.PUR_AGENT;
		}
		else{
			return CustomerCategory.SELLER;
		}
	}
	
	public Level getCustomerLevel(){
		int index = customerLevel.getSelectionModel().getSelectedIndex();
		switch(index){
		case 0:
			return Level.LEVEL_ONE;
		case 1:
			return Level.LEVEL_TWO;
		case 2:
			return Level.LEVEL_THREE;
		case 3:
			return Level.LEVEL_FOUR;
		default:
			return Level.LEVEL_FIVE;
		}
	}
	
	public boolean isCompleted(){
		if(hasContent(customerID)&&hasContent(customerName)&&hasContent(customerPhone)&&hasContent(customerReceivableLimit)
				&&hasContent(customerAddress)&&hasContent(customerPostcode)&&hasContent(customerMail)&&hasContent(customerSalesman)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean hasContent(JFXTextField textField){
		if(textField.getText().length()>0){
			return true;
		}
		else{
			return false;
		}
	}
}
