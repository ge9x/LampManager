package ui.viewcontroller.InventoryStaff;

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
    InventoryBLService inventoryBLService = new InventoryBLService_Stub();


    String inventory;
    InventoryViewVO inventoryViewVO;
    ArrayList<InventoryBillVO> alarmBills;
    ArrayList<String> inventories;


    TableView<AlarmBean> alarmTable;
    TableView<ItemBean> inventoryItemTable;
    TableView<ItemBean> salesItemTable;

    ObservableList<AlarmBean> alarmData = FXCollections.observableArrayList();
    ObservableList<ItemBean> inventoryItemData = FXCollections.observableArrayList();
    ObservableList<ItemBean> salesItemData = FXCollections.observableArrayList();

    @FXML
    TilePane tilePane;

    @FXML
    Label AlertIcon, addIcon;

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
        alarmTable = new TableView<>();
        alarmTable.setEditable(false);

        TableColumn IDColumn = new TableColumn("编号");
        TableColumn nameColumn = new TableColumn("商品名称");
        TableColumn currentColumn = new TableColumn("当前库存数量");
        TableColumn alarmColumn = new TableColumn("警戒数量");
        TableColumn minColumn = new TableColumn("最少进货数量");

        IDColumn.setPrefWidth(160);
        nameColumn.setPrefWidth(160);
        currentColumn.setPrefWidth(100);
        alarmColumn.setPrefWidth(100);
        minColumn.setPrefWidth(97);

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("currentNum"));
        alarmColumn.setCellValueFactory(new PropertyValueFactory<>("alarmNum"));
        minColumn.setCellValueFactory(new PropertyValueFactory<>("minusNum"));

        alarmTable.setItems(alarmData);
        alarmTable.getColumns().addAll(IDColumn,nameColumn,currentColumn,alarmColumn,minColumn);
        AlarmTablePane.setContent(alarmTable);


    }
    public void initItemTable(){
        salesItemTable = new TableView<>();
        inventoryItemTable = new TableView<>();
        salesItemTable.setEditable(false);
        inventoryItemTable.setEditable(false);



        TableColumn dateColumn = new TableColumn("时间");
        TableColumn nameColumn = new TableColumn("商品名称");
        TableColumn isInColumn = new TableColumn("I/O");
        TableColumn amountColumn = new TableColumn("数量");
        TableColumn moneyColumn = new TableColumn("金额");

        dateColumn.setPrefWidth(60);
        nameColumn.setPrefWidth(60);
        isInColumn.setPrefWidth(60);
        amountColumn.setPrefWidth(60);
        moneyColumn.setPrefWidth(55);

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isInColumn.setCellValueFactory(new PropertyValueFactory<>("isIn"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));

        inventoryItemTable.getColumns().addAll(dateColumn,nameColumn,isInColumn,amountColumn,moneyColumn);
        salesItemTable.getColumns().addAll(dateColumn, nameColumn, isInColumn, amountColumn, moneyColumn);

        InventoryItemTablePane.setContent(inventoryItemTable);
        SalesItemTablePane.setContent(salesItemTable);
    }

    public void showAlarmTable(){
        alarmBills = inventoryBLService.showAlarmBills();
        for (InventoryBillVO vo : alarmBills){
            for (GoodsVO good : vo.goodsMap.keySet()){
                int num = vo.goodsMap.get(good);
                alarmData.add(new AlarmBean(good.ID,good.name,good.amount,num,num-good.amount));
            }
        }
    }
    public void showItemTable(){
        inventoryViewVO = inventoryBLService.show(StartDate.getValue().toString(), EndDate.getValue().toString(), inventory);
        ArrayList<InventoryViewItemVO> items = inventoryViewVO.item;
        for (InventoryViewItemVO item : items){
            if (item.type == InventoryListItemType.IN){
                inventoryItemData.add(new ItemBean(item.goods.ID,item.goods.name,"I",item.amount,item.price));
            }else if (item.type == InventoryListItemType.OUT){
                inventoryItemData.add(new ItemBean(item.goods.ID,item.goods.name,"O",item.amount,item.price));
            }else if (item.type == InventoryListItemType.PURCHASE){
                salesItemData.add(new ItemBean(item.goods.ID,item.goods.name,"I",item.amount,item.price));
            }else if (item.type == InventoryListItemType.SALES){
                salesItemData.add(new ItemBean(item.goods.ID,item.goods.name,"O",item.amount,item.price));
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
    public class AlarmBean{
        StringProperty ID;
        StringProperty name;
        IntegerProperty currentNum;
        IntegerProperty alarmNum;
        IntegerProperty minusNum;

        public AlarmBean(String ID,String name,int currentNum,int alarmNum,int minusNum) {
            this.ID = new SimpleStringProperty(ID);
            this.name = new SimpleStringProperty(name);
            this.currentNum = new SimpleIntegerProperty(currentNum);
            this.alarmNum = new SimpleIntegerProperty(alarmNum);
            this.minusNum = new SimpleIntegerProperty(minusNum);
        }

        public String getID() {
            return ID.get();
        }

        public StringProperty IDProperty() {
            return ID;
        }

        public void setID(String ID) {
            this.ID.set(ID);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public int getCurrentNum() {
            return currentNum.get();
        }

        public IntegerProperty currentNumProperty() {
            return currentNum;
        }

        public void setCurrentNum(int cuurentNum) {
            this.currentNum.set(cuurentNum);
        }

        public int getAlarmNum() {
            return alarmNum.get();
        }

        public IntegerProperty alarmNumProperty() {
            return alarmNum;
        }

        public void setAlarmNum(int alarmNum) {
            this.alarmNum.set(alarmNum);
        }

        public int getMinusNum() {
            return minusNum.get();
        }

        public IntegerProperty minusNumProperty() {
            return minusNum;
        }

        public void setMinusNum(int minusNum) {
            this.minusNum.set(minusNum);
        }
    }

    public class ItemBean{
        StringProperty date;
        StringProperty name;
        StringProperty isIn;
        IntegerProperty amount;
        DoubleProperty money;

        public ItemBean(String date, String name, String isIn, Integer amount, Double money) {
            this.date = new SimpleStringProperty(date);
            this.name = new SimpleStringProperty(name);
            this.isIn = new SimpleStringProperty(isIn);
            this.amount = new SimpleIntegerProperty(amount);
            this.money = new SimpleDoubleProperty(money);
        }

        public String getDate() {
            return date.get();
        }

        public StringProperty dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }


        public String getIsIn() {
            return isIn.get();
        }

        public StringProperty isInProperty() {
            return isIn;
        }

        public void setIsIn(String isIn) {
            this.isIn.set(isIn);
        }

        public int getAmount() {
            return amount.get();
        }

        public IntegerProperty amountProperty() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount.set(amount);
        }

        public double getMoney() {
            return money.get();
        }

        public DoubleProperty moneyProperty() {
            return money;
        }

        public void setMoney(double money) {
            this.money.set(money);
        }
    }

}
