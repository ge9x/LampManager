package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import util.Criterion;
import util.ResultMessage;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class GoodsDataService_Stub implements GoodsDataService{
	ArrayList<GoodsPO> data;
	
	{
		data = new ArrayList<GoodsPO>();
		GoodsPO g1 = new GoodsPO(1, "圣洁牌纯黑落地灯", "L", null, 250, 233.3, 250, 233.3, 250);
		GoodsPO g2 = new GoodsPO(2, "圣洁牌纯白落地灯", "M", null, 250, 233.3, 250, 233.3, 250);
		GoodsPO g3 = new GoodsPO(3, "圣洁牌简洁黑白配台灯", "S", null, 2500, 233.3, 250, 233.3, 250);
		GoodsPO g4 = new GoodsPO(4, "圣洁牌古典吊灯", "LL", null, 250, 2333.3, 2500, 2333.3, 2500);
		GoodsPO g5 = new GoodsPO(5, "圣洁牌后现代主义七彩霓虹灯", "LLL", null, 3, 23333.3, 250000, 23333.3, 250000);
		data.add(g1);
		data.add(g2);
		data.add(g3);
		data.add(g4);
		data.add(g5);
	}

	public ArrayList<GoodsPO> show() throws RemoteException {
		return data;
	}

	public GoodsPO find(int ID) throws RemoteException {
		for(GoodsPO po : data){
			if(po.getID() == ID){
				return po;
			}
		}
		return null;
	}

	public ResultMessage add(GoodsPO po) throws RemoteException {
		for(GoodsPO gpo : data){
			if(gpo.getID() == po.getID()){
				System.out.println("add goods failed");
				return ResultMessage.FAILED;
			}
		}
		data.add(po);
		System.out.println("add goods success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(GoodsPO po) throws RemoteException {
		for(GoodsPO gpo : data){
			if(gpo.getID() == po.getID()){
				data.remove(gpo);
				System.out.println("delete goods success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("delete goods failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage update(GoodsPO po) throws RemoteException {
		for(GoodsPO gpo : data){
			if(gpo.getID() == po.getID()){
				data.remove(gpo);
				data.add(po);
				System.out.println("update goods success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update goods failed");
		return ResultMessage.FAILED;
	}

	@Override
	public String getNewID(String classificationID) throws RemoteException {
		return classificationID + "000001";
	}

	/**
	 * 后加入的方法，桩不予实现
	 */
	@Override
	public ArrayList<GoodsPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return data;
	}

}
