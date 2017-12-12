package ui.viewcontroller.InventoryStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.FinancialStaff.FinancialViewController;

/**
 * Created by Kry·L on 2017/11/26.
 */
public class InventoryNavbarController {
    private InventoryViewController inventoryViewController;
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
    public void initialize() {
        ViewIcon.setText("\ue678");
        CheckIcon.setText("\ue803");
        SyncIcon.setText("\ue603");
        ClassificationIcon.setText("\ue64c");
        GoodsIcon.setText("\ue68d");
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
