package util;

/** 
 * Created by Aster on 2017/10/21
 */
public enum PromotionType {
	MEMBER_PROMOTION_STRATEGY("会员促销策略"),
	BARGAIN_STRATEGY("特价包策略"),
	TOTAL_PROMOTION_STRATEGY("总价促销策略");
	
	String value;

    PromotionType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
