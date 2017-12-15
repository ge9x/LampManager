package util;

/**
 * Created by Kry·L on 2017/10/21.
 */
public enum BillState {
    ALL("全部"),
    DRAFT("草稿"),
    SUBMITTED("已提交"),
    PASS("审批通过"),
    FAILED("审批失败");

    String value;

    BillState(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static BillState getEnumByValue(String value){
        switch (value){
            case "全部": return BillState.ALL;
            case "草稿":  return BillState.DRAFT;
            case "已提交": return BillState.SUBMITTED;
            case "审批通过": return BillState.PASS;
            case "审批失败": return BillState.FAILED;
        }
       return null;
    }

}
