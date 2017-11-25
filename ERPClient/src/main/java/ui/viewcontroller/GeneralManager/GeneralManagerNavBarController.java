package ui.viewcontroller.GeneralManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GeneralManagerNavBarController {

    @FXML
    Label ExaminationIcon;
    
    @FXML
    Label HistoryIcon;
    
    @FXML
    Label PromotionIcon;
    
    @FXML
    Label SalesDetailsIcon;

    @FXML
    Label DocumentDetailsIcon;

    @FXML
    Label ProfitIcon;
    
    @FXML
    public void initialize() {
        ExaminationIcon.setText("\ue697");
        HistoryIcon.setText("\ue602");
        PromotionIcon.setText("\ue613");
        SalesDetailsIcon.setText("\ue62a");
        DocumentDetailsIcon.setText("\ue644");
        ProfitIcon.setText("\ue677");
    }

    public void clickExaminationButton(){

    }

}
