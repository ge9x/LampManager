package po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zlk on 2017/12/18
 */

@Entity
@Table(name = "initcustomer")
public class InitCustomerPO implements Serializable{
	private static final long servialVersionUID=454723894278482L;
		
	private int iD;
	/**客户编号*/
	private String customerID;
	/**客户分类:进货商、销售商*/
	private String category;
	/**客户级别:级别分一级到五级，一级普通用户，五级VIP用户*/
	private String level;
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
	public InitCustomerPO(String customerID, String category, String level, String customerName, String phone,
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	@Column(name = "customerID")
	public String getCustomerID(){
		return customerID;
	}
	
	public void setCustomerID(String customerID){
		this.customerID=customerID;
	}

	@Column(name = "category")
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name="level")
	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "customerName")
	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postcode")
	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "mail")
	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "receivableLimit")
	public double getReceivableLimit() {
		return receivableLimit;
	}


	public void setReceivableLimit(double receivableLimit) {
		this.receivableLimit = receivableLimit;
	}

	@Column(name = "receive")
	public double getReceive() {
		return receive;
	}


	public void setReceive(double receive) {
		this.receive = receive;
	}

	@Column(name = "pay")
	public double getPay() {
		return pay;
	}


	public void setPay(double pay) {
		this.pay = pay;
	}

	@Column(name = "salesman")
	public String getSalesman() {
		return salesman;
	}


	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Column(name = "points")
	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
}
