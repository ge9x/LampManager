package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataimpl.salesdataimpl.SalesDataServiceImpl;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;

public class TestSales {
	public static void main(String[] args){
		SalesDataService salesImpl=SalesDataServiceImpl.getInstance();
		
		try{
//			第一次使用请取消这段注释，成功运行一次后再注释掉
			
			GoodsItemPO gi1=new GoodsItemPO(5, "霓虹灯","大", 20, 35.0,
					"耐用");
			salesImpl.addGoodsItem(gi1);
			GoodsItemPO gi2=new GoodsItemPO(6, "挂灯","小", 10, 35.0,
					"好看");
			salesImpl.addGoodsItem(gi2);
			GoodsItemPO gi3=new GoodsItemPO(7, "挂灯","小", 10, 35.0,
					"好看");
			salesImpl.addGoodsItem(gi3);
			
			
			List<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
			goodsItemList.add(gi1);
			goodsItemList.add(gi2);
			goodsItemList.add(gi3);
			
			PurchasePO pur=new PurchasePO(BillType.PURCHASE,BillState.PASS,"供应商1"
				,1,"默认仓库","阿红",goodsItemList,"满足客户需求"
			     ,new Date().toString());
		
			SalesPO sal=new SalesPO(BillType.SALES, BillState.DRAFT,"阿强",3, "销售商1", "业务员1",
				"默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date().toString());
	
			salesImpl.addPurchase(pur);
			salesImpl.addSales(sal);
			
			ArrayList<PurchasePO> purList=salesImpl.showPurchase();
			ArrayList<SalesPO> salList=salesImpl.showSales();
			System.out.println("共有"+purList.size()+"条进货单记录");
			for(PurchasePO po:purList){
				System.out.println("单据编号："+po.getID()+" 单据类型："+po.getType()+" 单据状态:"+po.getState()+" 商品清单："+po.getGoodsItemList());
			}

			System.out.println("共有"+salList.size()+"条销售单记录");
			for(SalesPO po:salList){
				System.out.println("单据编号："+po.getID()+" 单据类型："+po.getType()+" 单据状态:"+po.getState()+" 商品清单："+po.getGoodsItemList());
			}
			System.out.println("查询结束");
			
		}catch(RemoteException e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
	}
}
