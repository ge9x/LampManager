package ui.viewcontroller.InventoryStaff;

import bean.AlarmBean;
import bean.ItemBean;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryController;
import blservice.inventoryblservice.InventoryBLService;
import blstubdriver.InventoryBLService_Stub;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import ui.component.DialogFactory;
import ui.component.Table;
import util.InventoryListItemType;
import util.Money;
import vo.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.transform.Result;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryLookController {
    InventoryViewController inventoryViewController;
    InventoryBLService inventoryBLService = new InventoryController();


    String inventory;
    InventoryViewVO inventoryViewVO;
    ArrayList<AlarmVO> alarms;
    ArrayList<String> inventories;


    Table<AlarmBean> alarmTable;
    Table<ItemBean> inventoryItemTable;
    Table<ItemBean> salesItemTable;

    ObservableList<ItemBean> inventoryItemData = FXCollections.observableArrayList();
    ObservableList<ItemBean> salesItemData = FXCollections.observableArrayList();

    int iNum = 0,oNum = 0,pNum = 0,sNum = 0,ioNumTotal = 0,psNumTotal = 0;
    double iMoney = 0,oMoney = 0,pMoney = 0,sMoney = 0,ioMoneyTotal = 0,psMoneyTotal = 0;

    @FXML
    TilePane tilePane;

    @FXML
    Label AlertIcon, addIcon, inNum, outNum, inMoney, outMoney, inoutNumTotal, inoutMoneyTotal;

    @FXML
    Label purNum, salNum, purMoney, salMoney, pursalNumTotal, pursalMoneyTotal;

    @FXML
    ScrollPane AlarmTablePane;

    @FXML
    ScrollPane InventoryItemTablePane;

    @FXML
    ScrollPane SalesItemTablePane;

    @FXML
    JFXDatePicker StartDate;

    @FXML
    JFXDatePicker EndDate;

    @FXML
    JFXComboBox<String> InventoryBox;

    @FXML
    public void initialize() {
        AlertIcon.setText("\ue6be");
        addIcon.setText("\ue61e");
        tilePane.setPrefColumns(2);

        InventoryBox.getItems().addAll(inventoryBLService.showInventory());
        InventoryBox.getSelectionModel().selectFirst();

        StartDate.setValue(LocalDate.parse(inventoryBLService.getStartDate()));
        EndDate.setValue(LocalDate.now());

        InventoryBox.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<String>>() {
            @Override
            public void changed(ObservableValue<? extends SingleSelectionModel<String>> observable, SingleSelectionModel<String> oldValue, SingleSelectionModel<String> newValue) {
                showAlarmTable();
                showItemTable();
            }
        });
        StartDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showItemTable();
            }
        });
        EndDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showItemTable();
            }
        });
        initAlarmTable();
        initItemTable();

        showAlarmTable();
        showItemTable();
    }

    public void initAlarmTable() {
        alarmTable = new Table<>();
        alarmTable.addColumn("编号", "ID", 160);
        alarmTable.addColumn("商品名称", "name", 160);
        alarmTable.addColumn("当前库存数量", "currentNum", 100);
        alarmTable.addColumn("警戒数量", "alarmNum", 100);
        alarmTable.addColumn("最少进货数量", "minusNum", 97);

        AlarmTablePane.setContent(alarmTable.getTable());
    }

    public void initItemTable() {
        salesItemTable = new Table<>();
        salesItemTable.getTable().setPrefHeight(330);
        inventoryItemTable = new Table<>();
        inventoryItemTable.getTable().setPrefHeight(330);

        salesItemTable.addColumn("时间", "date", 60);
        salesItemTable.addColumn("商品名称", "name", 60);
        salesItemTable.addColumn("I/O", "isIn", 60);
        salesItemTable.addColumn("数量", "amount", 60);
        salesItemTable.addColumn("金额", "money", 55);

        inventoryItemTable.addColumn("时间", "date", 60);
        inventoryItemTable.addColumn("商品名称", "name", 60);
        inventoryItemTable.addColumn("I/O", "isIn", 60);
        inventoryItemTable.addColumn("数量", "amount", 60);
        inventoryItemTable.addColumn("金额", "money", 55);

        InventoryItemTablePane.setContent(inventoryItemTable.getTable());
        SalesItemTablePane.setContent(salesItemTable.getTable());
    }

    public void showAlarmTable() {
        inventory = InventoryBox.getSelectionModel().getSelectedItem();
        alarms = inventoryBLService.getAlarmByInventory(inventory);
        alarmTable.clear();
        for (AlarmVO vo : alarms) {
            alarmTable.addRow(new AlarmBean(vo.goodsID, vo.goodsName, vo.alarmNumber, vo.goodsNumber, vo.numberSuggestAdding));
        }
    }

    public void showItemTable() {
        inventoryViewVO = inventoryBLService.show(StartDate.getValue().toString(), EndDate.getValue().toString(), inventory);
        ArrayList<InventoryViewItemVO> items = inventoryViewVO.item;
        inventoryItemTable.clear();
        salesItemTable.clear();
        for (InventoryViewItemVO item : items) {
            if (item.type == InventoryListItemType.IN) {
                inventoryItemTable.addRow(new ItemBean(item.date, item.goods.name, "I", item.amount, item.price));
                iNum += item.amount;
                iMoney += item.price;
            } else if (item.type == InventoryListItemType.OUT) {
                inventoryItemTable.addRow(new ItemBean(item.date, item.goods.name, "O", item.amount, item.price));
                oNum +=item.amount;
                oMoney+= item.price;
            } else if (item.type == InventoryListItemType.PURCHASE) {
                salesItemTable.addRow(new ItemBean(item.date, item.goods.name, "I", item.amount, item.price));
                pNum += item.amount;
                pMoney += item.price;
            } else if (item.type == InventoryListItemType.SALES) {
                salesItemTable.addRow(new ItemBean(item.date, item.goods.name, "O", item.amount, item.price));
                sNum += item.amount;
                sMoney += item.price;
            }
        }
        ioNumTotal = iNum + oNum;
        ioMoneyTotal = iMoney + oMoney;
        psNumTotal = pNum + sNum;

        inNum.setText(iNum+"");
        outNum.setText(oNum+"");
        inMoney.setText(Money.getMoneyString(iMoney));
        outMoney.setText(Money.getMoneyString(oMoney));
        inoutMoneyTotal.setText(Money.getMoneyString(ioMoneyTotal));
        inoutNumTotal.setText(ioNumTotal + "");
        pursalNumTotal.setText(psNumTotal+"");
        pursalMoneyTotal.setText(Money.getMoneyString(psMoneyTotal));
        purNum.setText(pNum + "");
        salNum.setText(sNum+"");
        purMoney.setText(Money.getMoneyString(pMoney));
        salMoney.setText(Money.getMoneyString(sMoney));
    }

    public void setInventoryViewController(InventoryViewController inventoryViewController) {
        this.inventoryViewController = inventoryViewController;
    }

    public void clickAddButton() {
        Dialog dialog = DialogFactory.getTextInputDialog();
        dialog.setHeaderText("请输入新的仓库名称");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            inventoryBLService.addInventory(result.get());
        }
    }
}
