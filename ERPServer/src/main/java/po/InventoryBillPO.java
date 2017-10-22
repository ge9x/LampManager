package po;

import java.util.Date;
import java.util.HashMap;

import util.BillState;
import util.BillType;
import util.UserPosition;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryBillPO extends BillPO{
	/**
	 * 该单据涉及的仓库
	 */
	private String inventory;
	/**
	 * 操作员
	 */
	private UserPosition user;
	/**
	 * 该单据内的商品对应的溢出/缺损数量
	 */
	private HashMap<GoodsPO, Integer> goodsMap;
	
	public InventoryBillPO(String ID, BillType type, BillState state, Date date, String inventory, UserPosition user, HashMap<GoodsPO, Integer> goodsMap) {
		super(ID, date, type, state);
		this.inventory = inventory;
		this.user = user;
		this.goodsMap = goodsMap;
	}
	
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public UserPosition getUser() {
		return user;
	}

	public void setUser(UserPosition user) {
		this.user = user;
	}

	public HashMap<GoodsPO, Integer> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(HashMap<GoodsPO, Integer> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
}
