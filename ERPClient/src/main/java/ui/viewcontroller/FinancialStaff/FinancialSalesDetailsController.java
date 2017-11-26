package ui.viewcontroller.FinancialStaff;

import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.FormBLService;
import blstubdriver.FinanceBLService_Stub;
import blstubdriver.FormBLService_Stub;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import vo.SalesDetailVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialSalesDetailsController {
    FinancialViewController financialViewController;
    FormBLService formBLService = new FormBLService_Stub();
    ArrayList<SalesDetailVO> salesDetails = new ArrayList<>();

    TableView<SalesDetailsBean> table = new TableView<SalesDetailsBean>();
    ObservableList<SalesDetailsBean> data = FXCollections.observableArrayList();
    @FXML
    Label searchIcon;
    @FXML
    AnchorPane TablePane;
    @FXML
    ScrollPane TableScrollPane;
    @FXML
    ComboBox Filters;


    public void initialize(){
        searchIcon.setText("\ue69d");
        table = new TableView<>();
        table.setEditable(false);

        Filters.getItems().addAll("商品名","客户","业务员","仓库");
        Filters.getSelectionModel().selectFirst();

        TableColumn dateColumn = new TableColumn("时间");
        dateColumn.setPrefWidth(136);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn nameColumn = new TableColumn("商品名称");
        nameColumn.setPrefWidth(136);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(136);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn amountColumn = new TableColumn("数量");
        amountColumn.setPrefWidth(68);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn priceolumn = new TableColumn("单价");
        priceolumn.setPrefWidth(68);
        priceolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn sumColumn = new TableColumn("总额");
        sumColumn.setPrefWidth(130);
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));

        table.setItems(data);
        table.getColumns().addAll(dateColumn,nameColumn,modelColumn,amountColumn,priceolumn,sumColumn);
        TableScrollPane.setContent(table);

    }

    public void showSalesdetails(){
//        salesDetails = formBLService.getSalesDetails();
    }

    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

    public class SalesDetailsBean{
        StringProperty date;
        StringProperty name;
        StringProperty model;
        IntegerProperty amount;
        DoubleProperty price;
        DoubleProperty sum;

        public SalesDetailsBean(String date,String name,String model,int amount,double price,double sum) {
            this.date = new SimpleStringProperty(date);
            this.name = new SimpleStringProperty(name);
            this.model = new SimpleStringProperty(model);
            this.amount = new SimpleIntegerProperty(amount);
            this.price = new SimpleDoubleProperty(price);
            this.sum = new SimpleDoubleProperty(sum);
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

        public String getModel() {
            return model.get();
        }

        public StringProperty modelProperty() {
            return model;
        }

        public void setModel(String model) {
            this.model.set(model);
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

        public double getPrice() {
            return price.get();
        }

        public DoubleProperty priceProperty() {
            return price;
        }

        public void setPrice(double price) {
            this.price.set(price);
        }

        public double getSum() {
            return sum.get();
        }

        public DoubleProperty sumProperty() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum.set(sum);
        }
    }
}
