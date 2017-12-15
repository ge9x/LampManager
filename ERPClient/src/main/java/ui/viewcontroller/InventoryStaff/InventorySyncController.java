package ui.viewcontroller.InventoryStaff;

import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryController;
import blservice.inventoryblservice.InventoryBLService;
import blstubdriver.InventoryBLService_Stub;
import com.jfoenix.controls.JFXNodesList;
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
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import util.BillState;
import util.BillType;
import vo.AccountBillVO;
import vo.InventoryBillVO;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventorySyncController {
    InventoryBLService inventoryBLService = new InventoryController();
    InventoryViewController inventoryViewController;
    InventorySyncEditController inventorySyncEditController;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();

    ArrayList<InventoryBillVO> overflow;
    ArrayList<InventoryBillVO> loss;
    ArrayList<InventoryBillVO> gift;
    BillPane billPane;

    @FXML
    Label addIcon;

    @FXML
    JFXTabPane tabPane;

    @FXML
    VBox vBox;

    @FXML
    JFXNodesList TypeChooser;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        TypeChooser.setVisible(false);

//        overflow = inventoryBLService.findBillByStateAndType(BillType.OVERFLOW, BillState.DRAFT);
//        loss = inventoryBLService.findBillByStateAndType(BillType.LOSS,BillState.DRAFT);
//        gift = inventoryBLService.findBillByStateAndType(BillType.GIFT, BillState.DRAFT);

        overflow = inventoryBLService.findBillByType(BillType.OVERFLOW);
        loss = inventoryBLService.findBillByType(BillType.LOSS);
        gift = inventoryBLService.findBillByType(BillType.GIFT);

        billPane = new BillPane("报溢单","报损单","赠送单");
        initTabs();
        vBox.getChildren().add(billPane.getTabPane());
        billPane.getTabPane().getSelectionModel().selectLast();
        billPane.getTabPane().getSelectionModel().selectFirst();
    }
    public void initTabs(){
        ArrayList<Tab> tabs = billPane.getAllTabs();
        for (int i = 0; i < tabs.size(); i++){
            tabs.get(i).setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    Tab tab = (Tab)event.getSource();
                    if (tab.isSelected()){
                        billNodes.clear();
                        fxmlLoaders.clear();
                        loadBills(tab.getText());
                        billPane.setContent(tab,billNodes);
                    }
                }
            });
        }
    }

    public void loadBills(String tab){
        ArrayList<InventoryBillVO> bills = new ArrayList<>();
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
                if (bills.get(i).state == BillState.DRAFT){
                    billController.showDeleteIcon();
                }
                billController.hideCheckbox();
                billController.setInventorySyncController(this);
                billController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void clickAddButton(){
        TypeChooser.setVisible(true);
    }
    public void clickAddOverflow(){
        showBillAddView(BillType.OVERFLOW);
        inventorySyncEditController.addInventoryBill();
    }
    public void clickAddLoss(){
        showBillAddView(BillType.LOSS);
    }
    public void showBillAddView(BillType type){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/inventory/SyncEdit.fxml"));
            Pane page  = loader.load();

            inventorySyncEditController = loader.getController();
            inventorySyncEditController.setInventorySyncController(this);
            inventorySyncEditController.setType(type);
            inventorySyncEditController.addInventoryBill();

            inventoryViewController.showSyncEditView(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeTypeChooser(Event event){
        TypeChooser.setVisible(false);
    }

    public void setDetailView(InventoryBillVO vo){
        showBillAddView(vo.type);
        inventorySyncEditController.setForDetailView(vo);
    }

    public void showInventoryBills(){
        inventoryViewController.showSyncView();
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }

    public void deleteBill(InventoryBillVO inventoryBill) {
        inventoryBLService.deleteBill(inventoryBill.ID);
        showInventoryBills();
    }
}
