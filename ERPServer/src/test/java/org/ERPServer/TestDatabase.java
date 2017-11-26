package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataservice.classificationdataservice.ClassificationDataService;
import dataservice.goodsdataservice.GoodsDataService;
import po.ClassificationPO;
import po.GoodsPO;

public class TestDatabase {
	SessionFactory sf;
	
	public TestDatabase(){
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	private Session setUp(){
		Session s = sf.openSession();
		s.beginTransaction();
		return s;
	}
	
	private void end(Session s){
		s.getTransaction().commit();
		s.close();
	}
	
	public void testClassification(){
		Session s = setUp();
////		第一次使用请取消这段注释，成功运行一次后再注释掉
//		ClassificationPO clapo = new ClassificationPO();
//		clapo.setName("灯");
//		GoodsPO gpo = new GoodsPO("圣洁牌经典黑白款落地灯", "L", null, 500, 50, 233.3, 250, 233.3, 250);
//		List<GoodsPO> goods = new ArrayList<GoodsPO>();
//		goods.add(gpo);
//		clapo.setGoods(goods);
//		s.save(clapo);
//		s.save(gpo);
//		end(s);
//		s = setUp();
		
		ClassificationDataService claImpl = ClassificationDataServiceImpl.getInstance();
		
		ClassificationPO father;
		try {
			father = claImpl.find(1);
			claImpl.add(new ClassificationPO("落地灯", father, null, null));
			
			ArrayList<ClassificationPO> claPOs = claImpl.show();
			System.out.println("共有" + claPOs.size() + "条记录：");
			for(ClassificationPO po : claPOs){
				System.out.println(po.getId() + " " + po.getName());
			}
			System.out.println("查询结束");
		} catch (RemoteException e) {
			System.out.println("Exception！");
			e.printStackTrace();
		}
		end(s);
	}
	
	public void testGoods(){
		Session session = setUp();
		
		GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
		
		end(session);
	}

	public static void main(String[] args) {
		TestDatabase test = new TestDatabase();
		
//		test.testClassification();
		test.testGoods();
		
		test.sf.close();
	}

}
