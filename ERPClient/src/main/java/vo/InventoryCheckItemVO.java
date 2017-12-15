package vo;

import ExcelUtil.impl.ExcelColumnName;

/**
 * Created on 2017/12/15
 * 
 * @author 巽
 *
 */
public class InventoryCheckItemVO {
	/**
	 * 商品ID
	 */
	@ExcelColumnName(value = "编号")
	public String ID;
	/**
	 * 商品名称
	 */
	@ExcelColumnName(value = "商品名称")
	public String name;
	/**
	 * 商品型号
	 */
	@ExcelColumnName(value = "型号")
	public String model;
	/**
	 * 商品数量
	 */
	@ExcelColumnName(value = "库存数量")
	public int amount;
	/**
	 * 商品均价
	 */
	@ExcelColumnName(value = "商品均价")
	public double averagePrice;

	public InventoryCheckItemVO(String ID, String name, String model, int amount, double averagePrice) {
		this.ID = ID;
		this.name = name;
		this.model = model;
		this.amount = amount;
		this.averagePrice = averagePrice;
	}
}
