package bl.inventorybl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import bl.goodsbl.Goods;
import bl.goodsbl.GoodsController;
import bl.salesbl.SalesController;
import blservice.goodsblservice.GoodsInfo;
import blservice.salesblservice.SalesInfo;
import dataservice.inventorydataservice.InventoryDataService;
import po.GoodsPO;
import po.InventoryBillPO;
import po.InventoryPO;
import rmi.InventoryRemoteHelper;
import util.BillState;
import util.BillType;
import util.Criterion;
import util.QueryMode;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class Inventory {
	private InventoryDataService inventoryDataService;
	private InventoryList inventoryList;
	private ArrayList<InventoryBill> inventoryBill;
	private SalesInfo salesInfo;
	private GoodsInfo goodsInfo;

	public Inventory() {
		inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
//		salesInfo = new SalesController();
		goodsInfo = new GoodsController();
	}

	public ArrayList<String> showInventory() throws RemoteException {
		ArrayList<InventoryPO> pos = inventoryDataService.showInventory();
		ArrayList<String> ret = new ArrayList<>();
		for (InventoryPO po : pos) {
			ret.add(po.getName());
		}
		return ret;
	}

	public InventoryViewVO show(Date startDate, Date endDate, String inventory) {
		return null;
	}

	public InventoryCheckVO check(Date today) {
		return null;
	}

	public ResultMessage exportExcel(InventoryCheckVO vo) {
		return null;
	}

	public ArrayList<InventoryBillVO> showBills() throws RemoteException {
		ArrayList<InventoryBillPO> pos = inventoryDataService.show();
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.billToVO(po));
		}
		return ret;
	}

	public ArrayList<InventoryBillVO> showAlarmBills() throws RemoteException {
		ArrayList<InventoryBillPO> pos = inventoryDataService.showAlarm();
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.billToVO(po));
		}
		return ret;
	}

	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", type, QueryMode.FULL));
		criteria.add(new Criterion("state", state, QueryMode.FULL));
		ArrayList<InventoryBillPO> pos = inventoryDataService.advancedQuery(criteria);
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for(InventoryBillPO po : pos){
			ret.add(this.billToVO(po));
		}
		return ret;
	}

	public ResultMessage addInventory(String inventory) throws RemoteException {
		ArrayList<InventoryPO> pos = inventoryDataService.showInventory();
		for (InventoryPO po : pos) {
			if (po.getName().equals(inventory)) {
				return ResultMessage.EXIST;
			}
		}
		InventoryPO toAdd = new InventoryPO(inventory);
		return inventoryDataService.addInventroy(toAdd);
	}

	public ResultMessage addBill(InventoryBillVO vo) throws RemoteException {
		InventoryBillPO toAdd = this.billToPO(vo);
		return inventoryDataService.addBill(toAdd);
	}

	public ResultMessage deleteInventory(String inventory) {
		return null;
	}

	public ResultMessage deleteBill(String ID) {
		return null;
	}

	public ResultMessage updateInventory(String before, String after) {
		return null;
	}

	public ResultMessage updateBill(InventoryBillVO vo) throws NumberFormatException, RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		int turn = Integer.parseInt(vo.ID.split("-")[2]);
		criteria.add(new Criterion("type", vo.type, QueryMode.FULL));
		criteria.add(new Criterion("date", vo.date, QueryMode.FULL));
		criteria.add(new Criterion("turn", turn, QueryMode.FULL));
		ArrayList<InventoryBillPO> found = inventoryDataService.advancedQuery(criteria);
		if(found.isEmpty()){
			return ResultMessage.NOT_EXIST;
		}
		else{
			if(found.size()>1){
				System.out.println("警告：数据库中出现重复的单据：" + vo.ID);
			}
			InventoryBillPO toUpdate = found.get(0);
			InventoryPO inventory = this.getInventoryByName(vo.inventory);
			HashMap<GoodsVO, Integer> voMap = vo.goodsMap;
			HashMap<GoodsPO, Integer> poMap = new HashMap<>();
			for(GoodsVO goodsVO : voMap.keySet()){
				GoodsPO goodsPO = goodsInfo.getGoodsByID(goodsVO.ID);
				poMap.put(goodsPO, voMap.get(goodsVO));
			}
			toUpdate.setState(vo.state);
			toUpdate.setInventory(inventory);
			toUpdate.setGoodsMap(poMap);
			return inventoryDataService.updateBill(toUpdate);
		}
	}

	public InventoryBillVO showBillDetails(String ID) {
		return null;
	}

	public ResultMessage submitBill(InventoryBillVO vo) throws RemoteException {
		return this.addBill(vo);
	}

	private InventoryBillVO billToVO(InventoryBillPO po) {
		String inventory = po.getInventory().getName();
		HashMap<GoodsPO, Integer> poMap = (HashMap<GoodsPO, Integer>) po.getGoodsMap();
		HashMap<GoodsVO, Integer> voMap = new HashMap<>();
		for (GoodsPO goods : poMap.keySet()) {
			voMap.put(Goods.poToVO(goods), poMap.get(goods));
		}
		InventoryBillVO ret = new InventoryBillVO(po.buildID(), po.getType(), po.getState(), po.getDate(), inventory,
				po.getUser(), voMap);
		return ret;
	}

	/**
	 * 仅限增加单据时调用
	 */
	private InventoryBillPO billToPO(InventoryBillVO vo) throws RemoteException {
		InventoryPO inventory = this.getInventoryByName(vo.inventory);
		HashMap<GoodsPO, Integer> poMap = new HashMap<>();
		for (GoodsVO goods : vo.goodsMap.keySet()) {
			poMap.put(goodsInfo.getGoodsByID(goods.ID), poMap.get(goods));
		}
		// 查询数据库以知晓这是当天该种类型单据的第几张
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", vo.type, QueryMode.FULL));
		criteria.add(new Criterion("date", vo.date, QueryMode.FULL));
		int turn = inventoryDataService.advancedQuery(criteria).size() + 1;

		InventoryBillPO ret = new InventoryBillPO(vo.date, vo.type, vo.state, inventory, vo.user, poMap, turn);
		return ret;
	}
	
	protected InventoryPO getInventoryByName(String name) throws RemoteException {
		ArrayList<InventoryPO> all = inventoryDataService.showInventory();
		for(InventoryPO po : all){
			if(po.getName().equals(name)){
				return po;
			}
		}
		return null;
	}
}
