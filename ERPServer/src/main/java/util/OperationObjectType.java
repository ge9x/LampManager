package util;

/**
 * 日志中每条记录的操作对象的类型<br>
 * Created on 2017/12/25
 * 
 * @author 巽
 *
 */
public enum OperationObjectType {
	BILL("单据"),
	CLASSIFICATION("商品分类"),
	GOODS("商品"),
	CUSTOMER("客户"),
	ACCOUNT("银行账户"),
	PROMOTION("促销策略"),
	INITIALIZATION("期初建账");

	String value;

	private OperationObjectType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
