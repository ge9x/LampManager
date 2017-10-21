package blservice.promotionTotal;

import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface PromotionTotal {
	/**
     * 显示所有总价促销策略
     * 
     * @return ArrayList<PromotionCustomerVO>
     */
	public ArrayList<PromotionTotalVO> show();
	
	/**
     * 添加赠品到促销策略中
     * 
     * @param GoodsVO
     */
	public void addGift(GoodsVO vo);
	
	/**
     * 添加代金券到促销策略中
     * 
     * @param price
     */
	public void addVoucher(double price);
	
	/**
     * 设置促销策略的目标总价
     * 
     * @param price
     */
	public void setPrice(double price);
	
	/**
     * 设置促销策略开始时间
     * 
     * @param date
     */
	public void setStartDate(Date date);
	
	/**
     * 设置促销策略结束时间
     * 
     * @param date
     */
	public void setEndDate(Date date);
	
	/**
     * 提交促销策略
     * 
     * @param PromotionCustomerVO
     * @return ResultMessage
     */
	public ResultMessage submit(PromotionCustomerVO vo);
}
