package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Kry·L on 2017/12/2.
 */
public class CashBillItemBean{
    public StringProperty name;
    public DoubleProperty money;
    public StringProperty remark;

    public CashBillItemBean(String name,double money,String remark) {
        this.name = new SimpleStringProperty(name);
        this.money = new SimpleDoubleProperty(money);
        this.remark = new SimpleStringProperty(remark);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getMoney() {
        return money.get();
    }

    public DoubleProperty moneyProperty() {
        return money;
    }

    public void setMoney(double money) {
        this.money.set(money);
    }

    public String getRemark() {
        return remark.get();
    }

    public StringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}