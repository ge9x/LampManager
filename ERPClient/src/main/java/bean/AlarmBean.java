package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Kry·L on 2017/12/21.
 */
public class AlarmBean{
    StringProperty ID;
    StringProperty name;
    IntegerProperty currentNum;
    IntegerProperty alarmNum;
    IntegerProperty minusNum;

    public AlarmBean(String ID,String name,int currentNum,int alarmNum,int minusNum) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.currentNum = new SimpleIntegerProperty(currentNum);
        this.alarmNum = new SimpleIntegerProperty(alarmNum);
        this.minusNum = new SimpleIntegerProperty(minusNum);
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

    public int getCurrentNum() {
        return currentNum.get();
    }

    public IntegerProperty currentNumProperty() {
        return currentNum;
    }

    public void setCurrentNum(int cuurentNum) {
        this.currentNum.set(cuurentNum);
    }

    public int getAlarmNum() {
        return alarmNum.get();
    }

    public IntegerProperty alarmNumProperty() {
        return alarmNum;
    }

    public void setAlarmNum(int alarmNum) {
        this.alarmNum.set(alarmNum);
    }

    public int getMinusNum() {
        return minusNum.get();
    }

    public IntegerProperty minusNumProperty() {
        return minusNum;
    }

    public void setMinusNum(int minusNum) {
        this.minusNum.set(minusNum);
    }
}