package ui.viewcontroller.common;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.ResultMessage;

/**
 * Created by Kry·L on 2017/11/10.
 */
public class LoginViewController {
    MainUIController mainUIController;
    UserBLService userBLService = new UserBLService_Stub();
    @FXML
    Label userIcon;

    @FXML
    Label passwordIcon;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    public void initialize(){
        userIcon.setText("\ue608");
        passwordIcon.setText("\ue620");
    }

    public LoginViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;
    }
    public void login(){
        ResultMessage re = userBLService.login(username.getText(),password.getText());
        if (re == ResultMessage.SUCCESS){
        }
    }
}
