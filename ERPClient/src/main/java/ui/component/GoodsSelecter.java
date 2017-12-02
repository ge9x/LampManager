package ui.component;



import java.util.ArrayList;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import ui.component.GoodsTable.GoodsBean;

public class GoodsSelecter {

	Dialog dialog;
	TableView<GoodsBean> table;
	GoodsTable goodsTable;
	
	public GoodsSelecter(){
		dialog = new Dialog();
		goodsTable = new GoodsTable();
		
		table = goodsTable.getTable();
		dialog.getDialogPane().setContent(table);
		ButtonType addButtonType = new ButtonType("添加",ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType,ButtonType.CANCEL);
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == addButtonType) {
		    	
		        return table.getSelectionModel().getSelectedItem();
		    }
		    return null;
		});
	}
	public Dialog getGoodsDialog(){
		return dialog;
	}
}
