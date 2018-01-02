package blservice.promotionblservice.promotionbargain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface PromotionBargainBLService {
	/**
     * 显示所有特价包策略
     * 
     * @return ArrayList<PromotionBargainVO>
     */
	public ArrayList<PromotionBargainVO> show();
	
	/**
     * 提交特价包促销策略
     * 
     * @param PromotionCustomerVO
     * @return ResultMessage
     */
	public ResultMessage submit(PromotionBargainVO vo);
	
	/**
	 * 通过促销策略ID查找特价包促销策略
	 * 
	 * @param promotionID
	 * @return PromotionBargainVO
	 */
	public PromotionBargainVO findPromotionByID(String promotionID);
	
	/**
	 * 删除特价包促销策略
	 * 
	 * @param promotionID
	 * @return ResultMessage
	 */
	public ResultMessage deletePromotion(String promotionID);
	
	/**
	 * 修改已有特价包促销策略
	 * 
	 * @param PromotionBargainVO
	 * @return ResultMessage
	 */
	public ResultMessage updatePromotion(PromotionBargainVO vo);
	
	/**
	 * 得到新的特价包促销策略ID
	 * 
	 * @return String
	 */
	public String getNewPromotionBargainID();
	
	/**
	 * 得到当前登录用户名
	 * 
	 * @return String
	 */
	public String getCurrentUserName();
	
	/**
	 * 通过促销策略名查找特价包促销策略
	 * 
	 * @param promotionName
	 * @return PromotionBargainVO
	 */
	public PromotionBargainVO findPromotionByName(String promotionName);
}
