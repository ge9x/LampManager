package bl.promotionbl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import bl.userbl.User;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerBLService;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerInfo;
import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionCustomerVO;

public class PromotionCustomerController implements PromotionCustomerBLService, PromotionCustomerInfo{

	private PromotionCustomer promotionCustomer;
	
	public PromotionCustomerController(){
		promotionCustomer = new PromotionCustomer();
	}
	
	public ArrayList<PromotionCustomerVO> show() {
		try {
			return promotionCustomer.show();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addGift(GoodsItemVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void addVoucher(double price) {
		// TODO Auto-generated method stub
		
	}

	public void addAllowance(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setCustomer(Level level) {
		// TODO Auto-generated method stub
		
	}

	public void setStartDate(String date) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(String date) {
		// TODO Auto-generated method stub
		
	}

	public ResultMessage submit(PromotionCustomerVO vo) {
		try {
			return promotionCustomer.submit(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	public PromotionCustomerVO findPromotionByID(String promotionID) {
		try {
			return promotionCustomer.findPromotionByID(promotionID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level) {
		try {
			ArrayList<PromotionCustomerVO> VOs = show();
			for(PromotionCustomerVO vo:VOs){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = sd.parse(vo.startDate);
				Date endDate = sd.parse(vo.endDate);
				if(new Date().before(startDate)||new Date().after(endDate)||level.ordinal()<vo.level.ordinal()){
					VOs.remove(vo);
				}
			}
			return VOs;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage deletePromotion(String promotionID) {
		// TODO Auto-generated method stub
		try {
			return promotionCustomer.deletePromotion(promotionID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public ResultMessage updatePromotion(PromotionCustomerVO vo) {
		// TODO Auto-generated method stub
		try {
			return promotionCustomer.updatePromotion(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public String getNewPromotionCustomerID() {
		// TODO Auto-generated method stub
		try {
			return promotionCustomer.getNewPromotionCustomerID();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		return promotionCustomer.getCurrentUserName();
	}

}
