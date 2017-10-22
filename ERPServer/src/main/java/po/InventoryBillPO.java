package po;

import java.util.Date;
import java.util.HashMap;

import util.BillState;
import util.BillType;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryBillPO extends BillPO{
	/**
	 * 该单据内的商品对应的溢出/缺损数量
	 */
	private HashMap<GoodsPO, Integer> goodsMap;
	
	public InventoryBillPO(String ID, BillType type, BillState state, Date date, HashMap<GoodsPO, Integer> goodsMap) {
		super(ID, date, type, state);
		this.goodsMap = goodsMap;
	}

	public HashMap<GoodsPO, Integer> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(HashMap<GoodsPO, Integer> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
}
