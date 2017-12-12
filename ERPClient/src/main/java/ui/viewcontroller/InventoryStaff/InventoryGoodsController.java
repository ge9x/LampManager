package ui.viewcontroller.InventoryStaff;

import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsBLService;
import blstubdriver.GoodsBLService_Stub;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ui.component.DialogFactory;
import util.ResultMessage;
import vo.GoodsVO;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryGoodsController {
    GoodsBLService goodsBLService = new GoodsController();

    InventoryViewController inventoryViewController;
    ArrayList<GoodsVO> goods;

    TableView<GoodsBean> table;
    ObservableList<GoodsBean> data = FXCollections.observableArrayList();


    @FXML
    ScrollPane TablePane;

    @FXML
    Label searchIcon;

    @FXML
    TextField SearchField;

    @FXML
    public void initialize(){
        searchIcon.setText("\ue69d");

        initTable();
        goods = goodsBLService.show();
        showGoods();
    }

    public void initTable(){
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
        TablePane.setContent(table);
    }
    public void showGoods(){
        data.clear();
        if (goods == null)
            return ;
        for (GoodsVO good : goods){
            data.add(new GoodsBean(good.ID,good.name,good.model,good.classification,good.amount,good.alarmAmount,good.recentBuyingPrice,good.recentRetailPrice,good.buyingPrice,good.retailPrice));
        }

    }
    public void clickModifyButton(){
        GoodsBean bean = getSelectedItem();
        if (bean != null){
            Dialog dialog = getModifyDialog(bean);
            Optional<ArrayList<String>> result = dialog.showAndWait();
            if (result.isPresent()){
                ArrayList<String> results = result.get();
                GoodsVO goodsVO = goodsBLService.showDetails(bean.getID());
                goodsVO.name = results.get(0);
                goodsVO.model = results.get(1);
                goodsVO.alarmAmount = Integer.parseInt(results.get(2));
                goodsVO.buyingPrice = Double.parseDouble(results.get(3));
                goodsVO.retailPrice = Double.parseDouble(results.get(4));
                ResultMessage re = goodsBLService.update(goodsVO);
                if (re == ResultMessage.SUCCESS){
                    bean.setName(goodsVO.name);
                    bean.setModel(goodsVO.model);
                    bean.setAlarmAmount(goodsVO.alarmAmount);
                    bean.setPurchasePrice(goodsVO.buyingPrice);
                    bean.setSalesPrice(goodsVO.retailPrice);
                }
            }
        }
    }
    public Dialog getModifyDialog(GoodsBean bean){
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("商品编号"));
        labels.add(new Label("商品名称"));
        labels.add(new Label("商品型号"));
        labels.add(new Label("进价"));
        labels.add(new Label("零售价"));
        labels.add(new Label("警戒数量"));

        ArrayList<Node> nodes = new ArrayList<>();
        Label ID = new Label(bean.getID());
        JFXTextField nameTF = new JFXTextField(bean.getName());
        JFXTextField modelTF = new JFXTextField(bean.getModel());
        JFXTextField purchaseTF = new JFXTextField(bean.getPurchasePrice()+"");
        JFXTextField salesTF = new JFXTextField(bean.getSalesPrice()+"");
        JFXTextField alarmTF = new JFXTextField(bean.getAlarmAmount()+"");

        nodes.add(ID);
        nodes.add(nameTF);
        nodes.add(modelTF);
        nodes.add(purchaseTF);
        nodes.add(salesTF);
        nodes.add(alarmTF);

        Dialog dialog = DialogFactory.createDialog(labels,nodes);
        dialog.setHeaderText("请输入新的商品信息");
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.FINISH) {
                ArrayList<String> result = new ArrayList<>();
                result.add(nameTF.getText());
                result.add(modelTF.getText());
                result.add(alarmTF.getText());
                result.add(purchaseTF.getText());
                result.add(salesTF.getText());
                return result;
            }
            return null;
        });
        return dialog;
    }

    public void clickSearchButton(){
        String keyword = SearchField.getText();
        goods = goodsBLService.find(keyword);
        showGoods();
    }
    public GoodsBean getSelectedItem(){
        GoodsBean goodsBean = table.getSelectionModel().getSelectedItem();
        if(goodsBean == null){
            Dialog dialog = DialogFactory.getInformationAlert();
            dialog.setHeaderText("请先选择商品记录");
            dialog.showAndWait();
            return null;
        }
        return goodsBean;
    }

    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
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
