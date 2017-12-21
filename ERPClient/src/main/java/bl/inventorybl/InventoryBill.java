package bl.inventorybl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bl.goodsbl.Goods;
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
 * 
 * @author 巽
 *
 */
public class InventoryBill {
	private InventoryDataService inventoryDataService;
	private GoodsInfo goodsInfo;

	public InventoryBill(GoodsInfo goodsInfo) {
		inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
		this.goodsInfo = goodsInfo;
	}

	/**
	 * 提交单据<br>
	 * 约定：和addBill方法逻辑相同（仅在使用情境和语义上有所区别）
	 * 
	 * @return 是否提交成功
	 */
	public ResultMessage submit(InventoryBillVO vo) throws RemoteException {
		return this.add(vo);
	}

	public ResultMessage add(InventoryBillVO vo) throws RemoteException {
		InventoryBillPO toAdd = this.voToPO(vo);
		return inventoryDataService.addBill(toAdd);
	}

	public ResultMessage delete(String ID) throws RemoteException {
		InventoryBillPO toDelete = this.getBillByID(ID);
		if (toDelete == null) {
			return ResultMessage.NOT_EXIST;
		}
		else if (toDelete.getState() == BillState.DRAFT) { // 只能删除草稿单据
			return inventoryDataService.deleteBill(toDelete);
		}
		else {
			return ResultMessage.FAILED;
		}
	}

	/**
	 * 修改单据
	 * 
	 * @param vo 要修改的单据的VO
	 * @return 是否修改成功
	 */
	public ResultMessage update(InventoryBillVO vo) throws NumberFormatException, RemoteException {
		InventoryBillPO toUpdate = this.getBillByID(vo.ID);
		if (toUpdate == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
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
		InventoryBillPO po = this.getBillByID(ID);
		return this.poToVO(po);
	}

	public ArrayList<InventoryBillVO> findByType(BillType type) throws RemoteException {
		return this.findByStateAndType(type, null);
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

	public ArrayList<InventoryBillVO> getBillsByDate(String startDate, String endDate) throws RemoteException {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		do { // TODO Optimize
			ArrayList<Criterion> criteria = new ArrayList<>();
			criteria.add(new Criterion("date", start.toString(), QueryMode.FULL));
			ArrayList<InventoryBillPO> found = inventoryDataService.advancedQuery(criteria);
			ArrayList<InventoryBillVO> vos = new ArrayList<>();
			for (InventoryBillPO po : found) {
				vos.add(this.poToVO(po));
			}
			ret.addAll(vos);
			start = start.plusDays(1);
		} while (!start.isAfter(end));
		return ret;
	}

	/**
	 * 通过日期和仓库得到已通过(PASS)的库存报溢单和库存报损单
	 * 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param inventory 仓库名
	 * @return 查找到的单据VO的集合
	 * @throws RemoteException
	 */
	public ArrayList<InventoryBillVO> getPassBillsByDateAndInventory(String startDate, String endDate,
			InventoryPO inventory) throws RemoteException {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		do { // TODO Optimize
			ArrayList<Criterion> criteria = new ArrayList<>();
			criteria.add(new Criterion("date", start.toString(), QueryMode.FULL));
			criteria.add(new Criterion("inventory", inventory, QueryMode.FULL));
			criteria.add(new Criterion("state", BillState.PASS, QueryMode.FULL));
			criteria.add(new Criterion(new Criterion("type", BillType.OVERFLOW, QueryMode.FULL),
					new Criterion("type", BillType.LOSS, QueryMode.FULL)));
			ArrayList<InventoryBillPO> found = inventoryDataService.advancedQuery(criteria);
			ArrayList<InventoryBillVO> vos = new ArrayList<>();
			for (InventoryBillPO po : found) {
				vos.add(this.poToVO(po));
			}
			ret.addAll(vos);
			start = start.plusDays(1);
		} while (!start.isAfter(end));
		return ret;
	}

	public ResultMessage examine(InventoryBillVO vo) throws RemoteException {
		ResultMessage ret = this.update(vo);
		if (vo.state == BillState.PASS) {
			HashMap<GoodsVO, Integer> goodsMap = vo.goodsMap;
			if (vo.type == BillType.OVERFLOW) {
				this.changeInventory(goodsMap, vo.inventory, 1);
			}
			else { // BillType = LOSS
				this.changeInventory(goodsMap, vo.inventory, -1);
			}
		}
		return ret;
	}

	public ArrayList<InventoryBillVO> getAllSubmittedBill() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("state", BillState.SUBMITTED, QueryMode.FULL));
		ArrayList<InventoryBillPO> pos = inventoryDataService.advancedQuery(criteria);
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		for (InventoryBillPO po : pos) {
			ret.add(this.poToVO(po));
		}
		return ret;
	}

	private InventoryBillVO poToVO(InventoryBillPO po) {
		if (po == null) {
			return null;
		}
		else {
			String inventory = po.getInventory().getName();
			Map<GoodsPO, Integer> poMap = po.getGoodsMap();
			HashMap<GoodsVO, Integer> voMap = new HashMap<>();
			for (GoodsPO goods : poMap.keySet()) {
				voMap.put(Goods.poToVO(goods), poMap.get(goods));
			}
			InventoryBillVO ret = new InventoryBillVO(po.buildID(), po.getType(), po.getState(), po.getDate(),
					inventory, po.getUser(), voMap);
			return ret;
		}
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

	/**
	 * 根据String类型的单据ID得到对应的库存类单据
	 * 
	 * @param ID 单据ID（如"BYD-20171214-00001"）
	 * @return ID对应的唯一的库存类单据PO
	 * @throws RemoteException
	 */
	private InventoryBillPO getBillByID(String ID) throws RemoteException {
		String[] identity = ID.split("-");
		BillType type = BillType.getEnumByAcronym(identity[0]);
		String date = identity[1];
		if (date.length() != 8) {
			System.out.println("Inventory.showBillDetails: 非法的日期：" + date);
			return null;
		}
		else {
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
			return found.get(0);
		}
	}

	private ResultMessage changeInventory(HashMap<GoodsVO, Integer> goodsMap, String inventory, int sign)
			throws RemoteException {
		InventoryPO inventoryPO = inventoryDataService.findInventoryByName(inventory);
		if (inventoryPO == null) {
			return ResultMessage.FAILED;
		}
		else {
			Map<GoodsPO, Integer> map = inventoryPO.getNumber();
			for (GoodsVO vo : goodsMap.keySet()) {
				boolean isExistent = false;
				for (GoodsPO po : map.keySet()) {
					if (vo.ID.equals(po.buildID())) {
						map.put(po, map.get(po) + sign * goodsMap.get(vo));
						isExistent = true;
						break;
					}
				}
				if (!isExistent) { // 如果本来仓库里没有这种商品
					GoodsPO goodsPO = goodsInfo.getGoodsByID(vo.ID);
					map.put(goodsPO, goodsMap.get(vo));
				}
			}
			inventoryPO.setNumber(map);
			return inventoryDataService.updateInventory(inventoryPO);
		}
	}
}
