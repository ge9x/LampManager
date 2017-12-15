package vo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryViewVO {
	/**
	 * 开始时间点
	 */
	public String startDate;
	/**
	 * 截止时间点
	 */
	public String endDate;
	/**
	 * 仓库
	 */
	public String inventory;
	/**
	 * 每张单据中每种商品的库存变动条目
	 */
	public ArrayList<InventoryViewItemVO> item;
	/**
	 * 商品数量合计
	 */
	public HashMap<GoodsVO, Double> total;
	
	public InventoryViewVO(String startDate, String endDate, String inventory, ArrayList<InventoryViewItemVO> item,
			HashMap<GoodsVO, Double> total) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.inventory = inventory;
		this.item = item;
		this.total = total;
	}
}