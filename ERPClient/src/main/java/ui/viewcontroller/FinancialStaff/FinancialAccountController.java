package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Kry·L on 2017/11/14.
 */
public class FinancialAccountController {
    @FXML
    Label addIcon;

    @FXML
    Label searchIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        searchIcon.setText("\ue69d");
    }
}
