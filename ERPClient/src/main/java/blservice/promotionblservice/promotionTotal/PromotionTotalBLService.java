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
     * 添加赠品到促销策略中
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
	public ResultMessage submit(PromotionTotalVO vo);
	
	public PromotionTotalVO findPromotionByID(String promotionID);
}
