package ui.viewcontroller.InventoryStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;


/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryLookController {
    InventoryViewController inventoryViewController;

    @FXML
    TilePane tilePane;

    @FXML
    Label AlertIcon;



    @FXML
    public void initialize(){
//        AlertIcon.setText();
        tilePane.setPrefColumns(2);
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }
}
