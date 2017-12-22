package bl.inventorybl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsInfo;
import dataservice.inventorydataservice.InventoryDataService;
import po.GoodsPO;
import po.InventoryPO;
import rmi.InventoryRemoteHelper;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryCheckItemVO;
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
	private InventoryBill inventoryBill;
	private GoodsInfo goodsInfo;

	public Inventory() {
		inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
		goodsInfo = new GoodsController();
		inventoryBill = new InventoryBill(goodsInfo);
		inventoryList = new InventoryList();
	}

	public ArrayList<String> showInventory() throws RemoteException {
		ArrayList<InventoryPO> pos = inventoryDataService.showInventory();
		ArrayList<String> ret = new ArrayList<>();
		for (InventoryPO po : pos) {
			ret.add(po.getName());
		}
		return ret;
	}

	public InventoryViewVO show(String startDate, String endDate, String inventory) throws RemoteException {
		InventoryPO inventoryPO = inventoryDataService.findInventoryByName(inventory);
		ArrayList<InventoryBillVO> inventoryBillVOs = inventoryBill.getPassBillsByDateAndInventory(startDate, endDate,
				inventoryPO);
		return inventoryList.show(startDate, endDate, inventory, inventoryBillVOs);
	}

	public InventoryCheckVO check() {
		LocalDate date = LocalDate.now();
		ArrayList<GoodsVO> goods = goodsInfo.getAllGoods();
		HashMap<GoodsVO, Double> averagePrice = new HashMap<>();
		for (GoodsVO vo : goods) {
			averagePrice.put(vo, vo.buyingPrice);
		}
		InventoryCheckVO check = new InventoryCheckVO(date.toString(), averagePrice);
		return check;
	}

	public ResultMessage exportExcel(String filePath, String fileName, InventoryCheckVO vo) {
		fileName = fileName.split("\\.")[0];
		ArrayList<InventoryCheckItemVO> items = new ArrayList<>();
		HashMap<GoodsVO, Double> map = vo.averagePrice;
		for (GoodsVO goodsVO : map.keySet()) {
			double averagePrice = map.get(goodsVO);
			items.add(new InventoryCheckItemVO(goodsVO.ID, goodsVO.name, goodsVO.model, goodsVO.amount, averagePrice));
		}
		ExportToExcel exporter = new ExportToExcel.Builder(filePath, fileName, ExcelType.XLSX)
				.withModel(Model.of(InventoryCheckItemVO.class, items)).build();
		if (exporter.export()) {
			return ResultMessage.SUCCESS;
		}
		else {
			return ResultMessage.FAILED;
		}
	}

	public ArrayList<InventoryBillVO> showBills() throws RemoteException {
		return inventoryBill.show();
	}

	public ArrayList<InventoryBillVO> showAlarmBills() throws RemoteException {
		return inventoryBill.showAlarm();
	}

	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state) throws RemoteException {
		return inventoryBill.findByStateAndType(type, state);
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
		return inventoryBill.add(vo);
	}

	public ResultMessage deleteInventory(String inventory) throws RemoteException {
		InventoryPO found = inventoryDataService.findInventoryByName(inventory);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			return inventoryDataService.deleteInventory(found);
		}
	}

	public ResultMessage deleteBill(String ID) throws RemoteException {
		return inventoryBill.delete(ID);
	}

	public ResultMessage updateInventory(String before, String after) throws RemoteException {
		InventoryPO found = inventoryDataService.findInventoryByName(before);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			return inventoryDataService.updateInventory(found);
		}
	}

	public ResultMessage updateBill(InventoryBillVO vo) throws NumberFormatException, RemoteException {
		return inventoryBill.update(vo);
	}

	public InventoryBillVO showBillDetails(String ID) throws NumberFormatException, RemoteException {
		return inventoryBill.showDetails(ID);
	}

	/**
	 * 约定：和addBill方法逻辑相同（仅在使用情境和语义上有所区别）
	 */
	public ResultMessage submitBill(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.submit(vo);
	}

	public ArrayList<InventoryBillVO> findBillByType(BillType type) throws RemoteException {
		return inventoryBill.findByType(type);
	}

	public String getNewBillIDByType(BillType type) throws RemoteException {
		return inventoryBill.getNewIDByType(type);
	}

	public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate) throws RemoteException {
		return inventoryBill.getBillsByDate(startDate, endDate);
	}

	protected InventoryPO getInventoryByName(String name) throws RemoteException {
		return inventoryDataService.findInventoryByName(name);
	}

	public ResultMessage raiseInventory(ArrayList<GoodsItemVO> goodsItems, String inventory) throws RemoteException {
		return this.changeInventory(goodsItems, inventory, 1);
	}

	public ResultMessage reduceInventory(ArrayList<GoodsItemVO> goodsItems, String inventory) throws RemoteException {
		return this.changeInventory(goodsItems, inventory, -1);
	}

	public ResultMessage examine(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.examine(vo);
	}

	public ArrayList<InventoryBillVO> getAllSubmittedInventoryBill() throws RemoteException {
		return inventoryBill.getAllSubmittedBill();
	}

	/**
	 * 改变库存（增加或减少）
	 * 
	 * @param sign 符号（增加：1，减少：-1）
	 */
	private ResultMessage changeInventory(ArrayList<GoodsItemVO> goodsItems, String inventory, int sign)
			throws RemoteException {
		InventoryPO inventoryPO = inventoryDataService.findInventoryByName(inventory);
		System.out.println("changeInventory method is called");
		if (inventoryPO == null) {
			return ResultMessage.FAILED;
		}
		else {
			Map<GoodsPO, Integer> map = inventoryPO.getNumber();
			for (GoodsItemVO itemVO : goodsItems) {
				boolean isExistent = false;
				for (GoodsPO goods : map.keySet()) {
					if (goods.buildID().equals(itemVO.ID)) {
						int number = map.get(goods) + sign * itemVO.number;
						if (number < 0) { // 负数检查
							return ResultMessage.FAILED;
						}
						map.put(goods, number);
						break;
					}
				}
				if (!isExistent) { // 如果本来仓库里没有这种商品
					if (sign == -1) { // 负数检查
						return ResultMessage.FAILED;
					}
					GoodsPO goodsPO = goodsInfo.getGoodsByID(itemVO.ID);
					map.put(goodsPO, itemVO.number);
				}
			}
			inventoryPO.setNumber(map);
			return inventoryDataService.updateInventory(inventoryPO);
		}
	}

}
