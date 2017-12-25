package ui.viewcontroller.InventoryStaff;

import javax.swing.text.View;

import org.hibernate.annotations.Check;

import bl.goodsbl.Goods;
import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Created by Kry·L on 2017/11/26.
 */
public class InventoryNavbarController {
    private InventoryViewController inventoryViewController;
    private UserInfo userInfo;

    @FXML
    Label ViewIcon;

    @FXML
    Label CheckIcon;

    @FXML
    Label SyncIcon;

    @FXML
    Label ClassificationIcon;

    @FXML
    Label GoodsIcon;

    @FXML
    Circle avatar;

    @FXML
    Label userName;

    @FXML
    Rectangle ViewRec, ViewSelectRec, CheckRec, CheckSelectRec, SyncRec, SyncSelectRec, ClassificationRec, ClassificationSelectRec, GoodsRec,
    			GoodsSelectRec;



    @FXML
    public void initialize() {
        userInfo = new UserController();

        ViewIcon.setText("\ue678");
        CheckIcon.setText("\ue803");
        SyncIcon.setText("\ue603");
        ClassificationIcon.setText("\ue64c");
        GoodsIcon.setText("\ue68d");

        String username = userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID());
        userName.setText(username);
        Image img = new Image("images/avatar/inventory2.jpg");
        avatar.setFill(new ImagePattern(img));
    }

    public void clickViewButton(){
    	showHighlight(ViewRec, ViewSelectRec);
        inventoryViewController.showViewView();
    }
    public void clickCheckButton() {
    	showHighlight(CheckRec, CheckSelectRec);
        inventoryViewController.showCheckView();
    }
    public void clicSyncButton() {
    	showHighlight(SyncRec, SyncSelectRec);
        inventoryViewController.showSyncView();
    }
    public void clickClassificationButton(){
    	showHighlight(ClassificationRec, ClassificationSelectRec);
        inventoryViewController.showClassificationView();
    }
    public void clickGoodsButton(){
    	showHighlight(GoodsRec, GoodsSelectRec);
        inventoryViewController.showGoodsView();
    }


    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }
    
    public void showHighlight(Rectangle rec, Rectangle selectRec){
    	ViewRec.setFill(Paint.valueOf("#272727"));
    	ViewSelectRec.setVisible(false);
    	CheckRec.setFill(Paint.valueOf("#272727"));
    	CheckSelectRec.setVisible(false);
    	SyncRec.setFill(Paint.valueOf("#272727"));
    	SyncSelectRec.setVisible(false);
    	ClassificationRec.setFill(Paint.valueOf("#272727"));
		ClassificationSelectRec.setVisible(false);
		GoodsRec.setFill(Paint.valueOf("#272727"));
		GoodsSelectRec.setVisible(false);
    	
    	rec.setFill(Paint.valueOf("#000000"));
    	selectRec.setVisible(true);
    }
}
