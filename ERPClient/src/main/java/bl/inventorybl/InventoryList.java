package bl.inventorybl;

import java.util.ArrayList;

import blservice.salesblservice.SalesInfo;
import util.ResultMessage;

/**
 * 库存变动情况的清单
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryList {
	private ArrayList<InventoryLineItem> inventoryLineItem;
	
	public InventoryList(){
		inventoryLineItem = new ArrayList<InventoryLineItem>();
	}
	
	/**
	 * 通过Sales单据添加一条库存变动情况
	 * @param salesInfo Sales单据的信息
	 * @return 是否成功添加
	 */
	public ResultMessage add(SalesInfo salesInfo){
		return null;
	}
	
	/**
	 * 通过Sales单据删除一条库存变动情况
	 * @param salesInfo Sales单据的信息
	 * @return 是否成功删除
	 */
	public ResultMessage delete(SalesInfo salesInfo){
		return null;
	}
	
	/**
	 * 得到所有库存变动情况
	 * @return 含有所有库存变动情况的链表
	 */
	public ArrayList<InventoryLineItem> getList(){
		return inventoryLineItem;
	}
}
