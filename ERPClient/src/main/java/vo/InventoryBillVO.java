package vo;

import java.util.Date;
import java.util.HashMap;

import util.BillState;
import util.BillType;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryBillVO extends BillVO{
//	/**
//	 * 单据ID
//	 */
//	String id;
//	/**
//	 * 单据类型
//	 */
//	BillType type;
//	/**
//	 * 单据状态
//	 */
//	BillState state;
//	/**
//	 * 单据提交时间
//	 */
//	Date date;
	/**
	 * 该单据内的商品对应的溢出/缺损/赠送/报警数量（报警数量为商品警戒数量，实际数量由商品引用取得）
	 */
	public HashMap<GoodsVO, Integer> goodsMap;
	
	public InventoryBillVO(String id, BillType type, BillState state, Date date, HashMap<GoodsVO, Integer> goodsMap) {
		super();
		this.ID = id;
		this.type = type;
		this.state = state;
		this.date = date;
		this.goodsMap = goodsMap;
	}

}
