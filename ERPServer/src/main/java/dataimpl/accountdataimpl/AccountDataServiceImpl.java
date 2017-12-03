package dataimpl.accountdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.accountdataservice.AccountDataService;
import po.AccountPO;
import util.ResultMessage;

/**
 * Cteated on 2017/11/29
 * @author 巽
 *
 */
public class AccountDataServiceImpl implements AccountDataService{
	private static AccountDataServiceImpl accountDataServiceImpl;
	private DataHelper<AccountPO> accountDataHelper;
	
	public static AccountDataServiceImpl getInstance(){
		if(accountDataServiceImpl == null){
			accountDataServiceImpl = new AccountDataServiceImpl();
		}
		return accountDataServiceImpl;
	}
	
	private AccountDataServiceImpl(){
		accountDataHelper = new HibernateDataHelper<AccountPO>(AccountPO.class);
	}

	@Override
	public ResultMessage addAccount(AccountPO po) throws RemoteException {
		return accountDataHelper.save(po);
	}

	@Override
	public ResultMessage deleteAccount(AccountPO po) throws RemoteException {
		return accountDataHelper.delete(po);
	}

	@Override
	public ResultMessage updateAccount(AccountPO po) throws RemoteException {
		return accountDataHelper.update(po);
	}

	@Override
	public ArrayList<AccountPO> findByName(String keyword) throws RemoteException {
		return accountDataHelper.fuzzyQuery("name", keyword);
	}

	@Override
	public ArrayList<AccountPO> show() throws RemoteException {
		return accountDataHelper.fullyQuery(null, null);
	}
}
