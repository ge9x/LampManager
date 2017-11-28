package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;

public class TestClassification {

	public static void main(String[] args) {
		ClassificationDataService claImpl = ClassificationDataServiceImpl.getInstance();
		
		try {
//			第一次使用请取消这段注释，成功运行一次后再注释掉
//			ClassificationPO clapo = new ClassificationPO();
//			clapo.setName("灯");
//			GoodsPO gpo = new GoodsPO("圣洁牌经典黑白款落地灯", "L", null, 500, 50, 233.3, 250, 233.3, 250);
//			ArrayList<GoodsPO> goods = new ArrayList<GoodsPO>();
//			goods.add(gpo);
//			clapo.setGoods(goods);
//			s.save(clapo);
//			s.save(gpo);
//			ClassificationPO father = claImpl.find(1);
//			claImpl.add(new ClassificationPO("落地灯", father, null, null));
//			ClassificationPO father = claImpl.find(2);
//			claImpl.add(new ClassificationPO("可折叠落地灯", father, null, null));
			
			ArrayList<ClassificationPO> claPOs = claImpl.show();
			System.out.println("共有" + claPOs.size() + "条商品分类记录：");
			for(ClassificationPO po : claPOs){
				System.out.println(po.getID() + " " + po.getName() + " " + po.getGoods());
			}
			System.out.println("查询结束");
		} catch (RemoteException e) {
			System.out.println("Exception！");
			e.printStackTrace();
		}
	}

}
