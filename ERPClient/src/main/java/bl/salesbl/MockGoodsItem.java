package bl.salesbl;

import java.util.ArrayList;

import po.GoodsItemPO;
import vo.GoodsItemVO;

public class MockGoodsItem {
    ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO( "挂灯", 10, 35.0,
			"好看");

    public void addGoodsItem(GoodsItemVO item){
    	goodsItemList.add(gi1);
    	goodsItemList.add(gi2);
    	GoodsItemPO gi3=new GoodsItemPO( "台灯", 20, 35.0,
    			"耐用");
    	goodsItemList.add(gi3);
	}
}
