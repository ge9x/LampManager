package ui.viewcontroller.SalesStaff;

import bean.GoodsBean;
import bean.GoodsItemBean;
import bl.salesbl.PurchaseController;
import bl.salesbl.SalesBLFactory;
import blservice.salesblservice.SalesBLService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import ui.component.DialogFactory;
import ui.component.GoodsSelecter;
import ui.component.SalesBillTable;
import ui.viewcontroller.GeneralManager.GeneralManagerExaminationCellController;
import ui.viewcontroller.common.MainUIController;
import ui.viewcontroller.common.StateBarController;
import util.BillState;
import util.BillType;
import util.Money;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PurchaseVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class SalesStaffPurchaseEditViewController {
	MainUIController mainUIController;
	SalesStaffPurchaseOrderViewController salesStaffPurchaseOrderViewController;
	GeneralManagerExaminationCellController generalManagerExaminationCellController;
	
	SalesBLService salesBLService = SalesBLFactory.getPurchaseBLService();
	ArrayList<GoodsItemVO> goodsItemList = new ArrayList<GoodsItemVO>();
	ArrayList<CustomerVO> suppliers = new ArrayList<CustomerVO>();
	ArrayList<String> inventories = new ArrayList<String>();
	
	boolean isNew;
	boolean isExamine = false;
	public boolean onlyShow = false;
	
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
        SalesBillTable ItemTable = new SalesBillTable();
        itemTable = new TableView<>();
        itemTable.setEditable(true);

        ItemTable.amountColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean, Integer>forTableColumn(new IntegerStringConverter()));
        ItemTable.amountColumn.setOnEditCommit(
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
        		});
        
        ItemTable.retailPriceColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean, Double>forTableColumn(new DoubleStringConverter()));
        ItemTable.retailPriceColumn.setOnEditCommit(
        		(CellEditEvent<GoodsItemBean, Double> t)->{
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setRetailPrice(t.getNewValue());
        			
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setTotalPrice(t.getNewValue() 
        							* ((GoodsItemBean) t.getTableView().getItems().get(
        		        					t.getTablePosition().getRow())
        		        					).getAmount()
        							);
        		});
        
        ItemTable.remarkColumn.setCellFactory(TextFieldTableCell.<GoodsItemBean>forTableColumn());
        ItemTable.remarkColumn.setOnEditCommit(
        		(CellEditEvent<GoodsItemBean, String> t)->{
        			((GoodsItemBean) t.getTableView().getItems().get(
        					t.getTablePosition().getRow())
        					).setRemark(t.getNewValue());
        		});
        
        itemTable.setItems(data);
        itemTable.getColumns().addAll(ItemTable.IDColumn, ItemTable.nameColumn, ItemTable.modelColumn, ItemTable.amountColumn, 
        		ItemTable.retailPriceColumn, ItemTable.totalPriceColumn, ItemTable.remarkColumn);
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
    		GoodsItemBean itemBean = new GoodsItemBean(bean.getID(), bean.getName(), bean.getModel(), 0, bean.getRecentPurchasePrice(), 0, "");
        	data.add(itemBean);
            itemBean.totalPriceProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    total.setValue(total.getValue()-oldValue.doubleValue()+newValue.doubleValue());
                }
            });
    	}
    }

    public void clickDeleteButton(){
    	int index = itemTable.getSelectionModel().getSelectedIndex();
    	total.set(total.get()-data.get(index).getTotalPrice());
    	data.remove(index);
    }
    
    public void clickSubmitButton(){
    	if(supplier.getSelectionModel().getSelectedIndex()!=-1&&inventory.getSelectionModel().getSelectedIndex()!=-1&&!data.isEmpty()){
	    	goodsItemList.clear();
	    	for(GoodsItemBean bean:data){
	    		GoodsItemVO vo = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), bean.getRemark());
	    		goodsItemList.add(vo);
	    	}
	        String supplierName = suppliers.get(supplier.getSelectionModel().getSelectedIndex()).customerName;
	        String supplierID = suppliers.get(supplier.getSelectionModel().getSelectedIndex()).customerID;
	        String inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
	        PurchaseVO purchaseVO = new PurchaseVO(BillType.PURCHASE, BillState.SUBMITTED, BillID.getText(), supplierName, supplierID, inventoryName, Username.getText(), goodsItemList,remark.getText(), LocalDate.now().toString());
	    	if(!isExamine){
	    		if(isNew){
	    			salesBLService.submitPurchase(purchaseVO);
	    		}
	    		else{
	    			salesBLService.updatePurchase(purchaseVO);
	    		}
		        salesStaffPurchaseOrderViewController.showPurchaseOrderList();
	    	}
	    	else{
	    		salesBLService.updatePurchase(purchaseVO);
	    		generalManagerExaminationCellController.clickReturnButton();
	    	}
    	}
    	else{
    		Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("单据信息不完整");
	        Optional result = dialog.showAndWait();
    	}
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
	                if(isNew){
	                	salesBLService.savePurchase(purchaseVO);
	                }
	                else{
	                	salesBLService.updatePurchase(purchaseVO);
	                }
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
        
        itemTable.setEditable(false);
        

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (onlyShow){
                    mainUIController.back();
                    return;
                }
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
        
        itemTable.setEditable(true);

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

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
}
