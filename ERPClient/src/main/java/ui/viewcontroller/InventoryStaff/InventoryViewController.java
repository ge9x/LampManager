package ui.viewcontroller.InventoryStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/26.
 */
public class InventoryViewController {

    InventoryNavbarController inventoryNavbarController;
    InventoryCheckController inventoryCheckController;
    InventoryLookController inventoryLookController;
    InventoryClassificationController inventoryClassificationController;
    InventoryGoodsController inventoryGoodsController;
    InventorySyncController inventorySyncController;
    MainUIController mainUIController;

       public InventoryViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/inventory/InventoryNavbar.fxml"));
            Pane pane = navbarLoader.load();
            inventoryNavbarController = navbarLoader.getController();
            inventoryNavbarController.setInventoryViewController(this);

            mainUIController.setLeft(pane);
        }catch (IOException e){
            e.printStackTrace();
        }

        inventoryNavbarController.clickCheckButton();
    }
    public void showViewView(){
        mainUIController.setCenter(null);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/inventory/Look.fxml"));
            Pane page = loader.load();
            inventoryLookController = loader.getController();
            inventoryLookController.setInventoryViewController(this);

            mainUIController.setCenter(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showCheckView(){
        mainUIController.setCenter(null);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/inventory/Check.fxml"));
            Pane page = loader.load();
            inventoryCheckController = loader.getController();
            inventoryCheckController.setInventoryViewController(this);

            mainUIController.setCenter(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showSyncView(){
        mainUIController.setCenter(null);
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/inventory/Sync.fxml"));
        Pane page = loader.load();

        inventorySyncController = loader.getController();
        inventorySyncController.setInventoryViewController(this);

        mainUIController.setCenter(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showSyncEditView(Pane page){
        mainUIController.setCenter(page);
    }
    public void showClassificationView(){
        mainUIController.setCenter(null);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/inventory/Classification.fxml"));
            Pane page = loader.load();
            inventoryClassificationController = loader.getController();
            inventoryClassificationController.setInventoryViewController(this);

            mainUIController.setCenter(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showGoodsView(){
        mainUIController.setCenter(null);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/inventory/Goods.fxml"));
            Pane page = loader.load();
            inventoryGoodsController = loader.getController();
            inventoryGoodsController.setInventoryViewController(this);

            mainUIController.setCenter(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
