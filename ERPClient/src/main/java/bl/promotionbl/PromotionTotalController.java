package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotionTotal.PromotionTotalBLService;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionTotalVO;

public class PromotionTotalController implements PromotionTotalBLService{

	private PromotionTotal promotionTotal;
	
	public PromotionTotalController(){
		promotionTotal = new PromotionTotal();
	}
	
	public ArrayList<PromotionTotalVO> show() {
		// TODO Auto-generated method stub
		return null;
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

	public void setStartDate(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	public ResultMessage submit(PromotionTotalVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public PromotionTotalVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		return null;
	}

}
