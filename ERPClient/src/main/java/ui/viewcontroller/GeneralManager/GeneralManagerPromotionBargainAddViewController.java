package ui.viewcontroller.GeneralManager;

import java.util.Optional;

import com.jfoenix.controls.JFXTextField;

import blservice.promotionblservice.promotionbargain.PromotionBargainBLService;
import blservice.userblservice.UserBLService;
import blstubdriver.PromotionBargain_Stub;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import ui.component.DialogFactory;
import ui.component.GoodsSelecter;
import ui.component.GoodsTable.GoodsBean;
import util.Money;
import vo.PromotionBargainVO;

public class GeneralManagerPromotionBargainAddViewController {
	
	GeneralManagerPromotionViewController generalManagerPromotionViewController;
	PromotionBargainBLService promotionBargainService = new PromotionBargain_Stub();
	UserBLService userBLService = new UserBLService_Stub();
	PromotionBargainVO promotionBargain;
	
	TableView<GoodsItemBean> itemTable;
    ObservableList<GoodsItemBean> data =
            FXCollections.observableArrayList();
    DoubleProperty total = new SimpleDoubleProperty(0);

	@FXML
	JFXTextField promotionName;
	
	@FXML
	Text Total;
	
	@FXML
	VBox vbox;
	
	@FXML
	DatePicker startDate;
	
	@FXML
	DatePicker endDate;
	
	@FXML
	JFXTextField bargainTotal;
	
	@FXML
	Label addIcon;
	
	@FXML
	Label promotionID;
	
	@FXML
	Text username;
	
	@FXML
	public void intialize(){
		addIcon.setText("\ue61e");
		Total.setText(Money.getMoneyString(0));
		
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
	
	public void setGeneralManagerPromotionViewController (GeneralManagerPromotionViewController generalManagerPromotionViewController){
		this.generalManagerPromotionViewController = generalManagerPromotionViewController;
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
	}
	
	public void clickOKButton(){
		if(isCompleted()){
			
		}
		else{
			Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("促销策略信息填写不完整");
	        Optional result = dialog.showAndWait();
		}
	}
	
	public boolean isCompleted(){
		if(promotionName.getText().length()>0&&bargainTotal.getText().length()>0){
			return true;
		}
		else{
	        return false;
		}
	}
	
	public void clickCancelButton(){
		Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定放弃添加策略吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
//            	salesStaffCustomerInfoViewController.clickReturnButton();
            }
        }
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

        public void setTotalPrice(double totalPrice) {
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
