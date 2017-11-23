package org.ERPServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsPO;
import po.PromotionPO;
import util.CustomerCategory;
import util.Level;
import util.PromotionType;

public class TestDatabase {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		

		ClassificationPO clapo = new ClassificationPO();
		clapo.setId("11");
		clapo.setName("灯");
		GoodsPO gpo = new GoodsPO("0100011", "圣洁牌经典黑白款落地灯", "L", null, 500, 50, 233.3, 250, 233.3, 250);
		List<GoodsPO> goods = new ArrayList<GoodsPO>();
		goods.add(gpo);
		clapo.setGoods(goods);
       
		PromotionPO pro=new PromotionPO("00000009", 300, 400, new Date(), new Date(), null, 20.0, 30.0, null, Level.LEVEL_FOUR, 800, PromotionType.MEMBER_PROMOTION_STRATEGY);
		
		//s.save(clapo);
		//s.save(gpo);
		s.save(pro);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

}
