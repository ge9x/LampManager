package blstubdriver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import blservice.inventoryblservice.InventoryBLService;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;
import vo.InventoryViewItemVO;
import vo.GoodsVO;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class InventoryBLService_Stub implements InventoryBLService{
	ArrayList<InventoryBillVO> data;
	ArrayList<InventoryBillVO> newData = new ArrayList<>();
	ArrayList<InventoryBillVO> alarmData;
	ArrayList<String> inventoryName = new ArrayList<>();
	
	{
		data = new ArrayList<InventoryBillVO>();
		alarmData = new ArrayList<InventoryBillVO>();
		InventoryBillVO vo1 = new InventoryBillVO("BYD-20171022-00000", BillType.OVERFLOW, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsVO, Integer>());
		InventoryBillVO vo2 = new InventoryBillVO("BSD-20171022-00000", BillType.LOSS, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsVO, Integer>());
		InventoryBillVO vo3 = new InventoryBillVO("BJD-20171022-00000", BillType.ALARM, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsVO, Integer>());
		InventoryBillVO vo4 = new InventoryBillVO("ZSD-20171022-00000", BillType.GIFT, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsVO, Integer>());
        InventoryBillVO vo5 = new InventoryBillVO("JHD-20171022-00000", BillType.PURCHASE, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsVO, Integer>());
        newData.add(vo1);
        newData.add(vo1);
        newData.add(vo1);
        newData.add(vo2);
        newData.add(vo3);
        data.add(vo1);
		data.add(vo2);
		alarmData.add(vo3);
		data.add(vo4);
		data.add(vo5);
		inventoryName.add("栖霞区仓库");
		inventoryName.add("鼓楼区仓库");
	}

	public ArrayList<String> showInventory() {
		return inventoryName;
	}

	public ResultMessage addInventory(String inventory) {
		for(String s : inventoryName){
			if(s.equals(inventory)){
				System.out.println("add inventory failed");
				return ResultMessage.FAILED;
			}
		}
		inventoryName.add(inventory);
		System.out.println("add inventory success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deleteInventory(String inventory) {
		for(String s : inventoryName){
			if(s.equals(inventory)){
				inventoryName.remove(s);
				System.out.println("delete inventory success");
				return ResultMessage.SUCCESS;
			}
		}
		inventoryName.add(inventory);
		System.out.println("delete inventory failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage updateInventory(String before, String after) {
		for(String s : inventoryName){
			if(s.equals(before)){
				inventoryName.remove(s);
				inventoryName.add(after);
				System.out.println("update inventory success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update inventory failed");
		return ResultMessage.FAILED;
	}

	public InventoryViewVO show(String startDate, String endDate, String inventory) {
		if(startDate==null || endDate==null || inventory==null){
			return null;
		}
		else{
            InventoryViewVO ret = new InventoryViewVO(new Date(), new Date(), "栖霞区仓库", new ArrayList<InventoryViewItemVO>(), new HashMap<GoodsVO, Double>());
            System.out.println("show succeed");
            return ret;
		}
	}

	public InventoryCheckVO check() {
        InventoryCheckVO ret = new InventoryCheckVO(new Date(), new HashMap<GoodsVO, Double>());
		return ret;
	}

	public ResultMessage exportExcel(InventoryCheckVO vo) {
		if(vo==null){
			System.out.println("export Excel failed");
			return ResultMessage.FAILED;
		}
		else{
			System.out.println("export Excel success");
			return ResultMessage.SUCCESS;
		}
	}

	public ArrayList<InventoryBillVO> showBills() {
		return data;
	}

	public ArrayList<InventoryBillVO> showAlarmBills() {
		return alarmData;
	}

	public ArrayList<InventoryBillVO> findBill(Date startDate, Date endDate, String inventory, String ID,
			String keyword) {
		ArrayList<InventoryBillVO> ret = new ArrayList<InventoryBillVO>();
		for(InventoryBillVO vo : data){
			if(vo.ID.equals(ID)){
				ret.add(vo);
			}
			else {
				for(GoodsVO gvo : vo.goodsMap.keySet()){
					if(gvo.name.contains(keyword)){
						ret.add(vo);
					}
					break;
				}
			}
		}
		for(InventoryBillVO vo : alarmData){
			if(vo.ID.equals(ID)){
				ret.add(vo);
			}
			else {
				for(GoodsVO gvo : vo.goodsMap.keySet()){
					if(gvo.name.contains(keyword)){
						ret.add(vo);
					}
					break;
				}
			}
		}
		return ret;
	}

	public ResultMessage addBill(InventoryBillVO vo) {
		if(vo.type==BillType.ALARM){
			for(InventoryBillVO ivo : alarmData){
				if(ivo.ID.equals(vo.ID)){
					System.out.println("add bill failed");
					return ResultMessage.FAILED;
				}
			}
			alarmData.add(vo);
		}
		else{
			for(InventoryBillVO ivo : data){
				if(ivo.ID.equals(vo.ID)){
					System.out.println("add bill failed");
					return ResultMessage.FAILED;
				}
			}
			data.add(vo);
		}
		System.out.println("add bill success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deleteBill(String ID) {
		for(InventoryBillVO ivo : alarmData){
			if(ivo.ID.equals(ID)){
				alarmData.remove(ivo);
				System.out.println("delete bill success");
				return ResultMessage.SUCCESS;
			}
		}
		for(InventoryBillVO ivo : data){
			if(ivo.ID.equals(ID)){
				data.remove(ivo);
				System.out.println("delete bill success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("delete bill failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage updateBill(InventoryBillVO vo) {
		if(vo.type==BillType.ALARM){
			for(InventoryBillVO ivo : alarmData){
				if(ivo.ID.equals(vo.ID)){
					alarmData.remove(ivo);
					alarmData.add(vo);
					System.out.println("update bill success");
					return ResultMessage.SUCCESS;
				}
			}
		}
		else{
			for(InventoryBillVO ivo : data){
				if(ivo.ID.equals(vo.ID)){
					data.remove(ivo);
					data.add(vo);
					System.out.println("update bill success");
					return ResultMessage.SUCCESS;
				}
			}
		}
		System.out.println("update bill failed");
		return ResultMessage.FAILED;
	}

	public InventoryBillVO showBillDetails(String ID) {
		for(InventoryBillVO ivo : alarmData){
			if(ivo.ID.equals(ID)){
				return ivo;
			}
		}
		for(InventoryBillVO ivo : data){
			if(ivo.ID.equals(ID)){
				return ivo;
			}
		}
		return null;
	}

	public ResultMessage submitBill(String ID) {
		for(InventoryBillVO ivo : alarmData){
			if(ivo.ID.equals(ID)){
				System.out.println("submit bill success");
				return ResultMessage.SUCCESS;
			}
		}
		for(InventoryBillVO ivo : data){
			if(ivo.ID.equals(ID)){
				System.out.println("submit bill success");
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

    @Override
    public ArrayList<InventoryBillVO> findBillByType(BillType type) {
        return newData;
    }
}
