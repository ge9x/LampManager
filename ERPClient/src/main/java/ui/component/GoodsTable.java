package ui.component;

import bean.GoodsBean;
import blservice.goodsblservice.GoodsBLService;
import blstubdriver.GoodsBLService_Stub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import vo.GoodsVO;

import java.util.ArrayList;

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
	}