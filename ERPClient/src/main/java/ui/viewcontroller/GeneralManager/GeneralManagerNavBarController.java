package ui.viewcontroller.GeneralManager;

import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class GeneralManagerNavBarController {
	
	private GeneralManagerViewController generalManagerViewController;
	UserInfo userInfo = new UserController();

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
	Label userName;
	
	@FXML
    Circle avatar;
    
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
        Image img = new Image("./images/avatar/generalManager.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }

    public void clickExaminationButton(){
    	generalManagerViewController.showExaminationView();
    }
    
    public void clickSalesDetailsButton(){
    	generalManagerViewController.showSalesDetailsView();
    }
    
    public void clickProfitButton(){
    	generalManagerViewController.showProfitView();
    }
    
    public void clickPromotionButton(){
    	generalManagerViewController.showPromotionView();
    }
    
    public void clickDocumentDetailsButton(){
    	generalManagerViewController.showDocumentDetailsView();
    }
}
