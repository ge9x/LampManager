package org.ERPServer;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsPO;
import po.PromotionPO;
import util.Level;
import util.PromotionType;

public class TestPromotion {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		ClassificationPO clapo = new ClassificationPO();
		clapo.setId("08");
		clapo.setName("灯");
		GoodsPO gpo1 = new GoodsPO("0100011", "圣洁牌经典黑白款落地灯", "L", null, 500, 50, 233.3, 250, 233.3, 250);
		List<GoodsPO> goods=new ArrayList<GoodsPO>();
		goods.add(gpo1);
		PromotionPO pro=new PromotionPO("00000006", 300, 400, new Date(), new Date(), null, 20.0, 30.0, null, Level.LEVEL_FOUR, 800, PromotionType.MEMBER_PROMOTION_STRATEGY);
		List<PromotionPO> cus=new ArrayList<PromotionPO>();
		cus.add(pro);
		
		s.save(clapo);
		s.save(gpo1);
		s.save(pro);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
  }
}
