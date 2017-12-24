package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryBillPO;
import po.InventoryPO;
import po.GoodsPO;
import util.BillState;
import util.BillType;
import util.Criterion;
import util.ResultMessage;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
@SuppressWarnings("deprecation")
public class InventoryDataService_Stub implements InventoryDataService{
	ArrayList<InventoryBillPO> data;
	ArrayList<InventoryBillPO> alarmData;
	ArrayList<InventoryPO> inventory;

	{
		data = new ArrayList<InventoryBillPO>();
		alarmData = new ArrayList<InventoryBillPO>();
		GoodsPO g1 = new GoodsPO(1, "圣洁牌纯黑落地灯", "L", null, 250, 233.3, 250, 233.3, 250);
		GoodsPO g2 = new GoodsPO(2, "圣洁牌纯白落地灯", "M", null, 250, 233.3, 250, 233.3, 250);
		GoodsPO g3 = new GoodsPO(3, "圣洁牌简洁黑白配台灯", "S", null, 2500, 233.3, 250, 233.3, 250);
		GoodsPO g4 = new GoodsPO(4, "圣洁牌古典吊灯", "LL", null, 250, 2333.3, 2500, 2333.3, 2500);
		GoodsPO g5 = new GoodsPO(5, "圣洁牌后现代主义七彩霓虹灯", "LLL", null, 3, 23333.3, 250000, 23333.3, 250000);
	    InventoryPO inpo1 = new InventoryPO(1, "栖霞区仓库");
	    InventoryPO inpo2 = new InventoryPO(2, "鼓楼区仓库");
		InventoryBillPO vo1 = new InventoryBillPO(1, "2017/11/30", BillType.OVERFLOW, BillState.PASS, inpo1, "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo2 = new InventoryBillPO(1, "2017/11/30", BillType.LOSS, BillState.PASS, inpo1, "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo3 = new InventoryBillPO(1, "2017/11/30", BillType.ALARM, BillState.PASS, inpo1, "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo4 = new InventoryBillPO(1, "2017/11/30", BillType.GIFT, BillState.PASS, inpo1, "王二小", new HashMap<GoodsPO, Integer>());
		InventoryBillPO vo5 = new InventoryBillPO(1, "2017/11/30", BillType.PURCHASE, BillState.PASS, inpo1, "王二小", new HashMap<GoodsPO, Integer>());
		vo1.getGoodsMap().put(g1, 200);
		vo2.getGoodsMap().put(g2, 300);
		vo3.getGoodsMap().put(g3, 400);
		vo4.getGoodsMap().put(g4, 500);
		vo5.getGoodsMap().put(g5, 600);
		data.add(vo1);
		data.add(vo2);
		alarmData.add(vo3);
		data.add(vo4);
		data.add(vo5);
		inventory.add(inpo1);
		inventory.add(inpo2);
	}
	
	public ArrayList<InventoryPO> showInventory() throws RemoteException {
		return inventory;
	}

	public ResultMessage addInventroy(InventoryPO inventory) throws RemoteException {
		for(InventoryPO s : this.inventory){
			if(s.equals(inventory)){
				System.out.println("add inventory failed");
				return ResultMessage.FAILED;
			}
		}
		this.inventory.add(inventory);
		System.out.println("add inventory success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deleteInventory(InventoryPO inventory) throws RemoteException {
		for(InventoryPO s : this.inventory){
			if(s.equals(inventory)){
				this.inventory.remove(s);
				System.out.println("delete inventory success");
				return ResultMessage.SUCCESS;
			}
		}
		this.inventory.add(inventory);
		System.out.println("delete inventory failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage updateInventory(InventoryPO po) throws RemoteException {
		for(InventoryPO s : inventory){
			if(s.getID() == po.getID()){
				this.inventory.remove(s);
				this.inventory.add(po);
				System.out.println("update inventory success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update inventory failed");
		return ResultMessage.FAILED;
	}

	public ArrayList<InventoryBillPO> show() throws RemoteException {
		return data;
	}

	public InventoryBillPO findBill(int ID) throws RemoteException {
		for(InventoryBillPO po : data){
			if(po.getID() == ID){
				System.out.println("find bill success");
				return po;
			}
		}
		for(InventoryBillPO po : alarmData){
			if(po.getID() == ID){
				System.out.println("find bill success");
				return po;
			}
		}
		System.out.println("find bill failed");
		return null;
	}

	public ResultMessage addBill(InventoryBillPO po) throws RemoteException {
		for(InventoryBillPO ipo : data){
			if(ipo.getID() == po.getID()){
				System.out.println("add bill failed");
				return ResultMessage.FAILED;
			}
		}
		for(InventoryBillPO ipo : alarmData){
			if(ipo.getID() == po.getID()){
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
			if(ipo.getID() == po.getID()){
				data.remove(ipo);
				System.out.println("delete bill success");
				return ResultMessage.SUCCESS;
			}
		}
		for(InventoryBillPO ipo : alarmData){
			if(ipo.getID() == po.getID()){
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
			if(ipo.getID() == po.getID()){
				data.remove(ipo);
				data.add(po);
				System.out.println("update bill success");
				return ResultMessage.SUCCESS;
			}
		}
		for(InventoryBillPO ipo : alarmData){
			if(ipo.getID() == po.getID()){
				data.remove(ipo);
				data.add(po);
				System.out.println("update bill success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update bill failed");
		return ResultMessage.FAILED;
	}

	/**
	 * 后加入接口，桩不予实现
	 */
	@Override
	public ArrayList<InventoryBillPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return null;
	}

	@Override
	public InventoryPO findInventoryByName(String name) throws RemoteException {
		for(InventoryPO po : inventory){
			if(po.getName().equals(name)){
				return po;
			}
		}
		return null;
	}

}
