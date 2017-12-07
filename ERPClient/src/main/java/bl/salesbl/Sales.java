package bl.salesbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfoenix.controls.JFXPopup.PopupHPosition;

import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Sales {
	private SalesVO vo;
	private SalesDataService salesDataService;
	
	public Sales(){
		
	}
	
	
	public SalesVO findSlaesByID(String ID) {
		return null;
	}
	
	public SalesVO findSalesByState(BillState state) {
		return null;
	}
	
	public ResultMessage addSales(SalesVO vo){
		return null;
	}
	
	public ResultMessage updateSales(SalesVO vo){
		return null;
	}
	
	public ResultMessage deleteSales(String ID) {
		return null;
	}
	
	public ArrayList<SalesVO> getAllSalesOrder(String startDate,String endDate) {
		try {
			ArrayList<SalesPO> salpoList=salesDataService.showSales();
			ArrayList<SalesVO> salvoList=new ArrayList<>();
			for(SalesPO po:salpoList){
				salvoList.add(poTovo(po));
			}
			return salvoList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void init(){
		
	}
	
	public ResultMessage submitSales(SalesVO vo){
		return null;
	}
	
	public static Date stringToDate(String date){
		return null;
	}
	
	public static SalesPO voTopo(SalesVO vo){
		return null;
	}
	
	public static SalesVO poTovo(SalesPO po){
		List<GoodsItemPO> goodsItempoList=po.getGoodsItemList();
		ArrayList<GoodsItemVO> goodsItemvoList=new ArrayList<>();
		for(GoodsItemPO goodsItempo:goodsItempoList){
			goodsItemvoList.add(Purchase.poTovo(goodsItempo));
		}
		return new SalesVO(po.getType(), po.getState(), po.buildID(), po.getCustomer(), String.valueOf(po.getCustomerID()), po.getSalesman(), po.getUser(), po.getInventory(), goodsItemvoList, po.getAllowance(), po.getVoucher(), po.getRemarks(), po.getDate(), po.getPromotionName());
	}
	
}
