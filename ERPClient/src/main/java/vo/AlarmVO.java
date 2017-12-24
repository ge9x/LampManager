package vo;

/**
 * Created on 2017/12/22
 * 
 * @author 巽
 *
 */
public class AlarmVO {
	/**
	 * 商品ID
	 */
	public String goodsID;
	/**
	 * 商品名称
	 */
	public String goodsName;
	/**
	 * 商品型号
	 */
	public String goodsModel;
	/**
	 * 商品数量
	 */
	public int goodsNumber;
	/**
	 * 警戒数量
	 */
	public int alarmNumber;
	/**
	 * 建议进货数量
	 */
	public int numberSuggestAdding;

	public AlarmVO(String goodsID, String goodsName, String goodsModel, int goodsNumber, int alarmNumber,
			int numberSuggestAdding) {
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.goodsNumber = goodsNumber;
		this.alarmNumber = alarmNumber;
		this.numberSuggestAdding = numberSuggestAdding;
	}
}
