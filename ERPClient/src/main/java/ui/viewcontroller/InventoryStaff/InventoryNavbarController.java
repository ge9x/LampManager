package ui.viewcontroller.InventoryStaff;

import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
        inventoryViewController.showViewView();
    }
    public void clickCheckButton() {
        inventoryViewController.showCheckView();
    }
    public void clicSyncButton() {
        inventoryViewController.showSyncView();
    }
    public void clickClassificationButton(){
        inventoryViewController.showClassificationView();
    }
    public void clickGoodsButton(){
        inventoryViewController.showGoodsView();
    }


    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }
}
