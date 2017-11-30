package ui.viewcontroller.SalesStaff;

import java.util.Date;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
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
	JFXButton OKButton;
	
	@FXML
	JFXButton cancelButton;

	SalesStaffCustomerInfoViewController salesStaffCustomerInfoViewController;
	CustomerBLService customerBLService = new CustomerBLService_Stub();
	CustomerVO customer;
	
	@FXML
    public void initialize(){
		customerLevel.setText(Level.LEVEL_ONE.getValue());
		customerReceive.setText(Money.getMoneyString(0));
		customerPay.setText(Money.getMoneyString(0));

		customerLevel.setEditable(false);
		customerReceive.setEditable(false);
		customerPay.setEditable(false);
    }
	
	public void setSalesStaffCustomerInfoViewController(SalesStaffCustomerInfoViewController salesStaffCustomerInfoViewController){
		this.salesStaffCustomerInfoViewController = salesStaffCustomerInfoViewController;
	}
	
	public void clickOKButton(){
		setCustomerInfo();
		if(isCompleted()){
			customerBLService.addCustomer(customer);
			salesStaffCustomerInfoViewController.clickReturnButton();
		}
		else{
			Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("客户信息填写不完整");
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
		customer = new CustomerVO(customerID.getText(), getCustomerType(), Level.LEVEL_ONE, customerName.getText(), customerPhone.getText(),
				customerAddress.getText(), customerPostcode.getText(), customerMail.getText(), Double.parseDouble(customerReceivableLimit.getText()), 0, 0, 
				customerSalesman.getText(), 0, 0);
	}
	
	public CustomerCategory getCustomerType(){
		if(customerType.getText().equals("进货商")){
			return CustomerCategory.PUR_AGENT;
		}
		else{
			return CustomerCategory.SELLER;
		}
	}
	
	public boolean isCompleted(){
		if(hasContent(customerID)&&hasContent(customerType)&&hasContent(customerName)&&hasContent(customerPhone)&&hasContent(customerReceivableLimit)
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
