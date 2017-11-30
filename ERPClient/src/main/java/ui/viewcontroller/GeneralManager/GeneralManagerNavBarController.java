package ui.viewcontroller.GeneralManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GeneralManagerNavBarController {
	
	private GeneralManagerViewController generalManagerViewController;

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
    
    public void setGeneralManagerViewController(GeneralManagerViewController generalManagerViewController){
    	this.generalManagerViewController = generalManagerViewController;
    }
    
    @FXML
    public void initialize() {
        ExaminationIcon.setText("\ue697");
        PromotionIcon.setText("\ue613");
        SalesDetailsIcon.setText("\ue62a");
        DocumentDetailsIcon.setText("\ue644");
        ProfitIcon.setText("\ue677");
    }

    public void clickExaminationButton(){
    	generalManagerViewController.showExaminationView();
    }
    
    public void clickSalesDetailsButton(){
    	generalManagerViewController.showSalesDetailsView();
    }
}
