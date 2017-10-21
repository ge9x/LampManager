package vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import util.InventoryViewItemType;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryViewVO {
	/**
	 * 开始时间点
	 */
	Date startDate;
	/**
	 * 截止时间点
	 */
	Date endDate;
	/**
	 * 仓库
	 */
	String inventory;
	/**
	 * 每张单据中每种商品的库存变动条目
	 */
	ArrayList<InventoryViewItemVO> item;
	/**
	 * 商品数量合计
	 */
	HashMap<GoodsVO, Double> total;
}

/**
 * 一张单据中一种商品的库存变动条目<br><br>
 * Created on 2017/10/21
 * @author 巽
 *
 */
class InventoryViewItemVO{
	/**
	 * 商品
	 */
	public GoodsVO goods;
	/**
	 * 库存变动类型
	 */
	public InventoryViewItemType type;
	/**
	 * 数量
	 */
	public int amount;
	/**
	 * 金额
	 */
	public double price;
}
