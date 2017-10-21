package po;

import com.sun.glass.ui.Window.Level;

import util.CustomerCategory;

public class CustomerPO {
	/**客户编号*/
	public String customerID;
	/**客户分类:进货商、销售商*/
	public CustomerCategory category;
	/**客户级别:级别分一级到五级，一级普通用户，五级VIP用户*/
	public Level level;
	/**客户姓名*/
	public String customerName;
	/**电话*/
	public String phone;
	/**地址*/
	public String address;
	/**邮编*/
	public String postCode;
	/**电子邮箱*/
	public String mail;
	/**应收额度*/
	public double receivableLimit;
	/**应收*/
	public double receive;
	/**应付*/
	public double pay;
	/**默认业务员*/
	public String salesman;
	/**客户积分*/
	public double points;
	
	
	public CustomerPO(String customerID, CustomerCategory category, Level level, String customerName, String phone,
			String address, String postCode, String mail, double receivableLimit, double receive, double pay,
			String salesman, double points) {
		super();
		this.customerID = customerID;
		this.category = category;
		this.level = level;
		this.customerName = customerName;
		this.phone = phone;
		this.address = address;
		this.postCode = postCode;
		this.mail = mail;
		this.receivableLimit = receivableLimit;
		this.receive = receive;
		this.pay = pay;
		this.salesman = salesman;
		this.points = points;
	}


	public String getCustomerID() {
		return customerID;
	}


	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public CustomerCategory getCategory() {
		return category;
	}


	public void setCategory(CustomerCategory category) {
		this.category = category;
	}


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public double getReceivableLimit() {
		return receivableLimit;
	}


	public void setReceivableLimit(double receivableLimit) {
		this.receivableLimit = receivableLimit;
	}


	public double getReceive() {
		return receive;
	}


	public void setReceive(double receive) {
		this.receive = receive;
	}


	public double getPay() {
		return pay;
	}


	public void setPay(double pay) {
		this.pay = pay;
	}


	public String getSalesman() {
		return salesman;
	}


	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}


	public double getPoints() {
		return points;
	}


	public void setPoints(double points) {
		this.points = points;
	}
	
	
	
}
