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
public class InventoryBillPO extends PO{
	/**
	 * 单据ID
	 */
	private String id;
	/**
	 * 单据类型
	 */
	private BillType type;
	/**
	 * 单据状态
	 */
	private BillState state;
	/**
	 * 单据提交时间
	 */
	private Date date;
	/**
	 * 该单据内的商品对应的溢出/缺损数量
	 */
	private HashMap<GoodsPO, Integer> goodsMap;
	
	public InventoryBillPO(String id, BillType type, BillState state, Date date, HashMap<GoodsPO, Integer> goodsMap) {
		super();
		this.id = id;
		this.type = type;
		this.state = state;
		this.date = date;
		this.goodsMap = goodsMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
	}

	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public HashMap<GoodsPO, Integer> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(HashMap<GoodsPO, Integer> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
}
