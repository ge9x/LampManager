package po;

import util.Level;
import java.util.Date;
import util.CustomerCategory;

public class CustomerPO {
	/**客户编号*/
	private String customerID;
	/**客户分类:进货商、销售商*/
	private CustomerCategory category;
	/**客户级别:级别分一级到五级，一级普通用户，五级VIP用户*/
	private Level level;
	/**客户姓名*/
	private String customerName;
	/**电话*/
	private String phone;
	/**地址*/
	private String address;
	/**邮编*/
	private String postCode;
	/**电子邮箱*/
	private String mail;
	/**应收额度*/
	private double receivableLimit;
	/**应收*/
	private double receive;
	/**应付*/
	private double pay;
	/**默认业务员*/
	private String salesman;
	/**客户积分*/
	private double points;
	/**代金券*/
	private double voucher;
	
	
	public CustomerPO(String customerID, CustomerCategory category, Level level, String customerName, String phone,
			String address, String postCode, String mail, double receivableLimit, double receive, double pay,
			String salesman, double points,double voucher) {
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
		this.voucher=voucher;
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


	public double getVoucher() {
		return voucher;
	}


	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}


	
}
