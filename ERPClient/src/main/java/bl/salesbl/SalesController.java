package bl.salesbl;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;

import blservice.salesblservice.SalesBLService;
import blservice.salesblservice.SalesInfo;
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

public class SalesController implements SalesBLService,SalesInfo{

	private Sales sales;
	
	public SalesController(){
		sales=new Sales();
	}

	@Override
	public ArrayList<SalesVO> getAllSalesOrder(String startDate,String endDate) {
		try {
			return sales.getAllSalesOrder(startDate,endDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	//start
	@Override
	public ResultMessage examine(SalesVO vo) {
		try {
			return sales.examine(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ArrayList<SalesVO> getAllSubmittedSales() {
		try {
			return sales.getAllSubmittedSales();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PromotionBargainVO> showBargains() {
		return sales.showBargains();
	}

	@Override
	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level) {
		return sales.getFitPromotionCustomer(level);
	}

	@Override
	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total) {
		return sales.getFitPromotionTotal(total);
	}

	@Override
	public ResultMessage addPurchase(PurchaseVO vo) {
		return null;
	}

	@Override
	public ResultMessage addGoodsItem(GoodsItemVO item) {
		try {
			return sales.addGoodsItem(item);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage addSales(SalesVO vo) {
		try {
			return sales.addSales(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage submitPurchase(PurchaseVO pur) {
		return null;
	}

	@Override
	public ResultMessage submitSales(SalesVO sal) {
		try {
			return sales.submitSales(sal);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage saveSales(SalesVO bill) {
		try {
			return sales.saveSales(bill);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage savePurchase(PurchaseVO bill) {
		return null;
	}

	@Override
	public String getUserName() {
		return sales.getUserName();
	}

	@Override
	public ArrayList<CustomerVO> getAllSupplier() {
		return sales.getAllSupplier();
	}

	@Override
	public ArrayList<String> getAllInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> getAllCustomer() {
		return sales.getAllCustomer();
	}

	@Override
	public String getnewPurchaseID() {
		return null;
	}

	@Override
	public String getnewReturnID() {
		return null;
	}

	@Override
	public String getnewSalesID() {
		try {
			return sales.getnewSalesID();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnewSalesReturnID() {
		try {
			return sales.getnewSalesReturnID();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PurchaseVO> getPurchaseOrderByState(BillState state) {
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> getReturnOrderByState(BillState state) {
		return null;
	}

	@Override
	public ArrayList<SalesVO> getSalesOrderByState(BillState state) {
		try {
			return sales.getSalesOrderByState(state);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<SalesVO> getSalesreturnOrderByState(BillState state) {
		try {
			return sales.getSalesreturnOrderByState(state);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage deletePurchase(PurchaseVO vo) {
		return null;
	}

	@Override
	public ResultMessage deleteSales(SalesVO vo) {
		try {
			return sales.deleteSales(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}

	@Override
	public ResultMessage updatePurchase(PurchaseVO vo) {
		return null;
	}

	@Override
	public ResultMessage updateSales(SalesVO vo) {
		try {
			return sales.updateSales(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.NULL;
		}
	}
	
	
}
