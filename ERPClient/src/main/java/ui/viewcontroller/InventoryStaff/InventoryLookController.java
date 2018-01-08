package ui.viewcontroller.InventoryStaff;

import bean.AlarmBean;
import bean.ItemBean;
import bl.inventorybl.InventoryBLFactory;
import blservice.inventoryblservice.InventoryBLService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSpinner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import ui.component.DialogFactory;
import ui.component.Table;
import ui.viewcontroller.common.MainUIController;
import util.InventoryListItemType;
import util.Money;
import util.ResultMessage;
import vo.*;

import javax.xml.transform.Result;
import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryLookController {
    InventoryViewController inventoryViewController;
    InventoryBLService inventoryBLService = InventoryBLFactory.getBLService();

    String inventory;
    InventoryViewVO inventoryViewVO;
    ArrayList<AlarmVO> alarms;
    ArrayList<String> inventories;


    Table<AlarmBean> alarmTable;
    Table<ItemBean> inventoryItemTable;
    Table<ItemBean> salesItemTable;

    ObservableList<ItemBean> inventoryItemData = FXCollections.observableArrayList();
    ObservableList<ItemBean> salesItemData = FXCollections.observableArrayList();
    private Executor executor;

    int iNum = 0,oNum = 0,pNum = 0,sNum = 0,ioNumTotal = 0,psNumTotal = 0;
    double iMoney = 0,oMoney = 0,pMoney = 0,sMoney = 0,ioMoneyTotal = 0,psMoneyTotal = 0;

    @FXML
    Label AlertIcon, addIcon, inNum, outNum, inMoney, outMoney, inoutNumTotal, inoutMoneyTotal;

    @FXML
    Label purNum, salNum, purMoney, salMoney, pursalNumTotal, pursalMoneyTotal;

    @FXML
    StackPane Page;

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
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        AlertIcon.setText("\ue6be");
        addIcon.setText("\ue61e");

        inventories = inventoryBLService.showInventory();
        InventoryBox.getItems().addAll(inventories);
        InventoryBox.getSelectionModel().selectFirst();

        iNum = 0;oNum = 0;pNum = 0;sNum = 0;ioNumTotal = 0;psNumTotal = 0;
        iMoney = 0;oMoney = 0;pMoney = 0;sMoney = 0;ioMoneyTotal = 0;psMoneyTotal = 0;

        initDatePicker();
        initAlarmTable();
        initItemTable();

        showAlarmTable();
        showItemTable();

        InventoryBox.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<String>>() {
            @Override
            public void changed(ObservableValue<? extends SingleSelectionModel<String>> observable, SingleSelectionModel<String> oldValue, SingleSelectionModel<String> newValue) {
                showAlarmTable();
                showItemTable();
            }
        });

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

        salesItemTable.addColumn("时间", "date", 150);
        salesItemTable.addColumn("商品名称", "name", 200);
        salesItemTable.addColumn("I/O", "isIn", 100);
        salesItemTable.addColumn("数量", "amount", 100);
        salesItemTable.addColumn("金额", "money", 150);

        inventoryItemTable.addColumn("时间", "date", 150);
        inventoryItemTable.addColumn("商品名称", "name", 200);
        inventoryItemTable.addColumn("I/O", "isIn", 100);
        inventoryItemTable.addColumn("数量", "amount", 100);
        inventoryItemTable.addColumn("金额", "money", 150);

        InventoryItemTablePane.setContent(inventoryItemTable.getTable());
        SalesItemTablePane.setContent(salesItemTable.getTable());
    }

    public void showAlarmTable() {
        inventory = InventoryBox.getSelectionModel().getSelectedItem();

        Task<ArrayList<AlarmVO>> task = new Task<ArrayList<AlarmVO>>() {
            @Override
            protected ArrayList<AlarmVO> call() throws Exception {
                return inventoryBLService.getAlarmByInventory(inventory);
            }
        };

        task.setOnSucceeded(e -> {
            alarms = task.getValue();
            alarmTable.clear();
            for (AlarmVO vo : alarms) {
                alarmTable.addRow(new AlarmBean(vo.goodsID, vo.goodsName,  vo.goodsNumber, vo.alarmNumber,vo.numberSuggestAdding));
            }
        });

        executor.execute(task);
    }

    public void showItemTable() {
        inventoryViewVO = inventoryBLService.show(StartDate.getValue().toString(), EndDate.getValue().toString(), inventory);

        ArrayList<InventoryViewItemVO> items = inventoryViewVO.item;
        inventoryItemTable.clear();
        salesItemTable.clear();
        for (InventoryViewItemVO item : items) {
            if (item.type == InventoryListItemType.IN) {
                inventoryItemTable.addRow(new ItemBean(item.date, item.goods.name, "I", item.amount,  Money.getMoneyString(item.price)));
                iNum += item.amount;
                iMoney += item.price;
            } else if (item.type == InventoryListItemType.OUT) {
                inventoryItemTable.addRow(new ItemBean(item.date, item.goods.name, "O", item.amount, Money.getMoneyString(item.price)));
                oNum +=item.amount;
                oMoney+= item.price;
            } else if (item.type == InventoryListItemType.PURCHASE) {
                salesItemTable.addRow(new ItemBean(item.date, item.goods.name, "I", item.amount,  Money.getMoneyString(item.price)));
                pNum += item.amount;
                pMoney += item.price;
            } else if (item.type == InventoryListItemType.SALES) {
                salesItemTable.addRow(new ItemBean(item.date, item.goods.name, "O", item.amount,  Money.getMoneyString(item.price)));
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
            ResultMessage re = inventoryBLService.addInventory(result.get());
            dialog = DialogFactory.getInformationAlert();
            if (re == ResultMessage.SUCCESS){
                dialog.setHeaderText("添加仓库成功");
            }
            else if (ResultMessage.EXIST == re){
                dialog.setHeaderText("仓库已存在");
            }
            dialog.showAndWait();
        }
    }

    private void initDatePicker(){
        /**
         * 设定初始值
         */
        StartDate.setValue(LocalDate.parse(inventoryBLService.getStartDate()));
        EndDate.setValue(LocalDate.now());


        /**
         * 监听日期变更
         */
        StartDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (EndDate.getValue().isBefore(newValue)){
                    EndDate.setValue(newValue);
                }
                showItemTable();
            }
        });

        EndDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showItemTable();
            }
        });


        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isBefore(
                                        StartDate.getValue())
                                        ) {
                                    setDisable(true);
                                }
                            }
                        };
                    }
                };
        EndDate.setDayCellFactory(dayCellFactory);
    }
}
