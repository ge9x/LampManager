package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
import vo.GoodsItemVO;
import vo.PurchaseVO;

public class MockPurchaseList extends PurchaseList{
	ArrayList<PurchaseVO> purchaseBill=new ArrayList<PurchaseVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();
	
	GoodsItemVO gi1=new GoodsItemVO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO( "挂灯", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		PurchaseVO p1=new PurchaseVO(BillType.PURCHASE,BillState.PASS,"JHD-20171022-00001","供应商1"
			,"默认仓库","阿红",goodsItemList,"满足客户需求"
		     ,new Date());
		PurchaseVO p2=new PurchaseVO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
				,"默认仓库","阿明",goodsItemList,"好看"
				,new Date());
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	public ArrayList<PurchaseVO> showPurchase(){
		return purchaseBill;
	}
}
