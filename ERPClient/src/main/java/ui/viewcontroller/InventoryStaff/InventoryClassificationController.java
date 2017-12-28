package ui.viewcontroller.InventoryStaff;

import bl.classificationbl.ClassificationController;
import bl.goodsbl.GoodsController;
import blservice.classificationblservice.ClassificationBLService;
import blservice.goodsblservice.GoodsBLService;
import blstubdriver.ClassificationBLService_Stub;
import blstubdriver.GoodsBLService_Stub;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import ui.component.DialogFactory;
import util.ResultMessage;
import vo.ClassificationVO;
import vo.GoodsVO;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryClassificationController {
    InventoryViewController inventoryViewController;
    ClassificationBLService classificationBLService = new ClassificationController();
    GoodsBLService goodsBLService = new GoodsController();

    ArrayList<ClassificationVO> classifications;
    ArrayList<GoodsVO> goods;

    ArrayList<TreeItem<String>> treeItems = new ArrayList<>();
    TreeItem<String> root;
    TreeView<String> tree;
    TableView<GoodsBean> goodsTable;
    ObservableList<GoodsBean> goodsData = FXCollections.observableArrayList();


    @FXML
    ScrollPane TreePane;
    @FXML
    ScrollPane GoodsPane;

    @FXML
    public void initialize() {
        initTree();
        showTree();
        initTable();
    }
    public void initTree(){
        tree = new TreeView<>();
        tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showGoods(findID(tree.getSelectionModel().getSelectedItem().getValue()));
            }
        });
        tree.setEditable(true);
        tree.setPrefWidth(200);
        TreePane.setContent(tree);
    }

    public void initTable(){
        goodsTable = new TableView<>();

        TableColumn IDColumn = new TableColumn("编号");
        TableColumn nameColumn = new TableColumn("商品名称");
        TableColumn amountColumn = new TableColumn("库存数量");
        TableColumn purchaseColumn = new TableColumn("进价");
        TableColumn recentPurchaseColumn = new TableColumn("最近进价");
        TableColumn salesColumn = new TableColumn("零售价");
        TableColumn recentSalesColumn = new TableColumn("最近零售价");

        IDColumn.setPrefWidth(55);
        nameColumn.setPrefWidth(90);
        amountColumn.setPrefWidth(60);
        purchaseColumn.setPrefWidth(60);
        salesColumn.setPrefWidth(60);
        recentPurchaseColumn.setPrefWidth(60);
        recentSalesColumn.setPrefWidth(70);

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        recentPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("recentPurchasePrice"));
        purchaseColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        recentSalesColumn.setCellValueFactory(new PropertyValueFactory<>("recentSalesPrice"));
        salesColumn.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));

        goodsTable.getColumns().addAll(IDColumn,nameColumn,amountColumn,purchaseColumn,recentPurchaseColumn,salesColumn,recentSalesColumn);
        goodsTable.setItems(goodsData);
        GoodsPane.setContent(goodsTable);
    }

    public void showTree(){
        classifications = classificationBLService.show();
        treeItems.clear();
        for (ClassificationVO classificationVO:classifications){
            TreeItem<String> treeItem = new TreeItem<>(classificationVO.name);
            treeItem.setExpanded(true);
            treeItems.add(treeItem);
        }
        for (int i = 0; i < treeItems.size(); i++){
            if (classifications.get(i).father == null){
                root = treeItems.get(i);
                tree.setRoot(root);
            }
            else {
                for (TreeItem treeItem1 : treeItems){
                    if (treeItem1.getValue().toString().equals(classifications.get(i).father.name)){
                        treeItem1.getChildren().add(treeItems.get(i));
                    }
                }
            }
        }
    }
    public void showGoods(String classificationID){
        goodsData.clear();
        if (classificationBLService.showDetails(classificationID).goods == null) {
            return;
        }else{
            goods = classificationBLService.showDetails(classificationID).goods;
            for (GoodsVO good : goods){
                goodsData.add(new GoodsBean(good.ID,good.name,good.amount,good.recentBuyingPrice,good.recentRetailPrice,good.retailPrice,good.buyingPrice));
            }
        }
    }

    public void clickAddClassButton(){
        TreeItem<String> item = getSelectedItem();
        if (item != null){
            ClassificationVO classificationVO = classificationBLService.showDetails(findID(item.getValue()));
            //必须是不含商品的节点
            if (classificationVO.goods == null || classificationVO.goods.isEmpty()) {
                Dialog dialog = DialogFactory.getTextInputDialog();
                dialog.setHeaderText("请输入新的分类名称");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String name = result.get();
                    String newID = classificationBLService.getNewID();
                    ResultMessage re = classificationBLService.add(new ClassificationVO(newID, name, classificationVO, null, null));
                    if (re == ResultMessage.SUCCESS) {
                        showTree();
                    }
                }
            }else {
                Dialog dialog = DialogFactory.getInformationAlert();
                dialog.setHeaderText("该分类有商品，不能添加子分类");
                dialog.showAndWait();
            }
        }
    }
    public void clickAddGoodButton(){
       TreeItem<String> item = getSelectedItem();
       if (item != null){

           //只有叶节点才可以添加商品
           if (item.isLeaf()) {
               Dialog dialog = getAddGoodDialog(findID(item.getValue()));
               Optional<ArrayList<String>> result = dialog.showAndWait();
               if (result.isPresent()) {
                   ArrayList<String> results = result.get();
                   ResultMessage re = goodsBLService.add(new GoodsVO(results.get(0), results.get(1), results.get(2),
                           item.getValue(), 0, Integer.parseInt(results.get(3)), Double.parseDouble(results.get(4)),
                           Double.parseDouble(results.get(5)), Double.parseDouble(results.get(4)), Double.parseDouble(results.get(5))));
                   if (re == ResultMessage.SUCCESS) {
                       showGoods(findID(item.getValue()));
                   }
               }
           }else {
               Dialog dialog = DialogFactory.getInformationAlert();
               dialog.setHeaderText("该分类不是叶节点，不能添加商品");
               dialog.showAndWait();
           }
       }

    }
    public Dialog getAddGoodDialog(String classificationID){
        ArrayList<Label> labels = new ArrayList<>();

        labels.add(getMyLabel("商品编号"));
        labels.add(getMyLabel("商品名称"));
        labels.add(getMyLabel("商品型号"));
        labels.add(getMyLabel("进价"));
        labels.add(getMyLabel("零售价"));
        labels.add(getMyLabel("警戒数量"));

        ArrayList<Node> nodes = new ArrayList<>();
        Label ID = new Label(goodsBLService.getNewID(classificationID));
        JFXTextField nameTF = new JFXTextField();
        JFXTextField modelTF = new JFXTextField();
        JFXTextField purchaseTF = new JFXTextField();
        JFXTextField salesTF = new JFXTextField();
        JFXTextField alarmTF = new JFXTextField();
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
                result.add(ID.getText());
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
    public void clickModifyButton(){
        TreeItem<String> item = getSelectedItem();
        if (item != null){
            Dialog dialog = DialogFactory.getTextInputDialog();
            dialog.setHeaderText("请输入修改后的分类名称");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                ClassificationVO classificationVO = classificationBLService.showDetails(findID(item.getValue()));
                classificationVO.name = result.get();
                classificationBLService.update(classificationVO);
                showTree();
            }
        }
    }
    public TreeItem<String> getSelectedItem(){
        TreeItem<String> item = tree.getSelectionModel().getSelectedItem();
        if (item == null){
            Dialog dialog = DialogFactory.getInformationAlert();
            dialog.setHeaderText("请先选择分类");
            dialog.showAndWait();
            return null;
        }
        return item;
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }

    public String findID(String name){
        for (int i = 0; i < treeItems.size();i++){
            if (treeItems.get(i).getValue().equals(name)){
                return classifications.get(i).ID;
            }
        }
        return null;
    }
    private Label getMyLabel(String text){
        Label label = new Label(text);
        label.setPrefWidth(70);
        label.setFont(Font.font(12));
        return label;
    }
    public class GoodsBean{
        StringProperty ID;
        StringProperty name;
        IntegerProperty amount;
        DoubleProperty recentPurchasePrice;
        DoubleProperty purchasePrice;
        DoubleProperty recentSalesPrice;
        DoubleProperty salesPrice;

        public GoodsBean(String ID, String name, Integer amount, Double recentPurchasePrice, Double recentSalesPrice, Double salesPrice,Double purchasePrice) {
            this.ID = new SimpleStringProperty(ID);
            this.name = new SimpleStringProperty(name);
            this.amount = new SimpleIntegerProperty(amount);
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
