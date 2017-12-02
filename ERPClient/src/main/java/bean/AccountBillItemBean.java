package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Kry·L on 2017/12/2.
 */
public class AccountBillItemBean {
    public StringProperty accountName;
    public DoubleProperty money;
    public StringProperty remark;

    public AccountBillItemBean(String accountName, Double money, String remark) {
        this.accountName = new SimpleStringProperty(accountName);
        this.money = new SimpleDoubleProperty(money);
        this.remark = new SimpleStringProperty(remark);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    public double getMoney() {
        return money.get();
    }


    public void setMoney(double money) {
        this.money.set(money);
    }

    public String getRemark() {
        return remark.get();
    }


    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}

