package ui.viewcontroller.SalesStaff;

import bean.GoodsBean;
import bean.GoodsItemBean;
import bl.salesbl.SalesController;
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
import util.BillState;
import util.BillType;
import util.Money;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionVO;
import vo.SalesVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class SalesStaffSalesReturnEditViewController {
	SalesStaffSalesReturnOrderViewController salesStaffSalesReturnOrderViewController;
	GeneralManagerExaminationCellController generalManagerExaminationCellController;
	
	SalesBLService salesBLService = new SalesController();
	ArrayList<GoodsItemVO> goodsItemList = new ArrayList<GoodsItemVO>();
	ArrayList<CustomerVO> customers = new ArrayList<CustomerVO>();
	ArrayList<String> inventories = new ArrayList<String>();
	
	boolean isExamine = false;
	boolean isNew;
	
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
    Text Salesman;

    @FXML
    VBox vbox;

    @FXML
    Text Total;
    
    @FXML
    JFXTextField remark;
    
    @FXML
    Label title;

    @FXML
    JFXButton submitButton;

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXComboBox<String> inventory;
    
    @FXML
    JFXComboBox<String> customer;
    
    public void initialize(){
    	deleteIcon.setText("\ue606");
        addIcon.setText("\ue61e");
        String name = salesBLService.getUserName();
        Username.setText(name);
        Salesman.setText("");
        customers = salesBLService.getAllCustomer();
        inventories = salesBLService.getAllInventory();

        //初始化supplier选择框
        ArrayList<String> customerNames = new ArrayList<>();
        for (CustomerVO temp : customers){
            customerNames.add(temp.customerName);
        }
        customer.getItems().addAll(customerNames);
        
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

        //折让前总额Text与商品总额金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });
        
      //客户与业务员绑定
        customer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				int customerIndex = customer.getSelectionModel().getSelectedIndex();
				if(customer.getSelectionModel().getSelectedIndex()!=-1){
					Salesman.setText(customers.get(customerIndex).salesman);
				}
			}
        	
        });
    }
    
    public void addSalesReturnOrder() {
    	isNew = true;
        String ID = salesBLService.getnewSalesReturnID();
        BillID.setText(ID);
    }
    
    public void clickDeleteButton(){
    	int index = itemTable.getSelectionModel().getSelectedIndex();
    	total.set(total.get()-data.get(index).getTotalPrice());
    	data.remove(index);
    }

    public void clickAddButton(){
    	GoodsSelecter selecter = new GoodsSelecter();
    	Dialog dialog = selecter.getGoodsDialog();
    	Optional<GoodsBean> result = dialog.showAndWait();
    	
    	GoodsBean bean = null;
    	if (result.isPresent()){
    		bean = result.get();
    	}
    	GoodsItemBean itemBean = new GoodsItemBean(bean.getID(), bean.getName(), bean.getModel(), 0, bean.getRecentSalesPrice(), 0,"");
    	data.add(itemBean);
        itemBean.totalPriceProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                total.setValue(total.getValue()-oldValue.doubleValue()+newValue.doubleValue());
            }
        });
    }

    public void clickSubmitButton(){
    	if(customer.getSelectionModel().getSelectedIndex()!=-1&&inventory.getSelectionModel().getSelectedIndex()!=-1&&!data.isEmpty()){
	    	goodsItemList.clear();
	    	for(GoodsItemBean bean:data){
	    		GoodsItemVO vo = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), 
	    				bean.getRemark());
	    		goodsItemList.add(vo);
	    	}
	        CustomerVO customerVO = customers.get(customer.getSelectionModel().getSelectedIndex());
	        String inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
	        SalesVO salesVO = new SalesVO(BillType.SALESRETURN, BillState.SUBMITTED, BillID.getText(), customerVO.customerName, customerVO.customerID, 
	        		customerVO.salesman, Username.getText(), inventoryName, goodsItemList, 0, 0,remark.getText(),LocalDate.now().toString(), "");
	    	if(!isExamine){
	    		if(isNew){
	    			salesBLService.submitSales(salesVO);
	    		}
	    		else{
	    			salesBLService.updateSales(salesVO);
	    		}
		        salesStaffSalesReturnOrderViewController.showSalesReturnOrderList();
	    	}
	    	else{
	    		salesBLService.updateSales(salesVO);
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
	                goodsItemList.clear();
	            	for(GoodsItemBean bean:data){
	            		GoodsItemVO vo = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), bean.getRemark());
	            		goodsItemList.add(vo);
	            	}
	                SalesVO salesVO = new SalesVO(BillType.SALESRETURN, BillState.DRAFT, BillID.getText(), customerName, customerID, customerSalesman,
	                		Username.getText(), inventoryName, goodsItemList, 0, 0,remark.getText(), LocalDate.now().toString(), "");
	                if(isNew){
	                	salesBLService.saveSales(salesVO);
	                }
	                else{
	                	salesBLService.updateSales(salesVO);
	                }
	            }
	
	            salesStaffSalesReturnOrderViewController.showSalesReturnOrderList();
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


    public void setSalesStaffSalesReturnOrderViewController(SalesStaffSalesReturnOrderViewController salesStaffSalesReturnOrderViewController){
        this.salesStaffSalesReturnOrderViewController = salesStaffSalesReturnOrderViewController;
    }
    
    public void setGeneralManagerExaminationCellController(GeneralManagerExaminationCellController generalManagerExaminationCellController){
    	this.generalManagerExaminationCellController = generalManagerExaminationCellController;
    }
    
    public void setForDetailView(SalesVO salesBill){
    	isNew = false;
        BillID.setText(salesBill.ID);
        title.setText("销售退货单详情");
        Salesman.setText(salesBill.salesman);
        addIcon.setVisible(false);
        deleteIcon.setVisible(false);
        remark.setText(salesBill.remarks);
        remark.setEditable(false);

        inventory.getSelectionModel().select(salesBill.inventory);
        inventory.setDisable(true);
        customer.getSelectionModel().select(salesBill.customer);
        customer.setDisable(true);
        Username.setText(salesBill.user);
        Salesman.setText(salesBill.salesman);
        

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if(!isExamine){
            		salesStaffSalesReturnOrderViewController.showSalesReturnOrderList();
            	}
            	else{
            		generalManagerExaminationCellController.clickReturnButton();
            		isExamine = false;
            	}
            }
        });

        if (salesBill.state == BillState.DRAFT||isExamine){
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
        for (GoodsItemVO goodsItemVO:salesBill.goodsItemList){
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
        customer.setDisable(false);

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
