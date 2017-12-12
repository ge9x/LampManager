package ui.viewcontroller.common;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.Case;

import bl.userbl.UserController;
import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.component.DialogFactory;
import util.ResultMessage;
import util.UserPosition;
import vo.UserVO;

/**
 * Created by Kry·L on 2017/11/10.
 */
public class LoginViewController {
    MainUIController mainUIController;
    UserBLService userBLService = new UserController();
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
<<<<<<< HEAD
//        if (re == ResultMessage.SUCCESS){
//            UserVO user = userBLService.findUserByID(username.getText());
//            UserPosition position = user.position;
//            switch (position){
//                case ADMIN:
//                case FINANCIAL_STAFF: mainUIController.showFinancialStaffView();
//            }
            mainUIController.setCenter(null);
//            mainUIController.showInventoryView();
//            mainUIController.showFinancialStaffView();
//            mainUIController.showSalesStaffView();
          mainUIController.showGeneralManagerView();
//            mainUIController.showAdminView();
//        }
=======
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
>>>>>>> refs/remotes/origin/dev
    }

    public void setMainUIController(MainUIController mainUIController){

        this.mainUIController = mainUIController;
    }

    public void clickLoginButton(){
        login(username.getText(),password.getText());
    }
}
