package bl.inventorybl;

import java.util.ArrayList;

import blservice.salesblservice.SalesInfo;
import util.ResultMessage;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryList {
	private ArrayList<InventoryLineItem> inventoryLineItem;
	
	public InventoryList(){
		inventoryLineItem = new ArrayList<InventoryLineItem>();
	}
	
	public ResultMessage add(SalesInfo salesInfo){
		return null;
	}
	
	public ResultMessage delete(SalesInfo salesInfo){
		return null;
	}
	
	public ArrayList<InventoryLineItem> getList(){
		return inventoryLineItem;
	}
}
