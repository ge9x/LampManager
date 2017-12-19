package bl.promotionbl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotionbargain.PromotionBargainBLService;
import blservice.promotionblservice.promotionbargain.PromotionBargainInfo;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class PromotionBargainController implements PromotionBargainBLService, PromotionBargainInfo{

	private PromotionBargain promotionBargain;
	
	public PromotionBargainController(){
		promotionBargain = new PromotionBargain();
	}
	
	public ArrayList<PromotionBargainVO> show() {
		try {
			return promotionBargain.show();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addBargain(GoodsItemVO vo) {
		
	}

	public void setPrice(double price) {
		
	}

	public void setStartDate(String date) {
		
	}

	public void setEndDate(String date) {
		
	}

	public ResultMessage submit(PromotionBargainVO vo) {
		try {
			return promotionBargain.submit(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	public PromotionBargainVO findPromotionByID(String promotionID) {
		try {
			return promotionBargain.findPromotionByID(promotionID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PromotionBargainVO> getFitPromotionBargain() {
		return show();
	}

	@Override
	public ResultMessage deletePromotion(String promotionID) {
		// TODO Auto-generated method stub
		try {
			return promotionBargain.deletePromotion(promotionID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public ResultMessage updatePromotion(PromotionBargainVO vo) {
		// TODO Auto-generated method stub
		try {
			return promotionBargain.updatePromotion(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public String getNewPromotionBargainID() {
		// TODO Auto-generated method stub
		try {
			return promotionBargain.getNewPromotionBargainID();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		return promotionBargain.getCurrentUserName();
	}

	@Override
	public PromotionBargainVO findPromotionByName(String promotionName) {
		// TODO Auto-generated method stub
		try {
			return promotionBargain.findPromotionByName(promotionName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	

}
