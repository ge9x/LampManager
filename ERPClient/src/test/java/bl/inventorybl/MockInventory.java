package bl.inventorybl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import util.BillState;
import util.BillType;
import util.InventoryListItemType;
import util.ResultMessage;
import vo.AlarmVO;
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewItemVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
@SuppressWarnings("deprecation")
public class MockInventory extends Inventory {

	@Override
	public ArrayList<String> showInventory() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("栖霞区仓库");
		return ret;
	}

	@Override
	public InventoryViewVO show(String startDate, String endDate, String inventory) {
		GoodsVO goodsVO = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, "栖霞区仓库", 250, 25, 250, 2500, 250, 2500);
		InventoryViewItemVO itemVO = new InventoryViewItemVO("2017-12-21", goodsVO, InventoryListItemType.IN, 250, 2500);
		ArrayList<InventoryViewItemVO> item = new ArrayList<InventoryViewItemVO>();
		item.add(itemVO);
		HashMap<GoodsVO, Double> total = new HashMap<GoodsVO, Double>();
		total.put(goodsVO, 2500.0);
		InventoryViewVO ret = new InventoryViewVO(LocalDate.now().toString(), LocalDate.now().toString(), "栖霞区仓库", item, total);
		return ret;
	}

	@Override
	public InventoryCheckVO check() {
		GoodsVO goodsVO = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, "栖霞区仓库", 250, 25, 250, 2500, 250, 2500);
		HashMap<GoodsVO, Double> averagePrice = new HashMap<GoodsVO, Double>();
		averagePrice.put(goodsVO, 250.0);
		InventoryCheckVO ret = new InventoryCheckVO(LocalDate.now().toString(), averagePrice);
		return ret;
	}

	@Override
	public ResultMessage exportExcel(String filePath, String fileName, InventoryCheckVO vo) {
		if(vo==null){
			return null;
		}
		else{
			System.out.println("Export Excel succeed");
			return ResultMessage.SUCCESS;
		}
	}

	@Override
	public ArrayList<InventoryBillVO> showBills() {
		HashMap<GoodsVO, Integer> goodsMap = new HashMap<GoodsVO, Integer>();
		GoodsVO goodsVO = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, "栖霞区仓库", 250, 25, 250, 2500, 250, 2500);
		goodsMap.put(goodsVO, 25);
		InventoryBillVO billVO = new InventoryBillVO("BYD-20171107-00001", BillType.OVERFLOW, BillState.PASS, LocalDate.now().toString() , "栖霞区仓库", "乐圣洁", goodsMap);
		ArrayList<InventoryBillVO> ret = new ArrayList<InventoryBillVO>();
		ret.add(billVO);
		return ret;
	}

	@Override
	public ArrayList<AlarmVO> getAlarmByInventory(String inventory) {
		ArrayList<AlarmVO> ret = new ArrayList<>();
		ret.add(new AlarmVO("0100001", "圣洁牌经典黑白配台灯", "L", 21, 25, 9));
		return ret;
	}

	@Override
	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state) {
		if(type==BillType.ALARM && state==BillState.DRAFT){
			HashMap<GoodsVO, Integer> goodsMap = new HashMap<GoodsVO, Integer>();
			GoodsVO goodsVO = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, "栖霞区仓库", 250, 25, 250, 2500, 250, 2500);
			goodsMap.put(goodsVO, 25);
			InventoryBillVO billVO = new InventoryBillVO("BJD-20171107-00001", BillType.ALARM, BillState.PASS,LocalDate.now().toString(), "栖霞区仓库", "乐圣洁", goodsMap);
			ArrayList<InventoryBillVO> ret = new ArrayList<InventoryBillVO>();
			ret.add(billVO);
			return ret;
		}
		else{
			return new ArrayList<InventoryBillVO>();
		}
	}

	@Override
	public ResultMessage addInventory(String inventory) {
		if(inventory.equals("栖霞区仓库")){
			return ResultMessage.EXIST;
		}
		else{
			System.out.println("Add account succeed");
			return ResultMessage.SUCCESS;
		}
	}

	@Override
	public ResultMessage addBill(InventoryBillVO vo) {
		if(vo.ID.equals("BYD-20171107-00001")){
			return ResultMessage.EXIST;
		}
		else{
			System.out.println("Add account bill succeed");
			return ResultMessage.SUCCESS;
		}
	}

	@Override
	public ResultMessage deleteInventory(String inventory) {
		if(inventory.equals("栖霞区仓库")){
			System.out.println("Delete account succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public ResultMessage deleteBill(String ID) {
		if(ID.equals("BYD-20171107-00001")){
			System.out.println("Add account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public ResultMessage updateInventory(String before, String after) {
		if(before.equals("栖霞区仓库")){
			System.out.println("Update account succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public ResultMessage updateBill(InventoryBillVO vo) {
		if(vo.ID.equals("BYD-20171107-00001")){
			System.out.println("Update account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public InventoryBillVO showBillDetails(String ID) {
		if(ID.equals("BYD-20171107-00001")){
			HashMap<GoodsVO, Integer> goodsMap = new HashMap<GoodsVO, Integer>();
			GoodsVO goodsVO = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, "栖霞区仓库", 250, 25, 250, 2500, 250, 2500);
			goodsMap.put(goodsVO, 25);
			InventoryBillVO billVO = new InventoryBillVO("BJD-20171107-00001", BillType.ALARM, BillState.PASS, LocalDate.now().toString() , "栖霞区仓库", "乐圣洁", goodsMap);
			return billVO;
		}
		else{
			return null;
		}
	}

	@Override
	public ResultMessage submitBill(InventoryBillVO vo) {
		if(vo.ID.equals("BYD-20171107-00001")){
			System.out.println("Submit account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.FAILED;
		}
	}

}
