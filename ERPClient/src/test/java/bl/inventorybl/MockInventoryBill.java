package bl.inventorybl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import blservice.goodsblservice.GoodsInfo;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryBillVO;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
@SuppressWarnings("deprecation")
public class MockInventoryBill extends InventoryBill {

	public MockInventoryBill() {
		super();
	}

	@Override
	public ResultMessage submit(InventoryBillVO vo) {
		if(vo.ID.equals("BYD-20171107-00001")){
			System.out.println("submit account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.FAILED;
		}
	}

	@Override
	public ResultMessage update(InventoryBillVO vo) {
		if(vo.ID.equals("BYD-20171107-00001")){
			System.out.println("Update account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage add(InventoryBillVO vo) throws RemoteException {
		if(vo.ID.equals("BYD-20171107-00001")){
			System.out.println("add account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.FAILED;
		}
	}
	
	public ResultMessage delete(String ID) {
		if(ID.equals("BYD-20171107-00001")){
			System.out.println("delete account bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.FAILED;
		}
	}
    
	public ArrayList<InventoryBillVO> show() throws RemoteException {
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
		GoodsVO goodsVO = new GoodsVO("03000001", "圣洁牌经典黑白配台灯", "SJPJDHBP201712", "台灯", "栖霞区仓库", 200, 20, 250, 250.1, 250, 250.1);
		goodsMap.put(goodsVO, 25);
		InventoryBillVO vo = new InventoryBillVO("BYD-20171214-00001", BillType.OVERFLOW, BillState.DRAFT, "2017-12-14", "栖霞区仓库", "Xun", goodsMap);
		ret.add(vo);
		return ret;
	}

	public ArrayList<InventoryBillVO> showAlarm() throws RemoteException {
		ArrayList<InventoryBillVO> ret = new ArrayList<>();
		HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
		GoodsVO goodsVO = new GoodsVO("03000001", "圣洁牌经典黑白配台灯", "SJPJDHBP201712", "台灯", "栖霞区仓库", 200, 20, 250, 250.1, 250, 250.1);
		goodsMap.put(goodsVO, 25);
		InventoryBillVO vo = new InventoryBillVO("BJD-20171214-00001", BillType.ALARM, BillState.PASS, "2017-12-14", "栖霞区仓库", "Xun", goodsMap);
		ret.add(vo);
		return ret;
	}

	public InventoryBillVO showDetails(String ID) throws NumberFormatException, RemoteException {
		if(ID.equals("BYD-20171214-00001")){
			HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
			GoodsVO goodsVO = new GoodsVO("03000001", "圣洁牌经典黑白配台灯", "SJPJDHBP201712", "台灯", "栖霞区仓库", 200, 20, 250, 250.1, 250, 250.1);
			goodsMap.put(goodsVO, 25);
			InventoryBillVO ret = new InventoryBillVO("BYD-20171214-00001", BillType.OVERFLOW, BillState.DRAFT, "2017-12-14", "栖霞区仓库", "Xun", goodsMap);
			return ret;
		}
		else{
			return null;
		}
	}

	public ArrayList<InventoryBillVO> findByType(BillType type) throws RemoteException {
		if(type==BillType.OVERFLOW){
			ArrayList<InventoryBillVO> ret = new ArrayList<>();
			HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
			GoodsVO goodsVO = new GoodsVO("03000001", "圣洁牌经典黑白配台灯", "SJPJDHBP201712", "台灯", "栖霞区仓库", 200, 20, 250, 250.1, 250, 250.1);
			goodsMap.put(goodsVO, 25);
			InventoryBillVO vo = new InventoryBillVO("BYD-20171214-00001", BillType.OVERFLOW, BillState.DRAFT, "2017-12-14", "栖霞区仓库", "Xun", goodsMap);
			ret.add(vo);
			return ret;
		}
		else{
			return new ArrayList<>();
		}
	}

	public ArrayList<InventoryBillVO> findByStateAndType(BillType type, BillState state) throws RemoteException {
		if(type==BillType.OVERFLOW && state==BillState.DRAFT){
			ArrayList<InventoryBillVO> ret = new ArrayList<>();
			HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
			GoodsVO goodsVO = new GoodsVO("03000001", "圣洁牌经典黑白配台灯", "SJPJDHBP201712", "台灯", "栖霞区仓库", 200, 20, 250, 250.1, 250, 250.1);
			goodsMap.put(goodsVO, 25);
			InventoryBillVO vo = new InventoryBillVO("BYD-20171214-00001", BillType.OVERFLOW, BillState.DRAFT, "2017-12-14", "栖霞区仓库", "Xun", goodsMap);
			ret.add(vo);
			return ret;
		}
		else{
			return new ArrayList<>();
		}
	}
	
    public String getNewIDByType(BillType type) throws RemoteException {
		return "BYD-20171214-00001";
    }

	public ArrayList<InventoryBillVO> getBillsByDate(String startDate, String endDate) throws RemoteException{
    	if(startDate.equals("2017-12-14") || endDate.equals("2017-12-14")){
			ArrayList<InventoryBillVO> ret = new ArrayList<>();
			HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
			GoodsVO goodsVO = new GoodsVO("03000001", "圣洁牌经典黑白配台灯", "SJPJDHBP201712", "台灯", "栖霞区仓库", 200, 20, 250, 250.1, 250, 250.1);
			goodsMap.put(goodsVO, 25);
			InventoryBillVO vo = new InventoryBillVO("BYD-20171214-00001", BillType.OVERFLOW, BillState.DRAFT, "2017-12-14", "栖霞区仓库", "Xun", goodsMap);
			ret.add(vo);
			return ret;
		}
		else{
			return new ArrayList<>();
		}
    }
	
}
