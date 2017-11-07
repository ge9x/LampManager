package util;

/**
 * 库存变动类型：IN(入库), OUT(出库), PURCHASE(进货), SALES(销售)<br><br>
 * Created on 2017/10/21
 * @author 巽
 *
 */
public enum InventoryListItemType {
	IN("入库"),
	OUT("出库"),
	PURCHASE("进货"),
	SALES("销售");

	String value;
	
	InventoryListItemType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
