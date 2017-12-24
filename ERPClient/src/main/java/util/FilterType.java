package util;

/**
 * Created by Kry·L on 2017/12/7.
 */
public enum FilterType {
    GOODS,INVENTORY,SALESMAN,CUSTOMER;

    public static FilterType getEnumByValue(String value){
        switch (value){
            case "商品": return FilterType.GOODS;
            case "仓库": return FilterType.INVENTORY;
            case "业务员":return FilterType.SALESMAN;
            case "客户":return FilterType.CUSTOMER;
        }
        return null;
    }
}
