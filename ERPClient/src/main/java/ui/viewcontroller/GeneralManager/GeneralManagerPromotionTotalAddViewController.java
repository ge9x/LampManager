package ui.viewcontroller.GeneralManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import bean.GoodsItemBean;
import blservice.promotionblservice.promotionTotal.PromotionTotalBLService;
import blstubdriver.PromotionTotal_Stub;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import ui.component.DialogFactory;
import ui.component.GoodsSelecter;
import ui.component.GoodsTable.GoodsBean;
import util.Money;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class GeneralManagerPromotionTotalAddViewController {

	GeneralManagerPromotionViewController generalManagerPromotionViewController;
	PromotionTotalBLService promotionTotalBLService = new PromotionTotal_Stub();
	PromotionTotalVO promotionTotal;
	ArrayList<GoodsItemVO> gifts = new ArrayList<>();
	
	boolean isNew;
	
	TableView<GoodsItemBean> itemTable;
    ObservableList<GoodsItemBean> data =
            FXCollections.observableArrayList();
    DoubleProperty total = new SimpleDoubleProperty(0);
		
	@FXML
	Label promotionID;
	
	@FXML
	JFXTextField promotionName;
	
	@FXML
	JFXTextField targetPriceField;
	
	@FXML
	JFXTextField voucherField;
	
	@FXML
	Text username;
	
	@FXML
	Text Total;
	
	@FXML
	DatePicker startDate;
	
	@FXML
	DatePicker endDate;
	
	@FXML
	Label deleteIcon;
	
	@FXML
	Label addIcon;
	
	@FXML
	VBox vbox;
	
    @FXML
    Label title;

    @FXML
    JFXButton submitButton;

    @FXML
    JFXButton cancelButton;
	
	@FXML
	public void initialize(){
		deleteIcon.setText("\ue606");
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
        
        //初始化时间选择器
        startDate.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(
                                    startDate.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                            }   
                    }
                };
            }
        };
        endDate.setDayCellFactory(dayCellFactory);
        endDate.setValue(startDate.getValue().plusDays(1));

	}
	
	public void setGeneralManagerPromotionViewController (GeneralManagerPromotionViewController generalManagerPromotionViewController){
		this.generalManagerPromotionViewController = generalManagerPromotionViewController;
	}
	
	public void addPromotionTotal(){
		String ID = promotionTotalBLService.getNewPromotionTotalID();
		promotionID.setText(ID);
		isNew = true;
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
    	data.add(new GoodsItemBean(bean.getID(), bean.getName(), bean.getModel(), 0, bean.getRecentPurchasePrice(), 0,""));
	}
	
	public void clickOKButton(){
		if(promotionName.getText().length()>0&&targetPriceField.getText().length()>0){
			promotionTotal = new PromotionTotalVO(promotionName.getText(), promotionID.getText(),  startDate.getValue().toString(), 
					endDate.getValue().toString(), Double.parseDouble(voucherField.getText()),  gifts, Double.parseDouble(targetPriceField.getText()));
			if(isNew){
				promotionTotalBLService.submit(promotionTotal);
			}
			else{
				promotionTotalBLService.updatePromotion(promotionTotal);
			}
		}
		else{
			Dialog dialog = DialogFactory.getInformationAlert();
	        dialog.setHeaderText("促销策略信息填写不完整");
	        Optional result = dialog.showAndWait();
		}
	}
	
	public void clickCancelButton(){
		Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定放弃添加策略吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	generalManagerPromotionViewController.clickReturnButton();
            }
        }
	}
	
    public void setForDetailView(PromotionTotalVO promotionTotalVO){
    	isNew = false;
        promotionID.setText(promotionTotalVO.promotionID);
        promotionName.setText(promotionTotalVO.promotionName);
        targetPriceField.setText(String.valueOf(promotionTotalVO.totalPrice));
        voucherField.setText(String.valueOf(promotionTotalVO.voucher));
        startDate.setValue(LocalDate.parse(promotionTotalVO.startDate));
        endDate.setValue(LocalDate.parse(promotionTotalVO.endDate));
        title.setText("进货单详情");
        addIcon.setVisible(false);
        deleteIcon.setVisible(false); 
        promotionName.setEditable(false);
        targetPriceField.setEditable(false);
        voucherField.setEditable(false);
        startDate.setDisable(true);
        endDate.setDisable(true);

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                generalManagerPromotionViewController.clickReturnButton();
            }
        });
        
        submitButton.setText("编 辑");
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setForEditView();
            }
        });

        for (GoodsItemVO goodsItemVO:promotionTotalVO.gifts){
            gifts.add(goodsItemVO);
            data.add(new GoodsItemBean(goodsItemVO.ID, goodsItemVO.goodsName, goodsItemVO.model, goodsItemVO.number, goodsItemVO.price, 
            		goodsItemVO.sum, goodsItemVO.remarks));
            total.set(total.get() + goodsItemVO.sum);
        }
    }
    
    public void setForEditView(){
    	addIcon.setVisible(true);
    	deleteIcon.setVisible(true);
        title.setText("编辑特价包促销策略");
        promotionName.setEditable(true);
        targetPriceField.setEditable(true);
        voucherField.setEditable(true);
        startDate.setDisable(false);
        endDate.setDisable(false);
        
        submitButton.setText("提 交");
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickOKButton();
            }
        });
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                clickCancelButton();
            }
        });
    }
}
