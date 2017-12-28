package bean;

import javafx.beans.property.*;

public class InventoryCheckBean {
    IntegerProperty line;
    StringProperty ID;
    StringProperty name;
    StringProperty model;
    IntegerProperty amount;
    StringProperty avg;

    public InventoryCheckBean(int line, String ID, String name, String model, int amount,String avg) {
        this.line = new SimpleIntegerProperty(line);
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.model = new SimpleStringProperty(model);
        this.amount = new SimpleIntegerProperty(amount);
        this.avg = new SimpleStringProperty(avg);
    }

    public int getLine() {
        return line.get();
    }

    public IntegerProperty lineProperty() {
        return line;
    }

    public void setLine(int line) {
        this.line.set(line);
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

    public String getAvg() {
        return avg.get();
    }

    public StringProperty avgProperty() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg.set(avg);
    }
}