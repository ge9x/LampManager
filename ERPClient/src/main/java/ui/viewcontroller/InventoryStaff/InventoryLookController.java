package ui.viewcontroller.InventoryStaff;

import bean.AlarmBean;
import bean.ItemBean;
import bl.inventorybl.InventoryController;
import blservice.inventoryblservice.InventoryBLService;
import blstubdriver.InventoryBLService_Stub;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.*;
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
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryViewItemVO;
import vo.InventoryViewVO;

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
    ArrayList<InventoryBillVO> alarmBills;
    ArrayList<String> inventories;


    Table<AlarmBean> alarmTable;
    Table<ItemBean> inventoryItemTable;
    Table<ItemBean> salesItemTable;

    ObservableList<ItemBean> inventoryItemData = FXCollections.observableArrayList();
    ObservableList<ItemBean> salesItemData = FXCollections.observableArrayList();

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
    public void initialize(){
        AlertIcon.setText("\ue6be");
        addIcon.setText("\ue61e");
        tilePane.setPrefColumns(2);

        getInfos();
        initAlarmTable();
        initItemTable();

        showAlarmTable();
        showItemTable();
    }

    public boolean getInfos(){
        inventories = inventoryBLService.showInventory();

        alarmTable = new Table<>();
        salesItemTable = new Table<>();
        inventoryItemTable = new Table<>();

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("仓库名称"));
        labels.add(new Label("开始日期"));
        labels.add(new Label("结束日期"));

        ArrayList<Node> nodes = new ArrayList<Node>();
        JFXComboBox<String> comboBox= new JFXComboBox<>();
        comboBox.getItems().addAll(inventories);
        JFXDatePicker startDate = new JFXDatePicker();
        JFXDatePicker endDate = new JFXDatePicker();
        nodes.add(comboBox);
        nodes.add(startDate);
        nodes.add(endDate);

        Dialog<ArrayList<String>> dialog = DialogFactory.createDialog(labels,nodes);
        dialog.setHeaderText("请选择查看的仓库名称和时间段");

        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == ButtonType.FINISH) {
                ArrayList<String> result = new ArrayList<>();
                result.add(comboBox.getValue());
                result.add(startDate.getValue().toString());
                result.add(endDate.getValue().toString());
                return result;
            }
            return null;
        });

        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            if (result.get() != null){
                ArrayList<String> results = (ArrayList<String>)result.get();
                inventory = results.get(0);
                StartDate.setValue(LocalDate.parse(results.get(1)));
                EndDate.setValue(LocalDate.parse(results.get(2)));
                return true;
            }
        }
            return false;
    }
    public void initAlarmTable(){
        alarmTable.addColumn("编号","ID",160);
        alarmTable.addColumn("商品名称","name",160);
        alarmTable.addColumn("当前库存数量","currentNum",100);
        alarmTable.addColumn("警戒数量","alarmNum",100);
        alarmTable.addColumn("最少进货数量","minusNum",97);

        AlarmTablePane.setContent(alarmTable.getTable());
    }
    public void initItemTable(){
        salesItemTable.addColumn("时间","date",60);
        salesItemTable.addColumn("商品名称","name",60);
        salesItemTable.addColumn("I/O","isIn",60);
        salesItemTable.addColumn("数量","amount",60);
        salesItemTable.addColumn("金额","money",55);

        inventoryItemTable.addColumn("时间","date",60);
        inventoryItemTable.addColumn("商品名称","name",60);
        inventoryItemTable.addColumn("I/O","isIn",60);
        inventoryItemTable.addColumn("数量","amount",60);
        inventoryItemTable.addColumn("金额","money",55);

        InventoryItemTablePane.setContent(inventoryItemTable.getTable());
        SalesItemTablePane.setContent(salesItemTable.getTable());
    }

    public void showAlarmTable(){
        alarmBills = inventoryBLService.showAlarmBills();
        for (InventoryBillVO vo : alarmBills){
            for (GoodsVO good : vo.goodsMap.keySet()){
                int num = vo.goodsMap.get(good);
                alarmTable.addRow(new AlarmBean(good.ID,good.name,good.amount,num,num-good.amount));
            }
        }
    }
    public void showItemTable(){
        inventoryViewVO = inventoryBLService.show(StartDate.getValue().toString(), EndDate.getValue().toString(), inventory);
        ArrayList<InventoryViewItemVO> items = inventoryViewVO.item;
        for (InventoryViewItemVO item : items){
            if (item.type == InventoryListItemType.IN){
                inventoryItemTable.addRow(new ItemBean(item.date,item.goods.name,"I",item.amount,item.price));
                stringAdd(inNum,item.amount);
                stringAdd(inMoney,item.price);
            }else if (item.type == InventoryListItemType.OUT){
                inventoryItemTable.addRow(new ItemBean(item.date, item.goods.name, "O", item.amount, item.price));
                stringAdd(outNum,item.amount);
                stringAdd(outMoney,item.price);
            }else if (item.type == InventoryListItemType.PURCHASE){
                salesItemTable.addRow(new ItemBean(item.date, item.goods.name, "I", item.amount, item.price));
                stringAdd(purNum,item.amount);
                stringAdd(purMoney,item.price);
            }else if (item.type == InventoryListItemType.SALES){
                salesItemTable.addRow(new ItemBean(item.date, item.goods.name, "O", item.amount, item.price));
                stringAdd(salNum,item.amount);
                stringAdd(salMoney,item.price);
            }
        }

    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }
    public void clickAddButton(){
        Dialog dialog = DialogFactory.getTextInputDialog();
        dialog.setHeaderText("请输入新的仓库名称");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            inventoryBLService.addInventory(result.get());
        }
    }



    private void stringAdd(Label label, double a){
        if (label.getText() == "")
            label.setText("0.00");
        label.setText((Double.parseDouble(label.getText()) + a)+"");
    }
}
