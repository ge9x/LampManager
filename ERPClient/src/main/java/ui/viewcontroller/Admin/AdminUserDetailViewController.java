package ui.viewcontroller.Admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bl.userbl.UserController;
import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import util.UserLimits;
import util.UserPosition;
import vo.UserVO;

public class AdminUserDetailViewController {

	private boolean hasEdited = false;
	UserBLService userBLService = new UserController();
	UserVO user;
	AdminUserCellController adminUserCellController;
	
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
	JFXButton editButton;
	
	@FXML
	JFXButton returnButton;
	
	@FXML
	public void initialize() {
		userPosition.getItems().addAll(UserPosition.INVENTORY_STAFF.getValue(), UserPosition.FINANCIAL_STAFF.getValue(), UserPosition.SALES_STAFF.getValue(),
				UserPosition.GENERAL_MANAGER.getValue(), UserPosition.ADMIN.getValue());
		userLimit.getItems().addAll(UserLimits.STAFF.getValue(), UserLimits.MANAGER.getValue());
		userLimit.getSelectionModel().select(0);
		
		userPosition.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				if(hasEdited&&userPosition.getSelectionModel().isSelected(2)){
					userLimit.setDisable(false);
				}
				else{
					userLimit.getSelectionModel().select(0);
					userLimit.setDisable(true);
				}
			}
			
		});
	}
	
	public void setUser(UserVO user){
		this.user = user;
		userID.setText(user.userID);
		userPassword.setText(user.password);
		userName.setText(user.name);
		setUserPosition(user);
		
		userID.setEditable(false);
		userPassword.setEditable(false);
		userName.setEditable(false);
		userPosition.setDisable(true);
		userLimit.setDisable(true);
	}
	
	public void setUserPosition(UserVO vo){
		if(user.position==UserPosition.INVENTORY_STAFF){
			userPosition.getSelectionModel().select(0);
		}
		else if(user.position==UserPosition.FINANCIAL_STAFF){
			userPosition.getSelectionModel().select(1);
		}
		else if(user.position==UserPosition.SALES_STAFF){
			userPosition.getSelectionModel().select(2);
			if(user.limit==UserLimits.MANAGER){
				userLimit.getSelectionModel().select(1);
			}
		}
		else if(user.position==UserPosition.GENERAL_MANAGER){
			userPosition.getSelectionModel().select(3);
		}
		else{
			userPosition.getSelectionModel().select(4);
		}
	}
	
	public void setAdminUserCellController(AdminUserCellController adminUserCellController){
		this.adminUserCellController = adminUserCellController;
	}
	
	public void clickEditButton(){
		if(!hasEdited){
			userPassword.setEditable(true);
			userName.setEditable(true);
			userPosition.setDisable(false);
			
			editButton.setText("完    成");
			hasEdited = true;
		}
		else{
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
			
			setUser(user);
			editButton.setText("编    辑");
			userBLService.modifyUser(user);
			hasEdited = false;
		}
	}
	
	public void clickReturnButton(){
		adminUserCellController.clickReturnButton();
	}
}
