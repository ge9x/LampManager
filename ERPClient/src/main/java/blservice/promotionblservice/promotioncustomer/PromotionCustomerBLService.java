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
     * 添加商品到促销策略中
     * 
     * @param GoodsVO
     */
	public void addGift(GoodsItemVO vo);
	
	/**
     * 添加代金券到促销策略中
     * 
     * @param price
     */
	public void addVoucher(double price);
	
	/**
     * 添加价格折让到促销策略中
     * 
     * @param price
     */
	public void addAllowance(double price);
	
	/**
     * 设置会员促销粗略针对的会员等级
     * 
     * @param level
     */
	public void setCustomer(Level level);
	
	/**
     * 设置促销策略开始时间
     * 
     * @param date
     */
	public void setStartDate(String date);
	
	/**
     * 设置促销策略结束时间
     * 
     * @param date
     */
	public void setEndDate(String date);
	
	/**
     * 提交促销策略
     * 
     * @param PromotionCustomerVO
     * @return ResultMessage
     */
	public ResultMessage submit(PromotionCustomerVO vo);
	
	public PromotionCustomerVO findPromotionByID(String promotionID);
	
	public ResultMessage deletePromotion(String promotionID);
	
	public ResultMessage updatePromotion(PromotionCustomerVO vo);
	
	public String getNewPromotionCustomerID();
}
