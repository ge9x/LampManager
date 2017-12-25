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
import util.*;
import vo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class SalesStaffSalesEditViewController {
	SalesStaffSalesOrderViewController salesStaffSalesOrderViewController;
	GeneralManagerExaminationCellController generalManagerExaminationCellController;
	
	SalesBLService salesBLService = new SalesController();
	ArrayList<GoodsItemVO> goodsItemList = new ArrayList<GoodsItemVO>();
	ArrayList<CustomerVO> customers = new ArrayList<CustomerVO>();
	ArrayList<String> inventories = new ArrayList<String>();
	ArrayList<PromotionVO> promotions = new ArrayList<PromotionVO>();
	
	TableView<GoodsItemBean> itemTable;
    ObservableList<GoodsItemBean> data =
            FXCollections.observableArrayList();
    
    TableView<GoodsItemBean> bargainItemTable;
    ObservableList<GoodsItemBean> bargainData =
            FXCollections.observableArrayList();
    
    TableView<GoodsItemBean> giftItemTable;
    ObservableList<GoodsItemBean> giftData =
            FXCollections.observableArrayList();
    
    //商品原价总额
    DoubleProperty total = new SimpleDoubleProperty(0);
    //商品原价与特价包总额
    DoubleProperty bargainAndGoods = new SimpleDoubleProperty(0);
    //折让与代金券后总额
    DoubleProperty afterSum = new SimpleDoubleProperty(0);
    //特价包价格
    DoubleProperty bargainPrice = new SimpleDoubleProperty(0);
    //折让、代金券、特价包总额
    DoubleProperty allTotal = new SimpleDoubleProperty(0);
    
    boolean isExamine = false;
    boolean isNew;
    
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
    VBox bargainVbox;
    
    @FXML
    VBox giftVbox;

    @FXML
    Text Total;
    
    @FXML
    Text beforeSum;
    
    @FXML
    Text bargainPriceText, goodsTotalText;
    
    @FXML
    JFXTextField remark;
    
    @FXML
    JFXTextField allowance;
    
    @FXML
    JFXTextField voucher;
    
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
    
    @FXML
    JFXComboBox<String> promotion;
    
    public void initialize(){
    	deleteIcon.setText("\ue606");
        addIcon.setText("\ue61e");
        String name = salesBLService.getUserName();
        Username.setText(name);
        customers = salesBLService.getAllCustomer();
        inventories = salesBLService.getAllInventory();
        promotions.addAll(salesBLService.showBargains());
        bargainPriceText.setText(Money.getMoneyString(0));
        goodsTotalText.setText(Money.getMoneyString(0));

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
        itemTable.getColumns().addAll(ItemTable.IDColumn, ItemTable.nameColumn, ItemTable.modelColumn, ItemTable.amountColumn, ItemTable.retailPriceColumn, ItemTable.totalPriceColumn, ItemTable.remarkColumn);
        vbox.getChildren().add(itemTable);
        
        SalesBillTable BargainItemTable = new SalesBillTable();
        bargainItemTable = new TableView<>();
        bargainItemTable.setEditable(false);
        bargainItemTable.setItems(bargainData);
        bargainItemTable.getColumns().addAll(BargainItemTable.IDColumn, BargainItemTable.nameColumn, BargainItemTable.modelColumn, 
        		BargainItemTable.amountColumn, BargainItemTable.retailPriceColumn, BargainItemTable.totalPriceColumn, BargainItemTable.remarkColumn);
        bargainVbox.getChildren().add(bargainItemTable);
        
        SalesBillTable GiftItemTable = new SalesBillTable();
        giftItemTable = new TableView<>();
        giftItemTable.setEditable(false);
        giftItemTable.setItems(giftData);
        giftItemTable.getColumns().addAll(GiftItemTable.IDColumn, GiftItemTable.nameColumn, GiftItemTable.modelColumn, GiftItemTable.amountColumn
        		, GiftItemTable.retailPriceColumn, GiftItemTable.totalPriceColumn, GiftItemTable.remarkColumn);
        giftVbox.getChildren().add(giftItemTable);

        //折让前总额Text与商品总额金额之和绑定，与促销策略绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            	bargainAndGoods.set(bargainPrice.get()+total.get());
            	goodsTotalText.setText(Money.getMoneyString(total.get()));
                
                int customerIndex = customer.getSelectionModel().getSelectedIndex();
				promotions.clear();
				promotions.addAll(salesBLService.showBargains());
				if(customer.getSelectionModel().getSelectedIndex()!=-1){
					promotions.addAll(salesBLService.getFitPromotionCustomer(customers.get(customerIndex).level));
				}
		        promotions.addAll(salesBLService.getFitPromotionTotal(total.get()));
		        initializePromotionComboBox();
            }
        });
        
        //折让后总额Text与代金券和折让绑定
        afterSum.addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				allTotal.set(afterSum.get()+bargainPrice.get());
			}
        	
        });
        
        bargainPrice.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				allTotal.set(afterSum.get()+bargainPrice.get());
				bargainAndGoods.set(total.get()+bargainPrice.get());
				bargainPriceText.setText(Money.getMoneyString(bargainPrice.get()));
			}
        	
		});
        
        allTotal.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				Total.setText(Money.getMoneyString(allTotal.get()));
			}
        	
		});
        
        bargainAndGoods.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				beforeSum.setText(Money.getMoneyString(bargainAndGoods.get()));
			}
		});
        
        //折让与总额绑定
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
        
        //代金券与总额绑定
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
        
        //客户与促销策略绑定
        customer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				int customerIndex = customer.getSelectionModel().getSelectedIndex();
				promotions.clear();
				promotions.addAll(salesBLService.showBargains());
				if(customer.getSelectionModel().getSelectedIndex()!=-1){
					promotions.addAll(salesBLService.getFitPromotionCustomer(customers.get(customerIndex).level));
				}
		        promotions.addAll(salesBLService.getFitPromotionTotal(total.get()));
		        initializePromotionComboBox();
			}
        	
        });
        
        promotion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				int promotionIndex = promotion.getSelectionModel().getSelectedIndex();
				bargainData.clear();
				giftData.clear();
				
				if(promotionIndex!=-1){
					if(promotions.get(promotionIndex).type==PromotionType.BARGAIN_STRATEGY){
						PromotionBargainVO promotionBargainVO = (PromotionBargainVO)promotions.get(promotionIndex);
						for(GoodsItemVO vo:promotionBargainVO.bargains){
							bargainData.add(new GoodsItemBean(vo.ID, vo.goodsName, vo.model, vo.number, vo.price, 0, vo.remarks));
						}
						bargainPrice.set(promotionBargainVO.bargainTotal);
						allowance.setText("0");
					}
					else if(promotions.get(promotionIndex).type==PromotionType.MEMBER_PROMOTION_STRATEGY){
						PromotionCustomerVO promotionCustomerVO = (PromotionCustomerVO)promotions.get(promotionIndex);
						for(GoodsItemVO vo:promotionCustomerVO.gifts){
							giftData.add(new GoodsItemBean(vo.ID, vo.goodsName, vo.model, vo.number, vo.price, 0, vo.remarks));
						}
						bargainPrice.set(0);
						allowance.setText(String.valueOf(promotionCustomerVO.allowance));
					}
					else if(promotions.get(promotionIndex).type==PromotionType.TOTAL_PROMOTION_STRATEGY){
						PromotionTotalVO promotionTotalVO = (PromotionTotalVO)promotions.get(promotionIndex);
						for(GoodsItemVO vo:promotionTotalVO.gifts){
							giftData.add(new GoodsItemBean(vo.ID, vo.goodsName, vo.model, vo.number, vo.price, 0, vo.remarks));
						}
						bargainPrice.set(0);
						allowance.setText("0");
					}
				}
			}

			
		});
        
    }
    
    public void initializePromotionComboBox(){
    	//初始化promotion选择框
    	promotion.getItems().clear();
        ArrayList<String> promotionNames = new ArrayList<String>();
        for (PromotionVO temp : promotions){
            promotionNames.add(temp.promotionName);
        }
        promotion.getItems().addAll(promotionNames);
    }
    
    private Level getCustomerLevel(int index){
    	switch(index){
    	case 0:
    		return Level.LEVEL_ONE;
    	case 1:
    		return Level.LEVEL_TWO;
    	case 2:
    		return Level.LEVEL_THREE;
    	case 3:
    		return Level.LEVEL_FOUR;
    	default:
    		return Level.LEVEL_FIVE;
    	}
    }
    
    public void addSalesOrder() {
    	isExamine = false;
    	isNew = true;
        String ID = salesBLService.getnewSalesID();
        BillID.setText(ID);
    }
    
    public void clickDeleteButton(){
    	int index = itemTable.getSelectionModel().getSelectedIndex();
    	total.set(total.get()-data.get(index).getTotalPrice());
    	afterSum.set(afterSum.get()-data.get(index).getTotalPrice());
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
        GoodsItemBean itemBean = new GoodsItemBean(bean.getID(), bean.getName(), bean.getModel(), 0, bean.getRecentSalesPrice(), 0, "");
    	data.add(itemBean);
        itemBean.totalPriceProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                total.setValue(total.getValue() - oldValue.doubleValue() + newValue.doubleValue());
                afterSum.setValue(afterSum.getValue() - oldValue.doubleValue() + newValue.doubleValue());
            }
        });
    }

    public void clickSubmitButton(){
    	goodsItemList.clear();
    	for(GoodsItemBean bean:data){
    		GoodsItemVO vo = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), 
    				bean.getRemark());
    		goodsItemList.add(vo);
    	}
        CustomerVO customerVO = customers.get(customer.getSelectionModel().getSelectedIndex());
        String inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
        double allowancePrice = 0;
        double voucherPrice = 0;
        String promotionName = "";
        if(allowance.getText().length()>0){
        	allowancePrice = Double.parseDouble(allowance.getText());
        }
        if(voucher.getText().length()>0){
        	voucherPrice = Double.parseDouble(voucher.getText());
        }
        if(promotion.getSelectionModel().getSelectedIndex()!=-1){
        	promotionName = promotions.get(promotion.getSelectionModel().getSelectedIndex()).promotionName;
        }
        SalesVO salesVO = new SalesVO(BillType.SALES, BillState.SUBMITTED, BillID.getText(), customerVO.customerName, customerVO.customerID, 
        		customerVO.salesman, Username.getText(), inventoryName, goodsItemList, allowancePrice, 
        		voucherPrice,remark.getText(),LocalDate.now().toString(), promotionName);
    	if(!isExamine){
	        if(isNew){
	        	salesBLService.submitSales(salesVO);
	        }
	        else{
	        	salesBLService.updateSales(salesVO);
	        }
	        salesStaffSalesOrderViewController.showSalesOrderList();
    	}
    	else{
    		salesBLService.updateSales(salesVO);
    		generalManagerExaminationCellController.clickReturnButton();
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
	                String promotionName = "";
	                if (customer.getSelectionModel().getSelectedIndex() >= 0){
	                    customerName = customers.get(customer.getSelectionModel().getSelectedIndex()).customerName;
	                    customerID = customers.get(customer.getSelectionModel().getSelectedIndex()).customerID;
	                    customerSalesman = customers.get(customer.getSelectionModel().getSelectedIndex()).salesman;
	                }
	                if (inventory.getSelectionModel().getSelectedIndex() >= 0){
	                	inventoryName = inventories.get(inventory.getSelectionModel().getSelectedIndex());
	                }
	                if(promotion.getSelectionModel().getSelectedIndex() >= 0){
	                	promotionName = promotions.get(promotion.getSelectionModel().getSelectedIndex()).promotionName;
	                }
	                goodsItemList.clear();
	            	for(GoodsItemBean bean:data){
	            		GoodsItemVO vo = new GoodsItemVO(bean.getID(), bean.getName(), bean.getModel(), bean.getAmount(), bean.getRetailPrice(), bean.getRemark());
	            		goodsItemList.add(vo);
	            	}
	                SalesVO salesVO = new SalesVO(BillType.SALES, BillState.DRAFT, BillID.getText(), customerName, customerID, customerSalesman, Username.getText(), inventoryName, goodsItemList, Double.parseDouble(allowance.getText()), Double.parseDouble(voucher.getText()),remark.getText(), LocalDate.now().toString(), promotionName);
	                salesBLService.saveSales(salesVO);
	            }
	
	            salesStaffSalesOrderViewController.showSalesOrderList();
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


    public void setSalesStaffSalesOrderViewController(SalesStaffSalesOrderViewController salesStaffSalesOrderViewController){
        this.salesStaffSalesOrderViewController = salesStaffSalesOrderViewController;
    }
    
    public void setGeneralManagerExaminationCellController(GeneralManagerExaminationCellController generalManagerExaminationCellController){
    	this.generalManagerExaminationCellController = generalManagerExaminationCellController;
    }
    
    public void setForDetailView(SalesVO salesBill){
    	isNew = false;
        BillID.setText(salesBill.ID);
        title.setText("销售单详情");
        addIcon.setVisible(false);
        deleteIcon.setVisible(false);
        remark.setText(salesBill.remarks);
        remark.setEditable(false);
        voucher.setText(String.valueOf(salesBill.voucher));
        voucher.setEditable(false);
        allowance.setText(String.valueOf(salesBill.allowance));
        allowance.setEditable(false);
        total.set(0);
        bargainAndGoods.set(0);
        afterSum.set(0);
        bargainPrice.set(0);
        allTotal.set(0);
        itemTable.setEditable(false);

        inventory.getSelectionModel().select(salesBill.inventory);
        inventory.setDisable(true);
        customer.getSelectionModel().select(salesBill.customer);
        customer.setDisable(true);
        if(!salesBill.promotionName.equals("")){
        	promotion.getSelectionModel().select(salesBill.promotionName);
        }
        else{
        	promotion.getSelectionModel().clearSelection();
        }
        promotion.setDisable(true);
        Username.setText(salesBill.user);
        

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if(!isExamine){
            		salesStaffSalesOrderViewController.showSalesOrderList();
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
        
        goodsItemList.clear();
        data.clear();
        for (GoodsItemVO goodsItemVO:salesBill.goodsItemList){
            goodsItemList.add(goodsItemVO);
            data.add(new GoodsItemBean(goodsItemVO.ID, goodsItemVO.goodsName, goodsItemVO.model, goodsItemVO.number, goodsItemVO.price, 
            		goodsItemVO.sum, goodsItemVO.remarks));
            total.set(total.get() + goodsItemVO.sum);
        }
        
        bargainData.clear();
        giftData.clear();
        if(!salesBill.promotionName.equals("")){
        	if(salesBLService.findPromotionBargainByName(salesBill.promotionName)!=null){
        		PromotionBargainVO promotionBargainVO = salesBLService.findPromotionBargainByName(salesBill.promotionName);
        		for (GoodsItemVO goodsItemVO:promotionBargainVO.bargains){
                    bargainData.add(new GoodsItemBean(goodsItemVO.ID, goodsItemVO.goodsName, goodsItemVO.model, goodsItemVO.number, goodsItemVO.price, 
                    		goodsItemVO.sum, goodsItemVO.remarks));
                }
        		bargainPrice.set(promotionBargainVO.bargainTotal);
        	}
        	else if(salesBLService.findPromotionCustomerByName(salesBill.promotionName)!=null){
        		PromotionCustomerVO promotionCustomerVO = salesBLService.findPromotionCustomerByName(salesBill.promotionName);
        		for (GoodsItemVO goodsItemVO:promotionCustomerVO.gifts){
                    giftData.add(new GoodsItemBean(goodsItemVO.ID, goodsItemVO.goodsName, goodsItemVO.model, goodsItemVO.number, goodsItemVO.price, 
                    		goodsItemVO.sum, goodsItemVO.remarks));
                }
        	}
        	else if(salesBLService.findPromotionTotalByName(salesBill.promotionName)!=null){
        		PromotionTotalVO promotionTotalVO = salesBLService.findPromotionTotalByName(salesBill.promotionName);
        		for (GoodsItemVO goodsItemVO:promotionTotalVO.gifts){
                    giftData.add(new GoodsItemBean(goodsItemVO.ID, goodsItemVO.goodsName, goodsItemVO.model, goodsItemVO.number, goodsItemVO.price, 
                    		goodsItemVO.sum, goodsItemVO.remarks));
                }
        	}
        }
        
        afterSum.set(total.get()-Double.parseDouble(voucher.getText())-Double.parseDouble(allowance.getText()));
    }
    
    public void setForEditView(){
    	addIcon.setVisible(true);
    	deleteIcon.setVisible(true);
        title.setText("编辑草稿单");
        remark.setEditable(true);
        voucher.setEditable(true);
        allowance.setEditable(true);
        inventory.setDisable(false);
        customer.setDisable(false);
        promotion.setDisable(false);
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
}
