package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
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
		GoodsPO g1 = new GoodsPO("02000001", "圣洁牌纯黑落地灯", "L", "落地灯", 700, 250, 233.3, 250, 233.3, 250);
		GoodsPO g2 = new GoodsPO("02000002", "圣洁牌纯白落地灯", "M", "落地灯", 700, 250, 233.3, 250, 233.3, 250);
		GoodsPO g3 = new GoodsPO("03000003", "圣洁牌简洁黑白配台灯", "S", "台灯", 7000, 2500, 233.3, 250, 233.3, 250);
		GoodsPO g4 = new GoodsPO("04000004", "圣洁牌古典吊灯", "LL", "吊灯", 70, 250, 2333.3, 2500, 2333.3, 2500);
		GoodsPO g5 = new GoodsPO("05000005", "圣洁牌后现代主义七彩霓虹灯", "LLL", "霓虹灯", 7, 3, 23333.3, 250000, 23333.3, 250000);
		data.add(g1);
		data.add(g2);
		data.add(g3);
		data.add(g4);
		data.add(g5);
	}

	public ArrayList<GoodsPO> show() throws RemoteException {
		return data;
	}

	public GoodsPO find(String ID) throws RemoteException {
		for(GoodsPO po : data){
			if(po.getId().endsWith(ID)){
				return po;
			}
		}
		return null;
	}

	public ResultMessage add(GoodsPO po) throws RemoteException {
		for(GoodsPO gpo : data){
			if(gpo.getId().equals(po.getId())){
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
			if(gpo.getId().equals(po.getId())){
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
			if(gpo.getId().equals(po.getId())){
				data.remove(gpo);
				data.add(po);
				System.out.println("update goods success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update goods failed");
		return ResultMessage.FAILED;
	}

}
