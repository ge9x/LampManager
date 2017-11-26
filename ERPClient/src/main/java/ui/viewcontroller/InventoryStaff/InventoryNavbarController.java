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
    Label viewIcon;

    @FXML
    Label checkIcon;

    @FXML
    Label syncIcon;

    @FXML
    Label classificationIcon;

    @FXML
    Label goodsIcon;



    @FXML
    public void initialize() {
        viewIcon.setText("\ue982");
        checkIcon.setText("\ue60e");
        syncIcon.setText("\ue60f");
        classificationIcon.setText("\ue626");
        goodsIcon.setText("\ue62a");
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
