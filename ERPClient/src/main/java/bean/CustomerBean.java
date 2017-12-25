package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Kry·L on 2017/12/24.
 */
public class CustomerBean {
    /**客户编号*/
    private StringProperty customerID;
    /**客户分类:进货商、销售商*/
    private StringProperty category;
    /**客户级别:级别分一级到五级，一级普通用户，五级VIP用户*/
    private StringProperty level;
    /**客户姓名*/
    private StringProperty customerName;
    /**电话*/
    private StringProperty phone;
    /**地址*/
    private StringProperty address;
    /**邮编*/
    private StringProperty postCode;
    /**电子邮箱*/
    private StringProperty mail;
    /**应收额度*/
    private DoubleProperty receivableLimit;
    /**应收*/
    private DoubleProperty receive;
    /**应付*/
    private DoubleProperty pay;
    /**默认业务员*/
    private StringProperty salesman;
    /**客户积分*/
    private DoubleProperty points;

    public CustomerBean(String customerID, String category, String level, String customerName,
                        String phone, String address, String postCode, String mail, Double receivableLimit, Double receive, Double pay, String salesman, Double points) {
        this.customerID = new SimpleStringProperty(customerID);
        this.category = new SimpleStringProperty(category);
        this.level = new SimpleStringProperty(level);
        this.customerName = new SimpleStringProperty(customerName);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.postCode = new SimpleStringProperty(postCode);
        this.mail = new SimpleStringProperty(mail);
        this.receivableLimit = new SimpleDoubleProperty(receivableLimit);
        this.receive = new SimpleDoubleProperty(receive);
        this.pay = new SimpleDoubleProperty(pay);
        this.salesman = new SimpleStringProperty(salesman);
        this.points = new SimpleDoubleProperty(points);
    }

    public String getCustomerID() {
        return customerID.get();
    }

    public StringProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID.set(customerID);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getLevel() {
        return level.get();
    }

    public StringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPostCode() {
        return postCode.get();
    }

    public StringProperty postCodeProperty() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }

    public String getMail() {
        return mail.get();
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public double getReceivableLimit() {
        return receivableLimit.get();
    }

    public DoubleProperty receivableLimitProperty() {
        return receivableLimit;
    }

    public void setReceivableLimit(double receivableLimit) {
        this.receivableLimit.set(receivableLimit);
    }

    public double getReceive() {
        return receive.get();
    }

    public DoubleProperty receiveProperty() {
        return receive;
    }

    public void setReceive(double receive) {
        this.receive.set(receive);
    }

    public double getPay() {
        return pay.get();
    }

    public DoubleProperty payProperty() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay.set(pay);
    }

    public String getSalesman() {
        return salesman.get();
    }

    public StringProperty salesmanProperty() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman.set(salesman);
    }

    public double getPoints() {
        return points.get();
    }

    public DoubleProperty pointsProperty() {
        return points;
    }

    public void setPoints(double points) {
        this.points.set(points);
    }
}
