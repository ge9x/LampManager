package bl.salesbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsInfo;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import rmi.SalesDataRemoteObject;
import rmi.SalesRemoteHelper;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;

public class GoodsItem {
    GoodsInfo goodsInfo;
    
    private static SalesDataService salesDataService;
	
	public GoodsItem(){
		goodsInfo=new GoodsController();
		salesDataService=SalesRemoteHelper.getInstance().getSalesDataService();
	}
	public ResultMessage addGoodsItem(GoodsItemVO item) throws RemoteException{
		return salesDataService.addGoodsItem(voTopo(item));
	}
	
	public String getGoodsID(String goodsName){
		return null;
	}
	
	public String getModel(String goodsName){
		return null;
	}
	
    public static GoodsItemPO voTopo(GoodsItemVO vo){
	  	return new GoodsItemPO(vo.ID,vo.goodsName,vo.model,vo.number,vo.price,vo.remarks);
	}
	    
    public static GoodsItemVO poTovo(GoodsItemPO po){
	   	return new GoodsItemVO(po.getGoodsID(), po.getGoodsName(), po.getModel(), po.getNumber(), po.getPrice(), po.getRemarks());
    }
}
