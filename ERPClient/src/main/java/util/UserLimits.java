package util;

/**
 * Created by Aster on 2017/10/21.
 */
public enum UserLimits {
	STAFF("普通员工"),
	MANAGER("经理");
	
	String value;

    UserLimits(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
