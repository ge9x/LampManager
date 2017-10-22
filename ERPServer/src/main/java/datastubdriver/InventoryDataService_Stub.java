package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryBillPO;
import po.GoodsPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class InventoryDataService_Stub implements InventoryDataService{
	ArrayList<InventoryBillPO> data;
	ArrayList<InventoryBillPO> alarmData;
	
	{
		data = new ArrayList<InventoryBillPO>();
		alarmData = new ArrayList<InventoryBillPO>();
		InventoryBillPO vo1 = new InventoryBillPO("BYD-20171022-00000", BillType.OVERFLOW, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo2 = new InventoryBillPO("BSD-20171022-00000", BillType.LOSS, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo3 = new InventoryBillPO("BJD-20171022-00000", BillType.ALARM, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo4 = new InventoryBillPO("ZSD-20171022-00000", BillType.GIFT, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo5 = new InventoryBillPO("JHD-20171022-00000", BillType.PURCHASE, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
		data.add(vo1);
		data.add(vo2);
		alarmData.add(vo3);
		data.add(vo4);
		data.add(vo5);
	}

	public ArrayList<InventoryBillPO> show() throws RemoteException {
		return data;
	}

	public ArrayList<InventoryBillPO> showAlarm() throws RemoteException {
		return alarmData;
	}

	public InventoryBillPO findBill(String ID) throws RemoteException {
		for(InventoryBillPO po : data){
			if(po.getID().equals(ID)){
				System.out.println("find bill success");
				return po;
			}
		}
		for(InventoryBillPO po : alarmData){
			if(po.getID().equals(ID)){
				System.out.println("find bill success");
				return po;
			}
		}
		System.out.println("find bill failed");
		return null;
	}

	public ResultMessage addBill(InventoryBillPO po) throws RemoteException {
		for(InventoryBillPO ipo : data){
			if(ipo.getID().equals(po.getID())){
				System.out.println("add bill failed");
				return ResultMessage.FAILED;
			}
		}
		for(InventoryBillPO ipo : alarmData){
			if(ipo.getID().equals(po.getID())){
				System.out.println("add bill failed");
				return ResultMessage.FAILED;
			}
		}
		data.add(po);
		System.out.println("add bill success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deleteBill(InventoryBillPO po) throws RemoteException {
		for(InventoryBillPO ipo : data){
			if(ipo.getID().equals(po.getID())){
				data.remove(ipo);
				System.out.println("delete bill success");
				return ResultMessage.SUCCESS;
			}
		}
		for(InventoryBillPO ipo : alarmData){
			if(ipo.getID().equals(po.getID())){
				data.remove(ipo);
				System.out.println("delete bill success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("delete bill failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage updateBill(InventoryBillPO po) throws RemoteException {
		for(InventoryBillPO ipo : data){
			if(ipo.getID().equals(po.getID())){
				data.remove(ipo);
				data.add(po);
				System.out.println("update bill success");
				return ResultMessage.SUCCESS;
			}
		}
		for(InventoryBillPO ipo : alarmData){
			if(ipo.getID().equals(po.getID())){
				data.remove(ipo);
				data.add(po);
				System.out.println("update bill success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update bill failed");
		return ResultMessage.FAILED;
	}

}
