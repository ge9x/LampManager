package bl.inventorybl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bl.goodsbl.Goods;
import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsInfo;
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

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryBill {
	private InventoryDataService inventoryDataService;
	private GoodsInfo goodsInfo;
	
	public InventoryBill(){
		inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
		goodsInfo = new GoodsController();
	}

	/**
	 * 提交单据<br>
	 * 约定：和addBill方法逻辑相同（仅在使用情境和语义上有所区别）
	 * @return 是否提交成功
	 */
    public ResultMessage submit(InventoryBillVO vo) throws RemoteException {
		return this.add(vo);
    }
    
    /**
     * 修改单据
     * @param vo 要修改的单据的VO
     * @return 是否修改成功
     */
	public ResultMessage update(InventoryBillVO vo) throws NumberFormatException, RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		int turn = Integer.parseInt(vo.ID.split("-")[2]);
		criteria.add(new Criterion("type", vo.type, QueryMode.FULL));
		criteria.add(new Criterion("date", vo.date, QueryMode.FULL));
		criteria.add(new Criterion("turn", turn, QueryMode.FULL));
		ArrayList<InventoryBillPO> found = inventoryDataService.advancedQuery(criteria);
		if (found.isEmpty()) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			if (found.size() > 1) {
				System.out.println("警告：数据库中出现重复的单据：" + vo.ID);
			}
			InventoryBillPO toUpdate = found.get(0);
			InventoryPO inventory = this.getInventoryByName(vo.inventory);
			HashMap<GoodsVO, Integer> voMap = vo.goodsMap;
			HashMap<GoodsPO, Integer> poMap = new HashMap<>();
			for (GoodsVO goodsVO : voMap.keySet()) {
				GoodsPO goodsPO = goodsInfo.getGoodsByID(goodsVO.ID);
				poMap.put(goodsPO, voMap.get(goodsVO));
			}
			toUpdate.setState(vo.state);
			toUpdate.setInventory(inventory);
			toUpdate.setGoodsMap(poMap);
			return inventoryDataService.updateBill(toUpdate);
		}
	}

	public ResultMessage add(InventoryBillVO vo) throws RemoteException {
		InventoryBillPO toAdd = this.voToPO(vo);
		return inventoryDataService.addBill(toAdd);
	}

	public ResultMessage delete(String ID) { // TODO
		return null;
	}
    
	public ArrayList<InventoryBillVO> show() throws RemoteException {
		ArrayList<InventoryBillPO> pos = inventoryDataService.show();
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.poToVO(po));
		}
		return ret;
	}

	public ArrayList<InventoryBillVO> showAlarm() throws RemoteException {
		ArrayList<InventoryBillPO> pos = inventoryDataService.showAlarm();
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.poToVO(po));
		}
		return ret;
	}

	public InventoryBillVO showDetails(String ID) throws NumberFormatException, RemoteException {
		String[] identity = ID.split("-");
		BillType type = BillType.getEnumByAcronym(identity[0]);
		String date = identity[1];
		if(date.length() != 8){
			System.out.println("Inventory.showBillDetails: 非法的日期：" + date);
			return null;
		}
		else{
			date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
		}
		int turn = Integer.parseInt(identity[2]);
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", type, QueryMode.FULL));
		criteria.add(new Criterion("date", date, QueryMode.FULL));
		criteria.add(new Criterion("turn", turn, QueryMode.FULL));
		ArrayList<InventoryBillPO> found = inventoryDataService.advancedQuery(criteria);
		if (found.isEmpty()) {
			return null;
		}
		else {
			if (found.size() > 1) {
				System.out.println("警告：数据库中出现重复的单据：" + ID);
			}
			return this.poToVO(found.get(0));
		}
	}

	public ArrayList<InventoryBillVO> findByType(BillType type) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", type, QueryMode.FULL));
		ArrayList<InventoryBillPO> pos = inventoryDataService.advancedQuery(criteria);
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.poToVO(po));
		}
		return ret;
	}

	public ArrayList<InventoryBillVO> findByStateAndType(BillType type, BillState state) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		if (type != null) {
			criteria.add(new Criterion("type", type, QueryMode.FULL));
		}
		if (state != null) {
			criteria.add(new Criterion("state", state, QueryMode.FULL));
		}
		ArrayList<InventoryBillPO> pos = inventoryDataService.advancedQuery(criteria);
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.poToVO(po));
		}
		return ret;
	}
	
    public String getNewIDByType(BillType type) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		String date = LocalDate.now().toString();
		criteria.add(new Criterion("type", type, QueryMode.FULL));
		criteria.add(new Criterion("date", date, QueryMode.FULL));
		int turn = inventoryDataService.advancedQuery(criteria).size() + 1;
		return type.getAcronym() + "-" + date.replace("-", "") + "-" + String.format("%06d", turn);
    }

    public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate) throws RemoteException{
        return null; // TODO
    }
    
	private InventoryBillVO poToVO(InventoryBillPO po) {
		String inventory = po.getInventory().getName();
		Map<GoodsPO, Integer> poMap = po.getGoodsMap();
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
	private InventoryBillPO voToPO(InventoryBillVO vo) throws RemoteException {
		InventoryPO inventory = this.getInventoryByName(vo.inventory);
		HashMap<GoodsVO, Integer> voMap = vo.goodsMap;
		HashMap<GoodsPO, Integer> poMap = new HashMap<>();
		for (GoodsVO goods : voMap.keySet()) {
			poMap.put(goodsInfo.getGoodsByID(goods.ID), voMap.get(goods));
		}
		// 查询数据库以知晓这是当天该种类型单据的第几张
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", vo.type, QueryMode.FULL));
		criteria.add(new Criterion("date", vo.date, QueryMode.FULL));
		int turn = inventoryDataService.advancedQuery(criteria).size() + 1;

		InventoryBillPO ret = new InventoryBillPO(vo.date, vo.type, vo.state, inventory, vo.user, poMap, turn);
		return ret;
	}

	private InventoryPO getInventoryByName(String name) throws RemoteException {
		return inventoryDataService.findInventoryByName(name);
	}
}
