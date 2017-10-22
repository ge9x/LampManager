package vo;

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
public class InventoryBillVO extends BillVO{
	/**
	 * 该单据涉及的仓库
	 */
	public String inventory;
	/**
	 * 操作员
	 */
	public UserPosition user;
	/**
	 * 该单据内的商品对应的溢出/缺损/赠送/报警数量（报警数量为商品警戒数量，实际数量由商品引用取得）
	 */
	public HashMap<GoodsVO, Integer> goodsMap;
	
	public InventoryBillVO(String ID, BillType type, BillState state, Date date, String inventory, UserPosition user, HashMap<GoodsVO, Integer> goodsMap) {
		super();
		this.ID = ID;
		this.type = type;
		this.state = state;
		this.date = date;
		this.goodsMap = goodsMap;
	}

}
