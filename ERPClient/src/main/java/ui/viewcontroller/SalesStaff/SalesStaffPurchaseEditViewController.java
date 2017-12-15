package ui.viewcontroller.SalesStaff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import bean.CashBillItemBean;
import bean.GoodsItemBean;
import bl.salesbl.PurchaseController;
import bl.salesbl.SalesController;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import ui.component.DialogFactory;
import ui.component.GoodsSelecter;
import bean.GoodsBean;
import ui.viewcontroller.GeneralManager.GeneralManagerExaminationCellController;
import util.BillState;
import util.BillType;
import util.Money;
import vo.CashBillItemVO;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PurchaseVO;

public class SalesStaffPurchaseEditViewController {
	
	SalesStaffPurchaseOrderViewController salesStaffPurchaseOrderViewController;
	GeneralManagerExaminationCellController generalManagerExaminationCellController;
	
	SalesBLService salesBLService = new PurchaseController();
	ArrayList<GoodsItemVO> goodsItemList = new ArrayList<GoodsItemVO>();
	ArrayList<CustomerVO> suppliers = new ArrayList<CustomerVO>();
	ArrayList<String> inventories = new ArrayList<String>();
	
	boolean isNew;
	boolean isExamine = false;
	
	TableView<GoodsItemBean> itemTable;
    ObservableList<GoodsItemBean> data =
            FXCollections.observableArrayList();
    DoubleProperty total = new SimpleDoubleProperty(0);
    
    @FXML
    Label deleteIcon;

    @FXML
    Label addIcon;

    @FXML
    Label BillID;

    @FXML
    Text Username;

    @FXML
    VBox vbox;

    @FXML
    Text Total;
    
    @FXML
    Label title;
    
    @FXML
    JFXButton submitButton;

    @FXML
    JFXButton cancelButton;
    
    @FXML
    JFXTextField remark;

    @FXML
    JFXComboBox<String> supplier;
    
    @FXML
    JFXComboBox<String> inventory;
    
    public void initialize(){
    	deleteIcon.setText("\ue606");
        addIcon.setText("\ue61e");
        String name = salesBLService.getUserName();
        Username.setText(name);
        suppliers = salesBLService.getAllSupplier();
        inventories = salesBLService.getAllInventory();
        

        //初始化supplier选择框
        ArrayList<String> supplierNames = new ArrayList<>();
        for (CustomerVO customer : suppliers){
            supplierNames.add(customer.customerName);
        }
        supplier.getItems().addAll(supplierNames);
        
      //初始化inventory选择框
        inventory.getItems().addAll(inventories);

        //初始化表格
        itemTable = new TableView<>();
        itemTable.setEditable(true);

        TableColumn IDColumn = new TableColumn("商品编号");
        IDColumn.setPrefWidth(70);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn nameColumn = new TableColumn("条目名");
        nameColumn.setPrefWidth(60);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(60);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<GoodsItemBean, Integer> amountColumn = new TableColumn("数量");
        amountColumn.setPrefWidth(60);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn retailPriceColumn = new TableColumn("单价");
        retailPriceColumn.setPrefWidth(60);
        retailPriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        TableColumn totalPriceColumn = new TableColumn("总价");
        totalPriceColumn.setPrefWidth(60);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        TableColumn<GoodsItemBean, String> remarkColumn = new TableColumn("备注");
        remarkColumn.setPrefWidth(78);
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        amountColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean, Integer>forTableColumn(new IntegerStringConverter()));
        amountColumn.setOnEditCommit(
        		(CellEditEvent<GoodsItemBean, Integer> t)->{
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setAmount(t.getNewValue());
        			
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setTotalPrice(t.getNewValue() 
        							* ((GoodsItemBean) t.getTableView().getItems().get(
        		        					t.getTablePosition().getRow())
        		        					).getRetailPrice()
        							);
        			total.set(total.get()+((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).getTotalPrice());
        		});
        
        remarkColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean>forTableColumn());
        remarkColumn.setOnEditCommit(
        		(CellEditEvent<GoodsItemBean, String> t)->{
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setRemark(t.getNewValue());
        		});
        
