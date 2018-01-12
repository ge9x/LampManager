package ui.viewcontroller.GeneralManager;

import bl.promotionbl.Promotion;
import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GeneralManagerNavBarController {
	
	private GeneralManagerViewController generalManagerViewController;
	UserInfo userInfo = UserBLFactory.getInfo();

    @FXML
    Label ExaminationIcon;
    
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
	
	@FXML
	Rectangle ExaminationRec, ExaminationSelectRec, PromotionRec, PromotionSelectRec, SalesDetailsRec, SalesDetailsSelectRec, DocumentDetailsRec,
				DocumentDetailsSelectRec, ProfitRec, ProfitSelectRec;
    
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
        Image img = new Image("images/avatar/generalManager.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }

    public void clickExaminationButton(){
    	showHighlight(ExaminationRec, ExaminationSelectRec);
    	generalManagerViewController.showExaminationView();
    }
    
    public void clickSalesDetailsButton(){
    	showHighlight(SalesDetailsRec, SalesDetailsSelectRec);
    	generalManagerViewController.showSalesDetailsView();
    }
    
    public void clickProfitButton(){
    	showHighlight(ProfitRec, ProfitSelectRec);
    	generalManagerViewController.showProfitView();
    }
    
    public void clickPromotionButton(){
    	showHighlight(PromotionRec, PromotionSelectRec);
    	generalManagerViewController.showPromotionView();
    }
    
    public void clickDocumentDetailsButton(){
    	showHighlight(DocumentDetailsRec, DocumentDetailsSelectRec);
    	generalManagerViewController.showDocumentDetailsView();
    }
    
    public void showHighlight(Rectangle rec, Rectangle selectRec){
    	ExaminationRec.setFill(Paint.valueOf("#272727"));
    	ExaminationSelectRec.setVisible(false);
    	PromotionRec.setFill(Paint.valueOf("#272727"));
    	PromotionSelectRec.setVisible(false);
    	SalesDetailsRec.setFill(Paint.valueOf("#272727"));
    	SalesDetailsSelectRec.setVisible(false);
    	DocumentDetailsRec.setFill(Paint.valueOf("#272727"));
		DocumentDetailsSelectRec.setVisible(false);
		ProfitRec.setFill(Paint.valueOf("#272727"));
		ProfitSelectRec.setVisible(false);
    	
    	rec.setFill(Paint.valueOf("#000000"));
    	selectRec.setVisible(true);
    } 
}
