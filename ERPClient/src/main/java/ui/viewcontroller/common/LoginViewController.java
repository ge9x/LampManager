package ui.viewcontroller.common;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.ResultMessage;
import util.UserPosition;
import vo.UserVO;

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
//很奇怪。。。controller中构造方法与initialize方法无法共存

    public void login(String userID,String password){
        ResultMessage re = userBLService.login(userID,password);
//        if (re == ResultMessage.SUCCESS){
//            UserVO user = userBLService.findUserByID(username.getText());
//            UserPosition position = user.position;
//            switch (position){
//                case ADMIN:
//                case FINANCIAL_STAFF: mainUIController.showFinancialStaffView();
//            }
            mainUIController.setCenter(null);
            mainUIController.showInventoryView();
//            mainUIController.showFinancialStaffView();
//            mainUIController.showSalesStaffView();
//            mainUIController.showGeneralManagerView();
//            mainUIController.showAdminView();
//        }
    }

    public void setMainUIController(MainUIController mainUIController){

        this.mainUIController = mainUIController;
    }

    public void clickLoginButton(){
        login(username.getText(),password.getText());
    }
}
