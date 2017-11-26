package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;
import po.GoodsPO;
import util.ResultMessage;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class ClassificationDataService_Stub implements ClassificationDataService{
	ArrayList<ClassificationPO> data;
	{
		data = new ArrayList<ClassificationPO>();
		ClassificationPO po1 = new ClassificationPO(1, "灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
		ClassificationPO po2 = new ClassificationPO(2, "落地灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
		ClassificationPO po3 = new ClassificationPO(3, "台灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
		ClassificationPO po4 = new ClassificationPO(4, "吊灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
		ClassificationPO po5 = new ClassificationPO(5, "霓虹灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
		data.add(po1);
		data.add(po2);
		data.add(po3);
		data.add(po4);
		data.add(po5);
	}

	public ArrayList<ClassificationPO> show() throws RemoteException {
		return data;
	}
	
	public ClassificationPO find(int ID) throws RemoteException {
		for(ClassificationPO po : data){
			if(po.getId() == ID){
				return po;
			}
		}
		return null;
	}

	public ResultMessage add(ClassificationPO po) throws RemoteException {
		for(ClassificationPO cpo : data){
			if(cpo.getId() == po.getId()){
				System.out.println("add classification failed");
				return ResultMessage.FAILED;
			}
		}
		data.add(po);
		System.out.println("add classification success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(ClassificationPO po) throws RemoteException {
		for(ClassificationPO cpo : data){
			if(cpo.getId() == po.getId()){
				data.remove(cpo);
				System.out.println("delete classification success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("delete classification failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage update(ClassificationPO po) throws RemoteException {
		for(ClassificationPO cpo : data){
			if(cpo.getId() == po.getId()){
				data.remove(cpo);
				data.add(po);
				System.out.println("update classification success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update classification failed");
		return ResultMessage.FAILED;
	}

}
