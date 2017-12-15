package bl.salesbl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesBLService;
import util.BillState;
import util.BillType;
import util.Level;
import util.ResultMessage;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class PurchaseController implements SalesBLService,PurchaseInfo{
	private Purchase purchase;
	
	public PurchaseController(){
		purchase=new Purchase();
	}

	@Override
	public ResultMessage examine(PurchaseVO vo) {
		try {
			return purchase.updatePurchase(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ArrayList<PurchaseVO> getAllSubmittedPurchase() {
		try {
			return purchase.getAllSubmittedPurchase();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
//blservice
	@Override
	public ArrayList<PromotionBargainVO> showBargains() {
		return null;
	}

	@Override
	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level) {
		return null;
	}

	@Override
	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total) {
		return null;
	}

	@Override
	public ResultMessage addPurchase(PurchaseVO vo) {
		try {
			return purchase.addPurchase(vo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage addGoodsItem(GoodsItemVO item) {
		try {
			return purchase.addGoodsItem(item);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage addSales(SalesVO vo) {
		return null;
	}

	@Override
	public ResultMessage submitPurchase(PurchaseVO pur) {
		try {
			return purchase.submitPurchase(pur);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage submitSales(SalesVO sal) {
		return null;
	}

	@Override
	public ResultMessage saveSales(SalesVO bill) {
		return null;
	}

	@Override
	public ResultMessage savePurchase(PurchaseVO bill) {
		try {
			return purchase.savePurchase(bill);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public String getUserName() {
		return purchase.getUserName();
	}

	@Override
	public ArrayList<CustomerVO> getAllSupplier() {
		return purchase.getAllSupplier();
	}

	@Override
	public ArrayList<String> getAllInventory() {
		return purchase.getAllInventory();
	}

	@Override
	public ArrayList<CustomerVO> getAllCustomer() {
		return purchase.getAllCustomer();
	}

	@Override
	public String getnewPurchaseID() {
		try {
			return purchase.getnewPurchaseID();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnewReturnID() {
		try {
			return purchase.getnewReturnID();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnewSalesID() {
		return null;
	}

	@Override
	public String getnewSalesReturnID() {
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> getPurchaseByDate(String startDate, String endDate){
		try {
			return purchase.getPurchaseByDate(startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PurchaseVO> getPurchaseOrderByState(BillState state) {
		try {
			return purchase.getPurchaseOrderByState(state);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PurchaseVO> getReturnOrderByState(BillState state) {
		try {
			return purchase.getReturnOrderByState(state);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<SalesVO> getSalesOrderByState(BillState state) {
		return null;
	}

	@Override
	public ArrayList<SalesVO> getSalesreturnOrderByState(BillState state) {
		return null;
	}

	@Override
	public ResultMessage deletePurchase(PurchaseVO vo) {
		try {
			return purchase.deletePurchase(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage deleteSales(SalesVO vo) {
		return null;
	}

	@Override
	public ResultMessage updatePurchase(PurchaseVO vo) {
		try {
			return purchase.updatePurchase(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage updateSales(SalesVO vo) {
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> getPurchaseByDateAndInventory(String startDate, String endDate, String inventory,
			BillType type) {
		try {
			return purchase.getPurchaseByDateAndInventory(startDate, endDate, inventory, type);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
