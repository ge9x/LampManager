package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GoodsItemBean {
	public StringProperty ID;
    public StringProperty name;
    public StringProperty model;
    public IntegerProperty amount;
    public DoubleProperty retailPrice;
    public DoubleProperty totalPrice;
    public StringProperty remark;

    public GoodsItemBean(String ID, String name, String model, int amount, double retailPrice, double totalPrice, String remark) {

        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.model = new SimpleStringProperty(model);
        this.amount = new SimpleIntegerProperty(amount);
        this.retailPrice = new SimpleDoubleProperty(retailPrice);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
        this.remark = new SimpleStringProperty(remark);
    }
    
    public String getID(){
    	return ID.get();
    }
    
    public StringProperty IDProperty(){
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
    
    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }
    
    public void setModel(String model) {
        this.model.set(model);
    }
    
    public int getAmount(){
    	return amount.get();
    }
    
    public IntegerProperty amountProperty(){
    	return amount;
    }
    
    public void setAmount(int amount){
    	this.amount.set(amount);
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public DoubleProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice.set(totalPrice);
    }
    
    public double getRetailPrice() {
        return retailPrice.get();
    }

    public DoubleProperty retailPriceProperty() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice.set(retailPrice);
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
