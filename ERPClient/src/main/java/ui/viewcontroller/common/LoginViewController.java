package ui.viewcontroller.common;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Kry·L on 2017/11/10.
 */
public class LoginViewController {

    @FXML
    Label userIcon;

    @FXML
    Label passwordIcon;

    @FXML
    public void initialize(){
        userIcon.setText("\ue608");
        passwordIcon.setText("\ue620");
    }
}
