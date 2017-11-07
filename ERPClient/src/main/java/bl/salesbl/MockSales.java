package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

public class MockSales extends Sales{
	ArrayList<SalesPO> salesBill=new ArrayList<SalesPO>();
	ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO( "挂灯", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		SalesPO s1=new SalesPO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
				"阿强", "默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date());
	    SalesPO s2=new SalesPO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
					"阿奇", "默认仓库",goodsItemList , 100,500, "满足客户需求", new Date());
	    salesBill.add(s1);
	    salesBill.add(s2);
	}
	
	public SalesPO findSlaesByID(String ID) {
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(ID)){
				System.out.println("Find sales success");
				return sal;
			}
		}
		System.out.println("Find sales failed");
		return null;
	}
	
	public SalesPO findSalesByState(BillState state) {
		for(SalesPO sal:salesBill){
			if(sal.getState().equals(state)){
				System.out.println("Find sales success");
				return sal;
			}
		}
		System.out.println("Find sales failed");
		return null;
	}
	
	public ResultMessage addSales(SalesPO po){
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(po.getID())){
				System.out.println("Add sales failed");
				return ResultMessage.FAILED;
			}
		}
		System.out.println("Add sales success");
		salesBill.add(po);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage updateSales(SalesPO po){
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(po.getID())){
				System.out.println("Update sales success");
				salesBill.remove(sal);
				salesBill.add(po);
				return ResultMessage.SUCCESS;
	
			}
		}
		System.out.println("Update sales failed");
		return ResultMessage.FAILED;
	}
	
	public ResultMessage deleteSales(String ID) {
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(ID)){
				salesBill.remove(sal);
				System.out.println("Delete sales success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete sales failed");
		return ResultMessage.FAILED;
	}
	
	public void init(){
		salesBill.clear();
		System.out.println("Init sales and purchase success");
	}
}
