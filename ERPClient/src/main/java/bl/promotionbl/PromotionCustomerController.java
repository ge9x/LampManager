package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotioncustomer.PromotionCustomerBLService;
import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionCustomerVO;

public class PromotionCustomerController implements PromotionCustomerBLService{

	private PromotionCustomer promotionCustomer;
	
	public PromotionCustomerController(){
		promotionCustomer = new PromotionCustomer();
	}
	
	public ArrayList<PromotionCustomerVO> show() {
		// TODO Auto-generated method stub
		return null;
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

	public void setStartDate(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	public ResultMessage submit(PromotionCustomerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public PromotionCustomerVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		return null;
	}

}
