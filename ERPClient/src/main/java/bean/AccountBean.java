package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Kry·L on 2017/12/24.
 */
public class AccountBean {
    StringProperty ID;
    StringProperty name;
    DoubleProperty money;

    public AccountBean(String ID, String name, double money) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.money = new SimpleDoubleProperty(money);
    }


    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
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
}
