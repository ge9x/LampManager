package bean;

import javafx.beans.property.*;

/**
 * Created by Kry·L on 2017/12/2.
 */
public class SalesDetailsBean{
    StringProperty date;
    StringProperty name;
    StringProperty model;
    IntegerProperty amount;
    DoubleProperty price;
    DoubleProperty sum;

    public SalesDetailsBean(String date,String name,String model,int amount,double price,double sum) {
        this.date = new SimpleStringProperty(date);
        this.name = new SimpleStringProperty(name);
        this.model = new SimpleStringProperty(model);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleDoubleProperty(price);
        this.sum = new SimpleDoubleProperty(sum);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getSum() {
        return sum.get();
    }

    public DoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }
}
