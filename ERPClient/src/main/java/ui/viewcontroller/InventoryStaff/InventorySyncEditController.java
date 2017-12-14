package ui.viewcontroller.InventoryStaff;

import bean.GoodsBean;
import bean.GoodsItemBean;
import bl.inventorybl.InventoryController;
import bl.userbl.UserController;
import blservice.inventoryblservice.InventoryBLService;
import blservice.userblservice.UserInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import ui.component.DialogFactory;
import ui.component.GoodsSelecter;
import ui.component.GoodsTable;
import util.BillState;
import util.BillType;
import vo.AccountBillVO;
import vo.GoodsVO;
import vo.InventoryBillVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/30.
 */
public class InventorySyncEditController {

    InventorySyncController inventorySyncController;
    InventoryBLService inventoryBLService = new InventoryController();
    HashMap<GoodsVO,Integer> goodsItems = new HashMap<>();
    UserInfo userInfo = new UserController();
    Boolean isNew;
    BillType type;

    ObservableList<GoodsBean> data = FXCollections.observableArrayList();
    TableView<GoodsBean> table;

    @FXML
    Label BillID;

    @FXML
    Label addIcon;

    @FXML
    Label deleteIcon;

    @FXML
    Label Username;

    @FXML
    JFXComboBox Inventory;

    @FXML
    VBox vbox;

    @FXML
    Label title;

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXButton submitButton;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        deleteIcon.setText("\ue606");

        String name = userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID());
        Username.setText(name);

        Inventory.getItems().addAll(inventoryBLService.showInventory());
        initTable();
    }
    public void addInventoryBill(){
        String ID = inventoryBLService.getNewBillIDByType(type);
        isNew = true;
        BillID.setText(ID);
    }
    public void initTable(){
        table = new TableView<>();
        table.setEditable(false);

        TableColumn nameColumn = new TableColumn("商品名称");
        nameColumn.setPrefWidth(140);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(140);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn amountColumn = new TableColumn("当前数量");
        amountColumn.setPrefWidth(82);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn<GoodsBean, Integer> newAmountColumn = new TableColumn("调整数量");
        newAmountColumn.setPrefWidth(85);
        newAmountColumn.setCellValueFactory(new PropertyValueFactory<>("newAmount"));

        newAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        newAmountColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<GoodsBean, Integer> t)->{
                    GoodsBean bean =  (GoodsBean) t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    bean.setAmount(t.getNewValue());
                    for (GoodsVO goodsVO:goodsItems.keySet()){
                        if (goodsVO.ID == bean.getID()){
                            goodsItems.put(goodsVO,t.getNewValue());
                        }
                    }
                });

        table.setItems(data);
        table.getColumns().addAll(nameColumn,modelColumn,amountColumn,newAmountColumn);
        vbox.getChildren().add(table);
    }
    public void setInventorySyncController(InventorySyncController inventorySyncController){
        this.inventorySyncController = inventorySyncController;
    }
    public void clickAddButton(){
        GoodsSelecter selecter = new GoodsSelecter();
        Dialog dialog = selecter.getGoodsDialog();
        Optional<GoodsBean> result = dialog.showAndWait();

        GoodsBean bean = null;
        if (result.isPresent()){
            bean = result.get();
            data.add(bean);
            goodsItems.put(new GoodsVO(bean.getID()),0);
        }

    }
    public void clickSubmitButton(){
        InventoryBillVO vo = new InventoryBillVO(BillID.getText(), type, BillState.SUBMITTED, LocalDate.now().toString(),
                Inventory.getSelectionModel().getSelectedItem().toString(),userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID()),
                goodsItems);
        if (isNew == true){
            inventoryBLService.submitBill(vo);
        }else {
            inventoryBLService.updateBill(vo);
        }
    }
    public void clickCancelButton(){
        Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("需要保存为草稿吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
                String inventory = "";
                if (Inventory.getSelectionModel().getSelectedIndex() >= 0){
                    inventory = Inventory.getSelectionModel().getSelectedItem().toString();
                }
                InventoryBillVO vo = new InventoryBillVO(BillID.getText(), type, BillState.SUBMITTED, LocalDate.now().toString(),
                        inventory,userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID()),
                        goodsItems);

                if (isNew == true){
                    inventoryBLService.addBill(vo);
                }else{
                    inventoryBLService.updateBill(vo);
                }
            }

            inventorySyncController.showInventoryBills();
        }
    }
    public void clickDeleteButton(){

    }
    public void setType(BillType type){
        this.type = type;
    }

    public void setForDetailView(InventoryBillVO vo) {
        addIcon.setVisible(false);
        deleteIcon.setVisible(false);

        title.setText("单据详情");
        isNew = false;
        BillID.setText(vo.ID);

        String inventory = vo.inventory;
        Inventory.getItems().clear();
        Inventory.setEditable(false);
        Inventory.getItems().add(inventory);
        Inventory.getSelectionModel().selectFirst();

        Username.setText(vo.user);

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                inventorySyncController.showInventoryBills();
            }
        });

        if(vo.state == BillState.DRAFT){
            submitButton.setText("编 辑");
            submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setForEditView();
                }
            });
        }
    }
    public void setForEditView(){
        addIcon.setVisible(true);
        deleteIcon.setVisible(true);
        title.setText("编辑草稿单");

        Inventory.setEditable(true);
        Inventory.getItems().clear();
        Inventory.getItems().addAll(inventoryBLService.showInventory());

        submitButton.setText("提 交");
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickSubmitButton();
            }
        });

        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                clickCancelButton();
            }
        });
    }
}
