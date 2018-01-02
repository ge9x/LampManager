package blservice.promotionblservice.promotionTotal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionTotalVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface PromotionTotalBLService {
	/**
     * 显示所有总价促销策略
     * 
     * @return ArrayList<PromotionCustomerVO>
     */
	public ArrayList<PromotionTotalVO> show();
	
	/**
     * 提交促销策略
     * 
     * @param PromotionCustomerVO
     * @return ResultMessage
     */
	public ResultMessage submit(PromotionTotalVO vo);
	
	/**
	 * 通过促销策略ID查找总价促销策略
	 * 
	 * @param promotionID
	 * @return PromotionTotalVO
	 */
	public PromotionTotalVO findPromotionByID(String promotionID);
	
	/**
	 * 删除总价促销策略
	 * 
	 * @param promotionID
	 * @return ResultMessage
	 */
	public ResultMessage deletePromotion(String promotionID);
	
	/**
	 * 修改已有的总价促销策略
	 * 
	 * @param promotionTotalVO
	 * @return ResultMessage
	 */
	public ResultMessage updatePromotion(PromotionTotalVO promotionTotalVO);
	
	/**
	 * 得到新的总价促销策略ID
	 * 
	 * @return String
	 */
	public String getNewPromotionTotalID();
	
	/**
	 * 得到当前登录用户名
	 * 
	 * @return
	 */
	public String getCurrentUserName();
	
	/**
	 * 通过促销策略名查找总价促销策略
	 * 
	 * @param promotionName
	 * @return
	 */
	public PromotionTotalVO findPromotionByName(String promotionName);
}
