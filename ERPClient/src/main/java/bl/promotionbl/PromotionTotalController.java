package bl.promotionbl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotionTotal.PromotionTotalBLService;
import blservice.promotionblservice.promotionTotal.PromotionTotalInfo;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionTotalVO;

public class PromotionTotalController implements PromotionTotalBLService, PromotionTotalInfo{

	private PromotionTotal promotionTotal;
	
	public PromotionTotalController(){
		promotionTotal = new PromotionTotal();
	}
	
	public ArrayList<PromotionTotalVO> show() {
		try {
			return promotionTotal.show();
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

	public void setPrice(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setStartDate(String date) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(String date) {
		// TODO Auto-generated method stub
		
	}

	public ResultMessage submit(PromotionTotalVO vo) {
		try {
			return promotionTotal.submit(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	public PromotionTotalVO findPromotionByID(String promotionID) {
		try {
			return promotionTotal.findPromotionByID(promotionID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total) {
		ArrayList<PromotionTotalVO> VOs = show();
		ArrayList<PromotionTotalVO> result = new ArrayList<>();
		for(PromotionTotalVO vo:VOs){
			if(total>=vo.totalPrice){
				result.add(vo);
			}
		}
		return result;
	}

	@Override
	public ResultMessage deletePromotion(String promotionID) {
		// TODO Auto-generated method stub
		try {
			return promotionTotal.deletePromotion(promotionID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public ResultMessage updatePromotion(PromotionTotalVO promotionTotalVO) {
		// TODO Auto-generated method stub
		try {
			return promotionTotal.updatePromotion(promotionTotalVO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public String getNewPromotionTotalID() {
		// TODO Auto-generated method stub
		try {
			return promotionTotal.getNewPromotionTotalID();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		return promotionTotal.getCurrentUserName();
	}

}
