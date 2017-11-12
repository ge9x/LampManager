package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class MockSales extends Sales{
	ArrayList<SalesVO> salesBill=new ArrayList<SalesVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();
	
	GoodsItemVO gi1=new GoodsItemVO("01", "霓虹灯",null, 20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO("02", "挂灯",null, 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		SalesVO s1=new SalesVO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
				"阿强","00000003", "默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date());
	    SalesVO s2=new SalesVO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
					"阿奇", "00000004","默认仓库",goodsItemList , 100,500, "满足客户需求", new Date());
	    salesBill.add(s1);
	    salesBill.add(s2);
	}
	
	public SalesVO findSlaesByID(String ID) {
		for(SalesVO sal:salesBill){
			if(sal.ID.equals(ID)){
				System.out.println("Find sales success");
				return sal;
			}
		}
		System.out.println("Find sales failed");
		return null;
	}
	
	public SalesVO findSalesByState(BillState state) {
		for(SalesVO sal:salesBill){
			if(sal.state.equals(state)){
				System.out.println("Find sales success");
				return sal;
			}
		}
		System.out.println("Find sales failed");
		return null;
	}
	
	public ResultMessage addSales(SalesVO po){
		for(SalesVO sal:salesBill){
			if(sal.ID.equals(po)){
				System.out.println("Add sales failed");
				return ResultMessage.FAILED;
			}
		}
		System.out.println("Add sales success");
		salesBill.add(po);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage updateSales(SalesVO po){
		for(SalesVO sal:salesBill){
			if(sal.ID.equals(po.ID)){
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
		for(SalesVO sal:salesBill){
			if(sal.ID.equals(ID)){
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
	
	public ResultMessage submitSales(SalesVO vo){
		for(SalesVO sal:salesBill){
			if(sal.ID.equals(vo.ID)){
				sal.state=BillState.SUBMITTED;
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("not find bill");
		return ResultMessage.NOT_EXIST;
	}
}
