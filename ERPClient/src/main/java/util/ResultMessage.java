package util;

/**
 * Created by Kry·L on 2017/10/20.
 */
public enum ResultMessage {
    SUCCESS("成功"),
    FAILED("失败"),
    ERROR("错误"),
    EXIST("已存在"),
    NOT_EXIST("不存在"),
    NULL("空"),
    SUFFICIENT("数量充足"),
    INSUFFICIENT("数量不足");

    public String value;

    ResultMessage(String value){
        this.value = value;
    }
    public String toString(){
        return value;
    }

}
