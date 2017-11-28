package ui.viewcontroller.Admin;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import ui.component.DialogFactory;
import util.UserLimits;
import util.UserPosition;
import vo.UserVO;

public class AdminUserAddViewController {
	
	UserBLService userBLService = new UserBLService_Stub();
	UserVO user;
	AdminUserViewController adminUserViewController;
	
	@FXML
	JFXTextField userID;
	
	@FXML
	JFXTextField userPassword;
	
	@FXML
	JFXTextField userName;
	
	@FXML
	JFXComboBox<String> userPosition;
	
	@FXML
	JFXComboBox<String> userLimit;
	
	@FXML
	JFXButton OKButton;
	
	@FXML
	JFXButton CancelButton;
	
	@FXML
	public void initialize() {
		userPosition.getItems().addAll(UserPosition.INVENTORY_STAFF.getValue(), UserPosition.FINANCIAL_STAFF.getValue(), UserPosition.SALES_STAFF.getValue(),
				UserPosition.GENERAL_MANAGER.getValue(), UserPosition.ADMIN.getValue());
		userLimit.getItems().addAll(UserLimits.STAFF.getValue(), UserLimits.MANAGER.getValue());
		userLimit.getSelectionModel().select(0);
		userLimit.setDisable(true);
		
		userPosition.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				if(userPosition.getSelectionModel().isSelected(2)){
					userLimit.setDisable(false);
				}
				else{
					userLimit.getSelectionModel().select(0);
					userLimit.setDisable(true);
				}
			}
			
		});
	}	
	
	public void setAdminUserViewController(AdminUserViewController adminUserViewController){
		this.adminUserViewController = adminUserViewController;
	}
	
	public void clickOKButton(){
		if(userID.getText().length()>0&&userPassword.getText().length()>0&&userName.getText().length()>0){
			user.userID = userID.getText();
			user.password = userPassword.getText();
			user.name = userName.getText();
				
			int index = userPosition.getSelectionModel().getSelectedIndex();
			switch(index){
			case 0:
				user.position = UserPosition.INVENTORY_STAFF;
				break;
			case 1:
				user.position = UserPosition.FINANCIAL_STAFF;
				break;
			case 2:
				user.position = UserPosition.SALES_STAFF;
				if(userLimit.getSelectionModel().isSelected(0)){
					user.limit = UserLimits.STAFF;
				}
				else{
					user.limit = UserLimits.MANAGER;
				}
					break;
			case 3:
				user.position = UserPosition.GENERAL_MANAGER;
				break;
			case 4:
				user.position = UserPosition.ADMIN;
				break;
			}
			
			userBLService.addUser(user);
			adminUserViewController.clickReturnButton();
		}
		else{
			
		}
		
	}
	
	public void clickCancelButton(){
		Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定放弃添加用户吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	adminUserViewController.clickReturnButton();
            }
        }
	}
}
