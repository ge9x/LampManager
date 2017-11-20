package org.ERPServer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import po.ClassificationPO;
import po.GoodsPO;

public class TestDatabase {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		ClassificationPO clapo = new ClassificationPO();
		clapo.setId("01");
		clapo.setName("灯");
		GoodsPO gpo = new GoodsPO("0100001", "圣洁牌经典黑白款落地灯", "L", clapo, 500, 50, 233.3, 250, 233.3, 250);
		List<GoodsPO> goods = new ArrayList<GoodsPO>();
		goods.add(gpo);
		clapo.setGoods(goods);
		
		s.save(clapo);
		s.save(gpo);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

}
