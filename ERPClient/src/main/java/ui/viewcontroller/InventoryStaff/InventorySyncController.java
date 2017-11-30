package ui.viewcontroller.InventoryStaff;

import blservice.inventoryblservice.InventoryBLService;
import blstubdriver.InventoryBLService_Stub;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import ui.viewcontroller.common.BillController;
import util.BillType;
import vo.AccountBillVO;
import vo.InventoryBillVO;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventorySyncController {
    InventoryBLService inventoryBLService = new InventoryBLService_Stub();
    InventoryViewController inventoryViewController;
    InventorySyncEditController inventorySyncEditController;

    TilePane billPane;
    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();

    ArrayList<InventoryBillVO> overflow;
    ArrayList<InventoryBillVO> loss;
    ArrayList<InventoryBillVO> gift;

    @FXML
    Label addIcon;

    @FXML
    JFXTabPane tabPane;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        overflow = inventoryBLService.findBillByType(BillType.OVERFLOW);
        loss = inventoryBLService.findBillByType(BillType.LOSS);
        gift = inventoryBLService.findBillByType(BillType.GIFT);

        initTabPane();
    }
    public void initTabPane(){
        Tab overflowTab = new Tab();
        Tab lossTab = new Tab();
        Tab giftTab = new Tab();

        overflowTab.setText("报溢单");
        lossTab.setText("报损单");
        giftTab.setText("赠送单");

        initTabs(overflowTab,lossTab,giftTab);
        tabPane.getTabs().addAll(overflowTab,lossTab,giftTab);
        tabPane.getSelectionModel().selectFirst();
    }
    public void initTabs(Tab...tabs){
        for (int i = 0; i < tabs.length; i++){
            tabs[i].setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    Tab tab = (Tab)event.getSource();
                    if (tab.isSelected()){
                        ScrollPane scrollPane = new ScrollPane();
                        billPane = new TilePane();
                        billPane.setPadding(new Insets(10,7,0,10));
                        billPane.setPrefColumns(3);
                        billPane.setHgap(25);
                        billPane.setVgap(20);
                        billPane.getChildren().clear();
                        billNodes.clear();
                        fxmlLoaders.clear();
                        loadBills(tab.getText());
                        billPane.getChildren().addAll(billNodes);
                        scrollPane.setContent(billPane);
                        tab.setContent(scrollPane);
                    }
                }
            });
        }
    }
    public void loadBills(String tab){
        ArrayList<InventoryBillVO> bills = null;
        switch (tab){
            case "报溢单": bills = overflow;break;
            case "报损单": bills = loss;break;
            case "赠送单": bills = gift;break;
        }
        for (int i = 0; i < bills.size(); i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/common/bill.fxml"));
                VBox node = loader.load();
                fxmlLoaders.add(loader);
                billNodes.add(node);
                BillController billController = loader.getController();
                billController.hideCheckbox();
                billController.setInventorySyncController(this);
                billController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void clickAddButton(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/inventory/SyncEdit.fxml"));
            Pane page  = loader.load();

            inventorySyncEditController = loader.getController();
            inventorySyncEditController.setInventorySyncController(this);

            inventoryViewController.showSyncEditView(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }
}
