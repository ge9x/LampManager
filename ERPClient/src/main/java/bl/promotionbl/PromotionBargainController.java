package bl.promotionbl;

import java.util.ArrayList;
import java.util.Date;

import blservice.promotionbargain.PromotionBargainBLService;
import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionBargainVO;

public class PromotionBargainController implements PromotionBargainBLService{

	private PromotionBargain promotionBargain;
	
	public PromotionBargainController(){
		promotionBargain = new PromotionBargain();
	}
	
	public ArrayList<PromotionBargainVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addBargain(GoodsVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void setPrice(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	public ResultMessage submit(PromotionBargainVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
