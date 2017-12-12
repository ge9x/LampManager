package rmi;

import java.rmi.Remote;

import dataservice.customerdataservice.CustomerDataService;

/**
 * Created by zlk on 2017-12-2
 */

public class CustomerRemoteHelper {
	 private Remote remote;
	 private static CustomerRemoteHelper customerRemoteHelper=new CustomerRemoteHelper();
	 public static CustomerRemoteHelper getInstance(){
		 return customerRemoteHelper;
	 }
	 private CustomerRemoteHelper(){
		 
	 }
	 public void setRemote(Remote remote){
	        this.remote = remote;
	    }
	 public CustomerDataService getCustomerDataService(){
		 return (CustomerDataService)remote;
	 }
}
