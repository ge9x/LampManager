package vo;

import util.Level;

import util.CustomerCategory;

/**
 * created by zlk on 2017/10/21
 */

public class CustomerVO {
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
	///**代金券*/
	//public double voucher;
	
	/**
	 * 在查看界面时，显示所有可以显示的商品信息
	 */
	public CustomerVO(String customerID,CustomerCategory category,Level level,String customerName,
			String phone,String address,String postCode,String mail,double receivableLimit,double
			receive,double pay,String salesman,double points){
		this.customerID=customerID;
		this.category=category;
		this.level=level;
		this.customerName=customerName;
		this.phone=phone;
		this.address=address;
		this.postCode=postCode;
		this.mail=mail;
		this.receivableLimit=receivableLimit;
		this.receive=receive;
		this.pay=pay;
		this.salesman=salesman;
		this.points=points;
	}
}
