package vo;

/**
 * Created on 2017/11/14
 * @author 巽
 *
 */
public class GoodsIdentityVO {
	/**
	 * 商品ID
	 */
	public String ID;
	/**
	 * 商品名称
	 */
	public String name;
	/**
	 * 商品型号
	 */
	public String model;
	
	public GoodsIdentityVO(String ID, String name, String model) {
		super();
		this.ID = ID;
		this.name = name;
		this.model = model;
	}
}