        itemTable.setItems(data);
        itemTable.getColumns().addAll(IDColumn, nameColumn, modelColumn, amountColumn, retailPriceColumn, totalPriceColumn, remarkColumn);
        vbox.getChildren().add(itemTable);

        //总额Text与商品总额金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });

    }
    
    public void addPurchaseOrder() {
        String ID = salesBLService.getnewPurchaseID();
        BillID.setText(ID);
        isNew = true;
        isExamine = false;
    }

    public void clickAddButton(){
    	GoodsSelecter selecter = new GoodsSelecter();
    	Dialog dialog = selecter.getGoodsDialog();
    	Optional<GoodsBean> result = dialog.showAndWait();
    	
    	GoodsBean bean = null;
    	if (result.isPresent()){
    		bean = result.get();
    	}
    	data.add(new GoodsItemBean(bean.getID(), bean.getName(), bean.getModel(), 0, bean.getRecentPurchasePrice(), 0,""));
//    	total.set(total.get()+money);
    	
//        ArrayList<Label> labels = new ArrayList<>();
//        labels.add(new Label("商品编号"));
//        labels.add(new Label("条目名"));
//        labels.add(new Label("型号"));
//        labels.add(new Label("数量"));
//        labels.add(new Label("单价"));
//        labels.add(new Label("总价"));
//        labels.add(new Label("备注"));
//
//        ArrayList<Node> nodes = new ArrayList<Node>();
//        TextField IDTF = new TextField();
//        TextField nameTF = new TextField();
//        TextField modelTF = new TextField();
//        TextField amountTF = new TextField();
//        TextField retailPriceTF = new TextField();
//        TextField totalPriceTF = new TextField();
//        TextField remarkTF = new TextField();
//        nodes.add(IDTF);
//        nodes.add(nameTF);
//        nodes.add(modelTF);
//        nodes.add(amountTF);
//        nodes.add(retailPriceTF);
//        nodes.add(totalPriceTF);
//        nodes.add(remarkTF);
//
//        Dialog dialog = DialogFactory.createDialog(labels,nodes);
//        dialog.setResultConverter(dialogButton -> {
//            ArrayList<String> result = new ArrayList<>();
//            result.add(IDTF.getText());
//            result.add(nameTF.getText());
//            result.add(modelTF.getText());
//            result.add(amountTF.getText());
//            result.add(retailPriceTF.getText());
//            result.add(totalPriceTF.getText());
//            result.add(remarkTF.getText());
//            if (dialogButton == ButtonType.FINISH) {
//                return result;
//            }
//            return null;
//        });
//
//        Optional result = dialog.showAndWait();
//        if (result.isPresent()){
//            ArrayList<String> values = (ArrayList<String>)result.get();
//            Double money = Double.parseDouble(values.get(5));
//            int amount = Integer.parseInt(values.get(3));
//            double totalPrice = Double.parseDouble(values.get(5));
//            GoodsItemVO GoodsItemVO = new GoodsItemVO(values.get(0),values.get(1),values.get(2),amount,money,values.get(6));
//
//            goodsItemList.add(GoodsItemVO);
//            data.add(new GoodsItemBean(values.get(0),values.get(1),values.get(2),amount,money,totalPrice,values.get(6)));
//            total.set(total.get()+money);
//        }
    }

    public void clickDeleteButton(){
    	int index = itemTable.getSelectionModel().getSelectedIndex();
    	total.set(total.get()-data.get(index).getTotalPrice());
    	data.remove(index);
    }
    
    public void clickSubmitButton(){
    	goodsItemList.clear();
    	for(GoodsItemBean bean:data){
    		GoodsItemVO vo = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), bean.getRemark());
    		goodsItemList.add(vo);
    	}
        String supplierName = suppliers.get(supplier.getSelectionModel().getSelectedIndex()).customerName;
        String supplierID = suppliers.get(supplier.getSelectionModel().getSelectedIndex()).customerID;
        String inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
        PurchaseVO purchaseVO = new PurchaseVO(BillType.PURCHASE, BillState.SUBMITTED, BillID.getText(), supplierName, supplierID, inventoryName, Username.getText(), goodsItemList,remark.getText(), LocalDate.now().toString());
        if(isNew){
        	salesBLService.submitPurchase(purchaseVO);
        }
        else{
        	salesBLService.updatePurchase(purchaseVO);
        }
        salesStaffPurchaseOrderViewController.showPurchaseOrderList();
    }
    
    public void clickCancelButton(){
    	if(!isExamine){
	        Dialog dialog = DialogFactory.getConfirmationAlert();
	        dialog.setHeaderText("需要保存为草稿吗？");
	        Optional result = dialog.showAndWait();
	
	
	        if (result.isPresent()){
	            if (result.get() == ButtonType.OK) {
	            	String supplierName = "";
	                String inventoryName = "";
	                String supplierID = "";
	                if (supplier.getSelectionModel().getSelectedIndex() >= 0){
	                    supplierName = suppliers.get(supplier.getSelectionModel().getSelectedIndex()).customerName;
	                    supplierID = suppliers.get(supplier.getSelectionModel().getSelectedIndex()).customerID;
	                }
	                if (inventory.getSelectionModel().getSelectedIndex() >= 0){
	                	inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
	                }
	                goodsItemList.clear();
	                for(GoodsItemBean bean:data){
	                	GoodsItemVO goodsItemVO = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), bean.getRemark());
	                	goodsItemList.add(goodsItemVO);
	                }
	                PurchaseVO purchaseVO = new PurchaseVO(BillType.PURCHASE, BillState.DRAFT, BillID.getText(), supplierName, supplierID, inventoryName, Username.getText(), goodsItemList,remark.getText(),LocalDate.now().toString());
	                salesBLService.savePurchase(purchaseVO);
	            }
	
	            salesStaffPurchaseOrderViewController.showPurchaseOrderList();
	        }
    	}
    	else{
    		Dialog dialog = DialogFactory.getConfirmationAlert();
	        dialog.setHeaderText("确定放弃修改吗？");
	        Optional result = dialog.showAndWait();
	
	
	        if (result.isPresent()){
	            if (result.get() == ButtonType.OK) {
	            	generalManagerExaminationCellController.clickReturnButton();
	            	isExamine = false;
	            }
	        }
    	}
    }


    public void setSalesStaffPurchaseOrderViewController(SalesStaffPurchaseOrderViewController salesStaffPurchaseOrderViewController){
        this.salesStaffPurchaseOrderViewController = salesStaffPurchaseOrderViewController;
    }
    
    public void setGeneralManagerExaminationCellViewController(GeneralManagerExaminationCellController generalManagerExaminationCellController){
    	this.generalManagerExaminationCellController = generalManagerExaminationCellController;
    }
    
    public void setForDetailView(PurchaseVO purchaseBill){
    	isNew = false;
        BillID.setText(purchaseBill.ID);
        title.setText("进货单详情");
        addIcon.setVisible(false);
        deleteIcon.setVisible(false);
        remark.setEditable(false);

        inventory.getSelectionModel().select(purchaseBill.inventory);
        inventory.setDisable(true);
        supplier.getSelectionModel().select(purchaseBill.supplier);
        supplier.setDisable(true);
        Username.setText(purchaseBill.user);
        remark.setText(purchaseBill.remarks);
        

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if(!isExamine){
            		salesStaffPurchaseOrderViewController.showPurchaseOrderList();
            	}
            	else{
            		generalManagerExaminationCellController.clickReturnButton();
            		isExamine = false;
            	}
            }
        });

        if (purchaseBill.state == BillState.DRAFT||isExamine){
            submitButton.setText("编 辑");
            submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setForEditView();
                }
            });
        }else{
            submitButton.setVisible(false);
        }
        for (GoodsItemVO goodsItemVO:purchaseBill.goodsItemList){
            goodsItemList.add(goodsItemVO);
            data.add(new GoodsItemBean(goodsItemVO.ID, goodsItemVO.goodsName, goodsItemVO.model, goodsItemVO.number, goodsItemVO.price, 
            		goodsItemVO.sum, goodsItemVO.remarks));
            total.set(total.get() + goodsItemVO.sum);
        }
    }
    
    public void setForEditView(){
    	addIcon.setVisible(true);
    	deleteIcon.setVisible(true);
        title.setText("编辑草稿单");
        remark.setEditable(true);
        inventory.setDisable(false);
        supplier.setDisable(false);

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
    
    public void isExamine(){
    	isExamine = true;
    }
}
