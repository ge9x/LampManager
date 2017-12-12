package ui.viewcontroller.common;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.ERPClient.Main;

/**
 * Created by Kry·L on 2017/12/12.
 */
public class StateBarController {
    MainUIController mainUIController;
    @FXML
    Label exitIcon;

    @FXML
    public void initialize(){
        exitIcon.setText("\ue72c");
    }

    public void clickCloseButton(){
        mainUIController.close();
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
}
