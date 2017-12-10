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
		try {
			ArrayList<PromotionTotalVO> VOs = show();
			for(PromotionTotalVO vo:VOs){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = sd.parse(vo.startDate);
				Date endDate = sd.parse(vo.endDate);
				if(new Date().before(startDate)||new Date().after(endDate)||total<vo.totalPrice){
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

}
