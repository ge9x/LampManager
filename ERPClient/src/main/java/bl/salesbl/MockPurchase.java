package bl.salesbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PurchaseVO;

public class MockPurchase extends Purchase{
	ArrayList<PurchaseVO> purchaseBill=new ArrayList<PurchaseVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();
	
	GoodsItemVO gi1=new GoodsItemVO( "01","霓虹灯",null, 20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO("01", "挂灯","null", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		PurchaseVO p1=new PurchaseVO(BillType.PURCHASE,BillState.PASS,"JHD-20171022-00001","供应商1"
			,"00000001","默认仓库","阿红",goodsItemList,"满足客户需求"
		     , LocalDate.now().toString());
		PurchaseVO p2=new PurchaseVO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
				,"00000002","默认仓库","阿明",goodsItemList,"好看"
				,LocalDate.now().toString());
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	public PurchaseVO findPurchaseByID(String ID) {
		for(PurchaseVO pur:purchaseBill){
			if(pur.ID.equals(ID)){
				System.out.println("Find purchase success");
				return pur;
			}
		}
		System.out.println("Find purchase failed");
		return null;
	}
	
	public ArrayList<PurchaseVO> findPurchaseByState(BillState state) {
		for(PurchaseVO pur:purchaseBill){
			if(pur.state.equals(state)){
				System.out.println("Find purchase success");
				return purchaseBill;
			}
		}
		System.out.println("Find purchase failed");
		return null;
	}
	
	public ResultMessage addPurchase(PurchaseVO po) {
		for(PurchaseVO pur:purchaseBill){
			if(pur.ID.equals(po.ID)){
				System.out.println("Add purchase failed");
				return ResultMessage.FAILED;
			}
		}
		System.out.println("Add purchase success");
		purchaseBill.add(po);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage updatePurchase(PurchaseVO po) {
		for(PurchaseVO pur:purchaseBill){
			if(pur.ID.equals(po.ID)){
				System.out.println("Update purchase success");
				purchaseBill.remove(pur);
				purchaseBill.add(po);
				return ResultMessage.SUCCESS;
	
			}
		}
		System.out.println("Update purchase failed");
		return ResultMessage.FAILED;
	}
		
	public ResultMessage deletePurchase(String ID) {
		for(PurchaseVO pur:purchaseBill){
			if(pur.ID.equals(ID)){
				purchaseBill.remove(pur);
				System.out.println("Delete purchase success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete Purchase failed");
		return ResultMessage.FAILED;
	}
	
	public void init() {
		purchaseBill.clear();
		System.out.println("Init sales and purchase success");
	}
	
	public ResultMessage submitPurchase(PurchaseVO vo){
		for(PurchaseVO pur:purchaseBill){
			if(pur.ID.equals(vo.ID)){
				pur.state=BillState.SUBMITTED;
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("not find bill");
		return ResultMessage.NOT_EXIST;
	}
}
