package ui.viewcontroller.Admin;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import ui.component.DialogFactory;
import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;
import vo.UserVO;

public class AdminUserAddViewController {
	
	UserBLService userBLService = UserBLFactory.getBLService();
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
		userID.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				for(int i=0;i<newValue.length();i++){
					if(Character.isDigit(newValue.charAt(i))){
						Dialog dialog = DialogFactory.getConfirmationAlert();
				        dialog.setHeaderText("用户ID填写错误");
				        Optional result = dialog.showAndWait();
					}
				}
			}
		});
	}	
	
	public void setAdminUserViewController(AdminUserViewController adminUserViewController){
		this.adminUserViewController = adminUserViewController;
	}
	
	public void clickOKButton(){
		if(userID.getText().length()>0&&userPassword.getText().length()>0&&userName.getText().length()>0){
				
			int index = userPosition.getSelectionModel().getSelectedIndex();
			UserPosition position = UserPosition.INVENTORY_STAFF;
			UserLimits limit = UserLimits.STAFF;
			switch(index){
			case 0:
				position = UserPosition.INVENTORY_STAFF;
				break;
			case 1:
				position = UserPosition.FINANCIAL_STAFF;
				break;
			case 2:
				position = UserPosition.SALES_STAFF;
				if(userLimit.getSelectionModel().isSelected(0)){
					limit = UserLimits.STAFF;
				}
				else{
					limit = UserLimits.MANAGER;
				}
					break;
			case 3:
				position = UserPosition.GENERAL_MANAGER;
				break;
			case 4:
				position = UserPosition.ADMIN;
				break;
			}
			
			user = new UserVO(userID.getText(), userPassword.getText(), userName.getText(), position, limit);
			
			ResultMessage re = userBLService.addUser(user);
			if(re==ResultMessage.SUCCESS){
				adminUserViewController.clickReturnButton();
			}
			else{
				Dialog dialog = DialogFactory.getInformationAlert();
		        dialog.setHeaderText("该用户ID已存在");
		        Optional result = dialog.showAndWait();
			}
		}
		else{
			Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("用户信息填写不完整");
	        Optional result = dialog.showAndWait();
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
