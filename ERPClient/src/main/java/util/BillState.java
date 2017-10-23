package util;

/**
 * Created by Kry·L on 2017/10/21.
 */
public enum BillState {
    DRAFT("草稿"),
    SUBMITTED("已提交"),
    PASS("通过审批"),
    FAILED("不通过");

    String value;

    BillState(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
