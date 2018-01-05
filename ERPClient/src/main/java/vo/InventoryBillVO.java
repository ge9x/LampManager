package vo;

import java.util.HashMap;

import util.BillState;
import util.BillType;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryBillVO extends BillVO{
    private static final String seperator = System.lineSeparator();
	/**
	 * 该单据涉及的仓库
	 */
	public String inventory;
	/**
	 * 操作员
	 */
	public String user;
	/**
	 * 该单据内的商品对应的溢出/缺损/赠送/报警数量（报警数量为商品警戒数量，要得到该商品实际数量应通过商品VO）
	 */
	public HashMap<GoodsVO, Integer> goodsMap;


	public InventoryBillVO(String ID, BillType type, BillState state, String date, String inventory, String user, HashMap<GoodsVO, Integer> goodsMap) {
		super();
		this.ID = ID;
		this.type = type;
		this.state = state;
		this.date = date;
		this.inventory = inventory;
		this.user = user;
		this.goodsMap = goodsMap;
	}
	@Override
    public String toString(){
	    return  super.toString()+
                "仓库: " + inventory + seperator +
                "操作员: " + user + seperator +
                goodsMapToString();


    }

    private String goodsMapToString() {
        String str = "";
        for (GoodsVO vo : goodsMap.keySet()){
            str += (vo.ID + "\t" +  vo.name + "\t" +goodsMap.get(vo) + seperator);
        }
        return "商品列表: " + seperator + str;
    }

}
