package vo;

/**
 * created by zlk on 2017/10/21
 */

public class CustomerAddVO {
	/**客户姓名*/
	public String customerName;
	/**客户电话*/
	public String phone;
	/**地址*/
	public String address;
	/**邮编*/
	public String postCode;
	/**电子邮箱*/
	public String mail;
	/**应收额度*/
	public double receivableLimit;
	/**默认业务员*/
	public String salesman;
	
	/**
	 * 添加客户时，操作员需要界面中添加这些客户信息
	 */
	public CustomerAddVO(String customerName,String phone,String address,String postCode,String
			mail,double receivableLimit,String salesman){
		this.customerName=customerName;
		this.phone=phone;
		this.address=address;
		this.postCode=postCode;
		this.mail=mail;
		this.receivableLimit=receivableLimit;
		this.salesman=salesman;
	}
}
