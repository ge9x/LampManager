package util;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class Money {
    public static String getMoneyString(double money){
        boolean lessThanZero = false;
        if (money < 0){
            lessThanZero = true;
            money = -money;
        }
        String raw = String.valueOf(money);
        if (!raw.contains(".")){
            raw = raw + ".00";
        }
        if ((raw.length() - raw.lastIndexOf(".")) < 3){
            for (int i = 0; i < 3-(raw.length() - raw.lastIndexOf(".")); i++){
                raw = raw + "0";
            }
        }
        for (int i = raw.lastIndexOf(".")-3; i >= 0; i=i-3){
            raw = raw.substring(0,i) + "," + raw.substring(i);
        }
        if (raw.charAt(0) == ',')
            raw = raw.substring(1);
        if (raw.substring(raw.length()-3).equals(".00"))
            raw = raw.substring(0,raw.length()-3);
        if (lessThanZero)
            return "￥-"+raw;
        else
            return "￥"+raw;
    }
}
