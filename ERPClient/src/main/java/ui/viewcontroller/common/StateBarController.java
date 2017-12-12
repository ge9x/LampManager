package ui.viewcontroller.common;

import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by Kry·L on 2017/12/12.
 */
public class StateBarController {
    MainUIController mainUIController;

    @FXML
    Label exitIcon;

    @FXML
    Pane container;

    JFXSnackbar snackbar;

    @FXML
    public void initialize(){
        exitIcon.setText("\ue72c");
        snackbar = new JFXSnackbar(container);
//        showMessage("导出成功");
    }

    public void showMessage(String message){
        snackbar.show(message,3000);
    }
    public void clickCloseButton(){
        mainUIController.close();
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
}
