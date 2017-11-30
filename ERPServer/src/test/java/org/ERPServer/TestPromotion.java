package org.ERPServer;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsItemPO;
import po.GoodsPO;
import po.PromotionPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
import util.CustomerCategory;
import util.Level;
import util.PromotionType;

public class TestPromotion {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		GoodsItemPO gi1=new GoodsItemPO(1, "霓虹灯",null, 20, 35.0,
				"耐用");
		//salesImpl.addGoodsItem(gi1);
		GoodsItemPO gi2=new GoodsItemPO(2, "挂灯",null, 10, 35.0,
				"好看");
		//salesImpl.addGoodsItem(gi2);
		ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		
		PurchasePO pur=new PurchasePO(BillType.PURCHASE,BillState.PASS,"供应商1"
			,1,"默认仓库","阿红",goodsItemList,"满足客户需求"
		     ,new Date().toString());

		s.save(gi1);
		s.save(gi2);
		s.save(pur);


		
		s.getTransaction().commit();
		s.close();
		sf.close();
  }
}
