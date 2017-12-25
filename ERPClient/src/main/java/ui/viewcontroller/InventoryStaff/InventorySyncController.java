package ui.viewcontroller.InventoryStaff;

import bl.inventorybl.InventoryController;
import blservice.inventoryblservice.InventoryBLService;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import util.BillState;
import util.BillType;
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
    BillPane billPane;
    ToggleGroup toggleGroup;

    @FXML
    Label addIcon;

    @FXML
    VBox vBox;

    @FXML
    JFXNodesList TypeChooser;

    @FXML
    JFXRadioButton All,Draft,Submitted,Pass,Failed;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        TypeChooser.setVisible(false);

        toggleGroup = new ToggleGroup();
        All.setToggleGroup(toggleGroup);
        Draft.setToggleGroup(toggleGroup);
        Submitted.setToggleGroup(toggleGroup);
        Pass.setToggleGroup(toggleGroup);
        Failed.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                BillState state = BillState.getEnumByValue(((JFXRadioButton)newValue).getText());
                filterBills(state);
            }
        });

        overflow = inventoryBLService.findBillByType(BillType.OVERFLOW);
        loss = inventoryBLService.findBillByType(BillType.LOSS);

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
                        loadBills(tab.getText());
                    }
                }
            });
        }
    }

    public void loadBills(String tab){
        fxmlLoaders.clear();
        billNodes.clear();
        ArrayList<InventoryBillVO> bills = new ArrayList<>();
        switch (tab){
            case "报溢单": bills = overflow;break;
            case "报损单": bills = loss;break;
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
        billPane.setContent(billPane.getTabByName(tab),billNodes);
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
    public void filterBills(BillState state){
        if (state == BillState.ALL){
            switch (billPane.getSelected()){
                case "报溢单": overflow = inventoryBLService.findBillByType(BillType.OVERFLOW);
                case "报损单": loss = inventoryBLService.findBillByType(BillType.LOSS);
            }
        }else{
            switch (billPane.getSelected()){
                case "报溢单": overflow = inventoryBLService.findBillByStateAndType(BillType.OVERFLOW,state);
                case "报损单": loss = inventoryBLService.findBillByStateAndType(BillType.LOSS,state);
            }
        }

        loadBills(billPane.getSelected());

    }
}
