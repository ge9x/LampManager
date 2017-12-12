package bl.salesbl;

import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsInfo;
import po.GoodsItemPO;
import vo.GoodsItemVO;

public class GoodsItem {
    GoodsInfo goodsInfo;
	
	public GoodsItem(){
		goodsInfo=new GoodsController();
	}
	
    public static GoodsItemPO voTopo(GoodsItemVO vo){
	  	return new GoodsItemPO(vo.ID,vo.goodsName,vo.model,vo.number,vo.price,vo.remarks);
	}
	    
    public static GoodsItemVO poTovo(GoodsItemPO po){
	   	return new GoodsItemVO(po.getGoodsID(), po.getGoodsName(), po.getModel(), po.getNumber(), po.getPrice(), po.getRemarks());
    }
}
