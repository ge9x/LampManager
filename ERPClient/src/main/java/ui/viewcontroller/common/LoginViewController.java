package ui.viewcontroller.common;

import java.io.*;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.Case;

import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ui.component.DialogFactory;
import util.ResultMessage;
import util.UserPosition;
import vo.UserVO;

/**
 * Created by Kry·L on 2017/11/10.
 */
public class LoginViewController {
    MainUIController mainUIController;
    UserBLService userBLService = UserBLFactory.getBLService();
    File file;

    @FXML
    JFXButton LoginButton;

    @FXML
    Label userIcon;

    @FXML
    Label passwordIcon;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    JFXCheckBox rememberBox;
    
    @FXML
    Label exitButton;

    @FXML
    public void initialize(){
        userIcon.setText("\ue608");
        passwordIcon.setText("\ue620");
        exitButton.setText("\ue604");

        try {
            file = new File(getClass().getResource("/login-info").getPath());
            if (!file.exists())
                file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String  line = "";
            if ((line = reader.readLine()) != null){
                username.setText(line);
                rememberBox.setSelected(true);
            }
            if ((line = reader.readLine()) != null){
                password.setText(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        username.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    clickLoginButton();
                }
            }
        });
        password.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    clickLoginButton();
                }
            }
        });

    }

    public void login(String userID,String password){
        ResultMessage re = userBLService.login(userID,password);
        if (re == ResultMessage.SUCCESS){
            UserVO user = userBLService.findUserByID(userID);
            UserPosition position = user.position;
            switch (position){
                case ADMIN: 
                	mainUIController.showAdminView();
                	break;
                case FINANCIAL_STAFF: 
                	mainUIController.showFinancialStaffView();
                	break;
                case SALES_STAFF: 
                	mainUIController.showSalesStaffView();
                	break;
                case INVENTORY_STAFF:
                	mainUIController.showInventoryView();
                	break;
                case GENERAL_MANAGER:
                	mainUIController.showGeneralManagerView();
                	break;
            }
        }
        else if(re == ResultMessage.FAILED){
        	Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("用户密码输入错误");
	        Optional result = dialog.showAndWait();
        }
        else{
        	Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("该用户不存在");
	        Optional result = dialog.showAndWait();
        }
    }

    public void setMainUIController(MainUIController mainUIController){

        this.mainUIController = mainUIController;
    }

    public void clickLoginButton(){
        if (username.getText().isEmpty() || password.getText().isEmpty()){
            Dialog dialog = DialogFactory.getInformationAlert();
            dialog.setHeaderText("用户名或密码不能为空");
            dialog.showAndWait();
        }else{
            if (rememberBox.isSelected()){
                remember(username.getText(),password.getText());
            }
            login(username.getText(),password.getText());
        }
    }
    private void remember(String username,String password){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(username+System.lineSeparator()+password);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void close(){
    	mainUIController.close();
    }

    public void clickLogButton(MouseEvent mouseEvent) {
        if (username.getText().isEmpty() || password.getText().isEmpty()){
            Dialog dialog = DialogFactory.getInformationAlert();
            dialog.setHeaderText("用户名或密码不能为空");
            dialog.showAndWait();
        }else{
            if (rememberBox.isSelected()){
                remember(username.getText(),password.getText());
            }
            logLogin(username.getText(),password.getText());
        }
    }

    private void logLogin(String username, String password) {
        ResultMessage re = userBLService.login(username,password);
        if (re == ResultMessage.SUCCESS) {
            UserVO user = userBLService.findUserByID(username);
            if (user.position == UserPosition.GENERAL_MANAGER || user.position == UserPosition.FINANCIAL_STAFF){
                mainUIController.showLog();
            }else{
                Dialog dialog = DialogFactory.getInformationAlert();
                dialog.setHeaderText("权限不足，无法查看日志");
                dialog.showAndWait();
            }
        }
    }
}
