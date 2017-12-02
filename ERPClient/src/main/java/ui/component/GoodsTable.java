package ui.component;

import java.util.ArrayList;

import blservice.goodsblservice.GoodsBLService;
import blstubdriver.GoodsBLService_Stub;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.viewcontroller.InventoryStaff.InventoryGoodsController.GoodsBean;
import vo.GoodsVO;

public class GoodsTable {
	TableView<GoodsBean> table;
	ArrayList<GoodsVO> goods;
	ObservableList<GoodsBean> data = FXCollections.observableArrayList();
	GoodsBLService goodsBLService = new GoodsBLService_Stub();
	
	public GoodsTable(){
		
			getData();
			table = new TableView<>();
	        table.setEditable(true);
	        TableColumn IDColumn = new TableColumn("编号");
	        TableColumn nameColumn = new TableColumn("商品名称");
	        TableColumn modelColumn = new TableColumn("商品型号");
	        TableColumn classificationColumn = new TableColumn("商品分类");
	        TableColumn amountColumn = new TableColumn("库存数量");
	        TableColumn alarmAmountColumn = new TableColumn("警戒数量");
	        TableColumn purchaseColumn = new TableColumn("进价");
	        TableColumn recentPurchaseColumn = new TableColumn("最近进价");
	        TableColumn salesColumn = new TableColumn("零售价");
	        TableColumn recentSalesColumn = new TableColumn("最近零售价");

	        IDColumn.setPrefWidth(90);
	        nameColumn.setPrefWidth(90);
	        modelColumn.setPrefWidth(90);
	        classificationColumn.setPrefWidth(90);
	        amountColumn.setPrefWidth(49);
	        alarmAmountColumn.setPrefWidth(49);
	        purchaseColumn.setPrefWidth(70);
	        salesColumn.setPrefWidth(70);
	        recentPurchaseColumn.setPrefWidth(70);
	        recentSalesColumn.setPrefWidth(70);

	        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
	        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
	        classificationColumn.setCellValueFactory(new PropertyValueFactory<>("classification"));
	        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
	        alarmAmountColumn.setCellValueFactory(new PropertyValueFactory<>("alarmAmount"));
	        recentPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("recentPurchasePrice"));
	        purchaseColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
	        recentSalesColumn.setCellValueFactory(new PropertyValueFactory<>("recentSalesPrice"));
	        salesColumn.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));

	        table.getColumns().addAll(IDColumn,nameColumn,modelColumn,classificationColumn,amountColumn,alarmAmountColumn,purchaseColumn,recentPurchaseColumn,salesColumn,recentSalesColumn);
	        table.setItems(data);
	}

	public void getData(){
		 goods = goodsBLService.show();
	        if (goods == null)
	            return ;
	        for (GoodsVO good : goods){
	            data.add(new GoodsBean(good.ID,good.name,good.model,good.classification,good.amount,good.alarmAmount,good.recentBuyingPrice,good.recentRetailPrice,good.buyingPrice,good.retailPrice));
	        }
	}
	public TableView getTable(){
		return table;
	}
	public class GoodsBean {
	    StringProperty ID;
	    StringProperty name;
	    StringProperty classification;
	    StringProperty model;
	    IntegerProperty amount;
	    IntegerProperty alarmAmount;
	    DoubleProperty recentPurchasePrice;
	    DoubleProperty purchasePrice;
	    DoubleProperty recentSalesPrice;
	    DoubleProperty salesPrice;
	
	    public GoodsBean(String ID, String name, String model,String classification,Integer alarmAmount,Integer amount, Double recentPurchasePrice, Double recentSalesPrice, Double salesPrice,Double purchasePrice) {
	        this.ID = new SimpleStringProperty(ID);
	        this.name = new SimpleStringProperty(name);
	        this.model = new SimpleStringProperty(model);
	        this.classification = new SimpleStringProperty(classification);
	        this.amount = new SimpleIntegerProperty(amount);
	        this.alarmAmount = new SimpleIntegerProperty(alarmAmount);
	        this.recentPurchasePrice = new SimpleDoubleProperty(recentPurchasePrice);
	        this.recentSalesPrice = new SimpleDoubleProperty(recentSalesPrice);
	        this.salesPrice = new SimpleDoubleProperty(salesPrice);
	        this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
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
	
	    public int getAlarmAmount() {
	        return alarmAmount.get();
	    }
	
	    public IntegerProperty alarmAmountProperty() {
	        return alarmAmount;
	    }
	
	    public void setAlarmAmount(int alarmAmount) {
	        this.alarmAmount.set(alarmAmount);
	    }
	
	    public StringProperty nameProperty() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name.set(name);
	    }
	
	    public int getAmount() {
	        return amount.get();
	    }
	
	    public IntegerProperty amountProperty() {
	        return amount;
	    }
	
	    public String getClassification() {
	        return classification.get();
	    }
	
	    public StringProperty classificationProperty() {
	        return classification;
	    }
	
	    public void setClassification(String classification) {
	        this.classification.set(classification);
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
	
	    public void setAmount(int amount) {
	        this.amount.set(amount);
	    }
	
	    public double getRecentPurchasePrice() {
	        return recentPurchasePrice.get();
	    }
	
	    public DoubleProperty recentPurchasePriceProperty() {
	        return recentPurchasePrice;
	    }
	
	    public void setRecentPurchasePrice(double recentPurchasePrice) {
	        this.recentPurchasePrice.set(recentPurchasePrice);
	    }
	
	    public double getRecentSalesPrice() {
	        return recentSalesPrice.get();
	    }
	
	    public DoubleProperty recentSalesPriceProperty() {
	        return recentSalesPrice;
	    }
	
	    public void setRecentSalesPrice(double recentSalesPrice) {
	        this.recentSalesPrice.set(recentSalesPrice);
	    }
	
	    public double getPurchasePrice() {
	        return purchasePrice.get();
	    }
	
	    public DoubleProperty purchasePriceProperty() {
	        return purchasePrice;
	    }
	
	    public void setPurchasePrice(double purchasePrice) {
	        this.purchasePrice.set(purchasePrice);
	    }
	
	    public double getSalesPrice() {
	        return salesPrice.get();
	    }
	
	    public DoubleProperty salesPriceProperty() {
	        return salesPrice;
	    }
	
	    public void setSalesPrice(double salesPrice) {
	        this.salesPrice.set(salesPrice);
	    }
	}
}