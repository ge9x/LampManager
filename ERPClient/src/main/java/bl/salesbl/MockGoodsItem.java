package bl.salesbl;

import java.util.ArrayList;

import vo.GoodsItemVO;

public class MockGoodsItem {
    ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();
	
	GoodsItemVO gi1=new GoodsItemVO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO( "挂灯", 10, 35.0,
			"好看");

	{
		goodsItemList.add(gi1);
    	goodsItemList.add(gi2);
	}
	
    public void addGoodsItem(GoodsItemVO item){
    	GoodsItemVO gi3=new GoodsItemVO( "台灯", 20, 35.0,
    			"耐用");
    	goodsItemList.add(gi3);
	}
    
    public String getGoodsID(String goodsName){
		for(GoodsItemVO vo:goodsItemList){
			if(vo.goodsName.equals(goodsName)){
				return vo.ID;
			}
		}
		System.out.println("goods not exist");
		return null;
	}
	
	public String getModel(String goodsName){
		for(GoodsItemVO vo:goodsItemList){
			if(vo.goodsName.equals(goodsName)){
				return vo.model;
			}
		}
		System.out.println("goods not exist");
		return null;
	}
}
