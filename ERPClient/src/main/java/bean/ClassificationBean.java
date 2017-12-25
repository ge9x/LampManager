package bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Kry·L on 2017/12/18.
 */
public class ClassificationBean {

    StringProperty ID;
    StringProperty name;

    public ClassificationBean(String ID, String name) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
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
}
