package ui.viewcontroller.SalesStaff;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import blservice.salesblservice.SalesBLService;
import blservice.userblservice.UserBLService;
import blstubdriver.SalesBLService_Stub;
import blstubdriver.UserBLService_Stub;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ui.component.DialogFactory;
import ui.viewcontroller.SalesStaff.SalesStaffPurchaseEditViewController.GoodsItemBean;
import util.BillState;
import util.BillType;
import util.Money;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class SalesStaffSalesEditViewController {
	SalesStaffSalesOrderViewController salesStaffSalesOrderViewController;
	
	SalesBLService salesBLService = new SalesBLService_Stub();
	UserBLService userBLService = new UserBLService_Stub();
	ArrayList<GoodsItemVO> goodsItemList = new ArrayList<GoodsItemVO>();
	ArrayList<CustomerVO> customers = new ArrayList<CustomerVO>();
	ArrayList<String> inventories = new ArrayList<String>();
	ArrayList<PromotionVO> promotions = new ArrayList<PromotionVO>();
	
	TableView<GoodsItemBean> itemTable;
    ObservableList<GoodsItemBean> data =
            FXCollections.observableArrayList();
    DoubleProperty total = new SimpleDoubleProperty(0);
    DoubleProperty afterSum = new SimpleDoubleProperty(0);
    
    @FXML
    Label addIcon;

    @FXML
    Label BillID;

    @FXML
    Text Username;
    
    @FXML
    Text Salesman;

    @FXML
    VBox vbox;

    @FXML
    Text Total;
    
    @FXML
    Text beforeSum;
    
    @FXML
    JFXTextArea remark;
    
    @FXML
    JFXTextField allowance;
    
    @FXML
    JFXTextField voucher;
    
    @FXML
    JFXComboBox inventory;
    
    @FXML
    JFXComboBox customer;
    
    @FXML
    JFXComboBox promotion;
    
    public void initialize(){
        addIcon.setText("\ue61e");
        String name = userBLService.findUserByID(userBLService.getCurrentUserID()).name;
        Username.setText(name);
        customers = salesBLService.getAllCustomer();
        inventories = salesBLService.getAllInventory();
        promotions.addAll(salesBLService.showBargains());
        promotions.addAll(salesBLService.getFitPromotionCustomer());
        promotions.addAll(salesBLService.getFitPromotionTotal());

        //初始化supplier选择框
        ArrayList<String> customerNames = new ArrayList<>();
        for (CustomerVO temp : customers){
            customerNames.add(temp.customerName);
        }
        customer.getItems().addAll(customerNames);
        
        //初始化inventory选择框
        inventory.getItems().addAll(inventories);
        
        //初始化promotion选择框
        ArrayList<String> promotionNames = new ArrayList<String>();
        for (PromotionVO temp : promotions){
            promotionNames.add(temp.promotionName);
        }
        promotion.getItems().addAll(promotionNames);

        //初始化表格
        itemTable = new TableView<>();
        itemTable.setEditable(false);

        TableColumn IDColumn = new TableColumn("商品编号");
        IDColumn.setPrefWidth(70);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn nameColumn = new TableColumn("条目名");
        nameColumn.setPrefWidth(60);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(60);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn amountColumn = new TableColumn("数量");
        amountColumn.setPrefWidth(60);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn retailPriceColumn = new TableColumn("单价");
        retailPriceColumn.setPrefWidth(60);
        retailPriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        TableColumn totalPriceColumn = new TableColumn("总价");
        totalPriceColumn.setPrefWidth(60);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        TableColumn remarkColumn = new TableColumn("备注");
        remarkColumn.setPrefWidth(78);
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        itemTable.setItems(data);
        itemTable.getColumns().addAll(IDColumn, nameColumn, modelColumn, amountColumn, retailPriceColumn, totalPriceColumn, remarkColumn);
        vbox.getChildren().add(itemTable);

        //折让前总额Text与商品总额金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                beforeSum.setText(Money.getMoneyString(total.get()));
            }
        });
        
        //折让后总额Text与代金券和折让绑定
        afterSum.addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				Total.setText(Money.getMoneyString(afterSum.get()));
			}
        	
        });
        
        allowance.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(allowance.getText().length()>0&&voucher.getText().length()>0){
					afterSum.set(total.get()-Double.parseDouble(allowance.getText())-Double.parseDouble(voucher.getText()));
				}
				else if(allowance.getText().length()<=0&&voucher.getText().length()<=0){
					afterSum.set(total.get());
				}
				else if(allowance.getText().length()<=0){
					afterSum.set(total.get()-Double.parseDouble(voucher.getText()));
				}
				else{
					afterSum.set(total.get()-Double.parseDouble(allowance.getText()));
				}
			}      	
        });

        voucher.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(allowance.getText().length()>0&&voucher.getText().length()>0){
					afterSum.set(total.get()-Double.parseDouble(allowance.getText())-Double.parseDouble(voucher.getText()));
				}
				else if(allowance.getText().length()<=0&&voucher.getText().length()<=0){
					afterSum.set(total.get());
				}
				else if(allowance.getText().length()<=0){
					afterSum.set(total.get()-Double.parseDouble(voucher.getText()));
				}
				else{
					afterSum.set(total.get()-Double.parseDouble(allowance.getText()));
				}
			}      	
        });
    }
    
    public void addSalesOrder() {
        String ID = salesBLService.getnewSalesID();
        BillID.setText(ID);
    }

    public void clickAddButton(){
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("商品编号"));
        labels.add(new Label("条目名"));
        labels.add(new Label("型号"));
        labels.add(new Label("数量"));
        labels.add(new Label("单价"));
        labels.add(new Label("总价"));
        labels.add(new Label("备注"));

        ArrayList<Node> nodes = new ArrayList<Node>();
        TextField IDTF = new TextField();
        TextField nameTF = new TextField();
        TextField modelTF = new TextField();
        TextField amountTF = new TextField();
        TextField retailPriceTF = new TextField();
        TextField totalPriceTF = new TextField();
        TextField remarkTF = new TextField();
        nodes.add(IDTF);
        nodes.add(nameTF);
        nodes.add(modelTF);
        nodes.add(amountTF);
        nodes.add(retailPriceTF);
        nodes.add(totalPriceTF);
        nodes.add(remarkTF);

        Dialog dialog = DialogFactory.createDialog(labels,nodes);
        dialog.setResultConverter(dialogButton -> {
            ArrayList<String> result = new ArrayList<>();
            result.add(IDTF.getText());
            result.add(nameTF.getText());
            result.add(modelTF.getText());
            result.add(amountTF.getText());
            result.add(retailPriceTF.getText());
            result.add(totalPriceTF.getText());
            result.add(remarkTF.getText());
            if (dialogButton == ButtonType.FINISH) {
                return result;
            }
            return null;
        });

        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            ArrayList<String> values = (ArrayList<String>)result.get();
            Double money = Double.parseDouble(values.get(5));
            int amount = Integer.parseInt(values.get(3));
            double totalPrice = Double.parseDouble(values.get(5));
            GoodsItemVO GoodsItemVO = new GoodsItemVO(values.get(0),values.get(1),values.get(2),amount,money,values.get(6));

            goodsItemList.add(GoodsItemVO);
            data.add(new GoodsItemBean(values.get(0),values.get(1),values.get(2),amount,money,totalPrice,values.get(6)));
            total.set(total.get()+money);
            afterSum.set(afterSum.get()+money);
        }
    }

    public void clickSubmitButton(){
        CustomerVO customerVO = customers.get(customer.getSelectionModel().getSelectedIndex());
        String inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
        SalesVO salesVO = new SalesVO(BillType.SALES, BillState.SUBMITTED, BillID.getText(), customerVO.customerName, customerVO.customerID, customerVO.salesman, Username.getText(), inventoryName, goodsItemList, Double.parseDouble(allowance.getText()), Double.parseDouble(voucher.getText()),remark.getText(),new Date());
        salesBLService.submitSales(salesVO);
        salesStaffSalesOrderViewController.showSalesOrderList();
    }
    
    public void clickCancelButton(){
        Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("需要保存为草稿吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	String customerName = "";
                String inventoryName = "";
                String customerID = "";
                String customerSalesman = "";
                if (customer.getSelectionModel().getSelectedIndex() >= 0){
                    customerName = customers.get(customer.getSelectionModel().getSelectedIndex()).customerName;
                    customerID = customers.get(customer.getSelectionModel().getSelectedIndex()).customerID;
                    customerSalesman = customers.get(customer.getSelectionModel().getSelectedIndex()).salesman;
                }
                if (inventory.getSelectionModel().getSelectedIndex() >= 0){
                	inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
                }
                SalesVO salesVO = new SalesVO(BillType.SALES, BillState.SUBMITTED, BillID.getText(), customerName, customerID, customerSalesman, Username.getText(), inventoryName, goodsItemList, Double.parseDouble(allowance.getText()), Double.parseDouble(voucher.getText()),remark.getText(),new Date());
                salesBLService.saveSales(salesVO);
            }

            salesStaffSalesOrderViewController.showSalesOrderList();
        }
    }


    public void setSalesStaffSalesOrderViewController(SalesStaffSalesOrderViewController salesStaffSalesOrderViewController){
        this.salesStaffSalesOrderViewController = salesStaffSalesOrderViewController;
    }
    
    public class GoodsItemBean{
        public StringProperty ID;
        public StringProperty name;
        public StringProperty model;
        public IntegerProperty amount;
        public DoubleProperty retailPrice;
        public DoubleProperty totalPrice;
        public StringProperty remark;

        public GoodsItemBean(String ID, String name, String model, int amount, double retailPrice, double totalPrice, String remark) {
        	this.ID = new SimpleStringProperty(ID);
            this.name = new SimpleStringProperty(name);
            this.model = new SimpleStringProperty(model);
            this.amount = new SimpleIntegerProperty(amount);
            this.retailPrice = new SimpleDoubleProperty(retailPrice);
            this.totalPrice = new SimpleDoubleProperty(totalPrice);
            this.remark = new SimpleStringProperty(remark);
        }
        
        public String getID(){
        	return ID.get();
        }
        
        public StringProperty IDProperty(){
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
        
        public String getModel() {
            return model.get();
        }

        public StringProperty modelProperty() {
            return model;
        }
        
        public void setModel(String model) {
            this.model.set(model);
        }
        
        public int getAmount(){
        	return amount.get();
        }
        
        public IntegerProperty amountProperty(){
        	return amount;
        }
        
        public void setAmount(int amount){
        	this.amount.set(amount);
        }

        public double getTotalPrice() {
            return totalPrice.get();
        }

        public DoubleProperty totalPriceProperty() {
            return totalPrice;
        }

        public void setTotalPricePrice(double totalPrice) {
            this.totalPrice.set(totalPrice);
        }
        
        public double getRetailPrice() {
            return retailPrice.get();
        }

        public DoubleProperty retailPriceProperty() {
            return retailPrice;
        }

        public void setRetailPrice(double retailPrice) {
            this.retailPrice.set(retailPrice);
        }

        public String getRemark() {
            return remark.get();
        }

        public StringProperty remarkProperty() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark.set(remark);
        }
    }
}
