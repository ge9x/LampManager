package bl.customerbl;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserInfo;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import rmi.CustomerRemoteHelper;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import util.UserLimits;
import vo.CustomerVO;
import vo.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

/**
 * Created by zlk on 2017/11/5
 */

public class Customer {
	private CustomerDataService customerDataService;
	UserInfo userInfo;
	
	public Customer(){
		customerDataService=CustomerRemoteHelper.getInstance().getCustomerDataService();
		userInfo=UserBLFactory.getInfo(); 
	}
	
	/**
	 * 创建客户时得到客户编号
	 * 
	 * @return customerID
	 * @author zlk
	 * @throws RemoteException 
	 * 
	 */
	public String getNewCustomerID() throws RemoteException{
		ArrayList<CustomerPO> cusList=customerDataService.show();
		if(cusList.size()==0){
			return "00000001";
		}else{
		String res=String.valueOf(cusList.get(cusList.size()-1).getID()+1);
		int len=res.length();
		for(int i=0;i<8-len;i++){
			res="0"+res;
		}
		return res;
		}
		//return customerDataService.getNewCustomerID();
	}
      //管理客户的步骤
	/**
	 * 管理客户中的添加客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ResultMessage addCustomer(CustomerVO vo) throws RemoteException {
		if(vo.level==Level.LEVEL_ONE){
			vo.points=0;
		}else if(vo.level==Level.LEVEL_TWO){
			vo.points=5;
		}else if(vo.level==Level.LEVEL_THREE){
			vo.points=10;
		}else if(vo.level==Level.LEVEL_FOUR){
			vo.points=20;
		}else if(vo.level==Level.LEVEL_FIVE){
			vo.points=35;
		}
		CustomerPO po=voTopo(vo);
		return customerDataService.add(po);
	}
	/**
	 * 管理客户中的删除客户
	 * 
	 * @param name
	 * @return 处理信息
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ResultMessage deleteCustomer(String customerID) throws RemoteException{
		CustomerPO po=customerDataService.getCustomerData(Integer.parseInt(customerID));
		if(po.getReceive()==0&&po.getPay()==0){
		return customerDataService.delete(po);
		}else{
			return ResultMessage.FAILED;
		}
	}
	/**
	 * 通过关键字查找客户
	 * 
	 * @param keywords
	 * @return 满足条件的客户
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) throws RemoteException{
		ArrayList<CustomerPO> cuspoList=customerDataService.findByKeywords(keywords);
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
		
	}
	/**
	 * 通过客户编号查找客户
	 * 
	 * @param customerID
	 * @return 满足条件的客户
	 * @author zlk
	 * @throws RemoteException 
	 * @throws NumberFormatException 
	 */
	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) throws NumberFormatException, RemoteException {
		ArrayList<CustomerPO> cuspoList=customerDataService.findByCustomerID(Integer.parseInt(customerID));
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
	}
	
	public ArrayList<CustomerVO> findCustomer(String input) throws RemoteException {
		ArrayList<CustomerPO> cuspoList=customerDataService.findCustomer(input);
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
	}

	/**
	 * 管理客户中的更新客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 * @throws RemoteException 
	 */
	public ResultMessage updateCustomer(CustomerVO vo) throws RemoteException{
			CustomerPO po=customerDataService.getCustomerData(Integer.parseInt(vo.customerID));
			po.setCategory(vo.category.getValue());
			po.setLevel(vo.level.getValue());
			po.setCustomerName(vo.customerName);
			po.setPhone(vo.phone);
			po.setAddress(vo.address);
			po.setPostCode(vo.postCode);
			po.setMail(vo.mail);
			po.setSalesman(vo.salesman);
			po.setReceivableLimit(vo.receivableLimit);
			po.setPoints(vo.points);
		    return customerDataService.update(po);
	}
	
	public ArrayList<CustomerVO> show() throws RemoteException {
		ArrayList<CustomerPO> cuspoList=customerDataService.show();
		ArrayList<CustomerVO> cusvoList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			cusvoList.add(poTovo(po));
		}
		return cusvoList;
	}
	
	public ArrayList<Integer> getAllCustomerID(){
	try{	
		ArrayList<CustomerPO> cuspoList = customerDataService.show();
		ArrayList<Integer> IDList=new ArrayList<>();
		for(CustomerPO po:cuspoList){
			IDList.add(po.getID());
		}
		return IDList;
	}catch (RemoteException e) {
		e.printStackTrace();
		return null;
	}
	}
	
	public ArrayList<String> getAllCustomerName(){
		try{
			ArrayList<CustomerPO> cuspoList = customerDataService.show();
			ArrayList<String> nameList=new ArrayList<>();
			for(CustomerPO po:cuspoList){
				nameList.add(po.getCustomerName());
			}
			return nameList;
		}catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CustomerVO getCustomerByID(int ID){
		try {
			CustomerPO po=customerDataService.getCustomerData(ID);
			return poTovo(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultMessage raiseCustomerReceive(int customerID, double amount) {
		try {
			CustomerPO po=customerDataService.getCustomerData(customerID);
			po.setReceive(po.getReceive()+amount);
			double oldPoints=po.getPoints();
			double newPoints=oldPoints+amount/10000*1;
			po.setPoints(newPoints);
			if(newPoints<5){
				po.setLevel(Level.LEVEL_ONE.getValue());
			}else if(newPoints>=5&&newPoints<10){
				po.setLevel(Level.LEVEL_TWO.getValue());
			}else if(newPoints>=10&&newPoints<20){
				po.setLevel(Level.LEVEL_THREE.getValue());
			}else if(newPoints>=20&&newPoints<35){
				po.setLevel(Level.LEVEL_FOUR.getValue());
			}else{
				po.setLevel(Level.LEVEL_FIVE.getValue());
			}
			return customerDataService.update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultMessage reduceCustomerReceive(int customerID, double amount) {
		try {
			CustomerPO po=customerDataService.getCustomerData(customerID);
			po.setReceive(po.getReceive()-amount);
			return customerDataService.update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultMessage raiseCustomerPay(int customerID, double amount) {
		try {
			CustomerPO po=customerDataService.getCustomerData(customerID);
			po.setPay(po.getPay()+amount);
			return customerDataService.update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultMessage reduceCustomerPay(int customerID, double amount) {
		try {
			CustomerPO po=customerDataService.getCustomerData(customerID);
			po.setPay(po.getPay()-amount);
			return customerDataService.update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CustomerVO> getAllSupplier() throws RemoteException {
		ArrayList<CustomerPO> cusList=customerDataService.show();
		ArrayList<CustomerVO> cusvo=new ArrayList<>();
		for(CustomerPO po:cusList){
			if(po.getCategory().equals("进货商")){
				cusvo.add(poTovo(po));
			}
		}
		return cusvo;
	}
	
	public ArrayList<CustomerVO> getAllSeller() throws RemoteException{
		ArrayList<CustomerPO> cusList=customerDataService.show();
		ArrayList<CustomerVO> cusvo=new ArrayList<>();
		for(CustomerPO po:cusList){
			if(po.getCategory().equals("销售商")){
				cusvo.add(poTovo(po));
			}
		}
		return cusvo;
	}
	
	private String alterID(String ID){
		int len=ID.length();
		for(int i=0;i<8-len;i++){
			ID="0"+ID;
		}
		return ID;
	}
	
	public CustomerPO voTopo(CustomerVO vo){
		return new CustomerPO(vo.category,vo.level,vo.customerName,vo.phone,vo.address,vo.postCode,vo.mail,vo.receivableLimit,vo.receive,vo.pay,vo.salesman,vo.points,vo.voucher);
	}
	
	public CustomerVO poTovo(CustomerPO po){
		return new CustomerVO(alterID(String.valueOf(po.getID())),CustomerCategory.categoryToString(po.getCategory()),Level.levelToString(po.getLevel()),po.getCustomerName(),po.getPhone(),po.getAddress(),po.getPostCode(),po.getMail(),po.getReceivableLimit(),po.getReceive(),po.getPay(),po.getSalesman(),po.getPoints(),po.getVoucher());
	}
	
	public ArrayList<UserVO> getAllSalesman() {
		return userInfo.getAllSalesmen();
	}
	
	public UserLimits getCurrentUserLimit() {
		return userInfo.findUserByID(userInfo.getCurrentUserID()).limit;
	}
}
