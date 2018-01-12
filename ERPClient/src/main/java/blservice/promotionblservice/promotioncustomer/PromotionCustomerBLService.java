package blservice.promotionblservice.promotioncustomer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionCustomerVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface PromotionCustomerBLService {
	/**
     * 显示所有会员促销策略
     * 
     * @return ArrayList<PromotionCustomerVO>
     */
	public ArrayList<PromotionCustomerVO> show();
	
	/**
     * 提交会员促销策略
     * 
     * @param PromotionCustomerVO
     * @return ResultMessage
     */
	public ResultMessage submit(PromotionCustomerVO vo);
	
	/**
	 * 通过促销策略ID查找会员促销策略
	 * 
	 * @param promotionID
	 * @return PromotionCustomerVO
	 */
	public PromotionCustomerVO findPromotionByID(String promotionID);
	
	/**
	 * 修改已有会员促销策略
	 * 
	 * @param PromotionCustomerVO
	 * @return ResultMessage
	 */
	public ResultMessage updatePromotion(PromotionCustomerVO vo);
	
	/**
	 * 得到新的会员促销策略ID
	 * 
	 * @return String
	 */
	public String getNewPromotionCustomerID();
	
	/**
	 * 得到当前登录用户名
	 * 
	 * @return String
	 */
	public String getCurrentUserName();
	
	/**
	 * 通过促销策略名查找会员促销策略
	 * @param promotionName
	 * @return PromotionCustomerVO
	 */
	public PromotionCustomerVO findPromotionByName(String promotionName);
}
