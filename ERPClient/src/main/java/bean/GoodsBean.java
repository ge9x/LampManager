package bean;

import javafx.beans.property.*;

public class GoodsBean {
    StringProperty ID;
    StringProperty name;
    StringProperty classification;
    StringProperty model;
    IntegerProperty amount;
    IntegerProperty alarmAmount;
    DoubleProperty recentPurchasePrice;
    DoubleProperty purchasePrice;
    DoubleProperty recentSalesPrice;
    DoubleProperty salesPrice;
    IntegerProperty newAmount = new SimpleIntegerProperty(0);

    public GoodsBean(String ID, String name, String model,String classification,Integer alarmAmount,Integer amount, Double recentPurchasePrice, Double recentSalesPrice, Double salesPrice,Double purchasePrice) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.model = new SimpleStringProperty(model);
        this.classification = new SimpleStringProperty(classification);
        this.amount = new SimpleIntegerProperty(amount);
        this.alarmAmount = new SimpleIntegerProperty(alarmAmount);
        this.recentPurchasePrice = new SimpleDoubleProperty(recentPurchasePrice);
        this.recentSalesPrice = new SimpleDoubleProperty(recentSalesPrice);
        this.salesPrice = new SimpleDoubleProperty(salesPrice);
        this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
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

    public int getAlarmAmount() {
        return alarmAmount.get();
    }

    public IntegerProperty alarmAmountProperty() {
        return alarmAmount;
    }

    public void setAlarmAmount(int alarmAmount) {
        this.alarmAmount.set(alarmAmount);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public String getClassification() {
        return classification.get();
    }

    public StringProperty classificationProperty() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification.set(classification);
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

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public double getRecentPurchasePrice() {
        return recentPurchasePrice.get();
    }

    public DoubleProperty recentPurchasePriceProperty() {
        return recentPurchasePrice;
    }

    public void setRecentPurchasePrice(double recentPurchasePrice) {
        this.recentPurchasePrice.set(recentPurchasePrice);
    }

    public double getRecentSalesPrice() {
        return recentSalesPrice.get();
    }

    public DoubleProperty recentSalesPriceProperty() {
        return recentSalesPrice;
    }

    public void setRecentSalesPrice(double recentSalesPrice) {
        this.recentSalesPrice.set(recentSalesPrice);
    }

    public double getPurchasePrice() {
        return purchasePrice.get();
    }

    public DoubleProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice.set(purchasePrice);
    }

    public double getSalesPrice() {
        return salesPrice.get();
    }

    public DoubleProperty salesPriceProperty() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice.set(salesPrice);
    }

    public int getNewAmount() {
        return newAmount.get();
    }

    public IntegerProperty newAmountProperty() {
        return newAmount;
    }

    public void setNewAmount(int newAmount) {
        this.newAmount.set(newAmount);
    }
}