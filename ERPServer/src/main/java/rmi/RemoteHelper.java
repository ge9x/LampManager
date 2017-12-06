package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Kry·L on 2017/11/29.
 */
public class RemoteHelper {
    public RemoteHelper(){
        initServer();
    }
    public void initServer(){
        AccountDataRemoteObject accountDataRemoteObject;
        FinanceDataRemoteObject financeDataRemoteObject;
        CustomerDataRemoteObject customerDataRemoteObject;
        ClassificationDataRemoteObject classificationDataRemoteObject;
        GoodsDataRemoteObject goodsDataRemoteObject;
        InventoryDataRemoteObject inventoryDataRemoteObject;
        UserDataRemoteObject userDataRemoteObject;
        SalesDataRemoteObject salesDataRemoteObject;
        
        try {
            accountDataRemoteObject = new AccountDataRemoteObject();
            financeDataRemoteObject = new FinanceDataRemoteObject();
            customerDataRemoteObject=new CustomerDataRemoteObject();
            classificationDataRemoteObject=new ClassificationDataRemoteObject();
            goodsDataRemoteObject=new GoodsDataRemoteObject();
            inventoryDataRemoteObject=new InventoryDataRemoteObject();
            userDataRemoteObject = new UserDataRemoteObject();
            salesDataRemoteObject=new SalesDataRemoteObject();
            
            LocateRegistry.createRegistry(8080);
            Naming.bind("rmi://127.0.0.1:8080/AccountDataRemoteObject",
                    accountDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/FinanceDataRemoteObject",financeDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/CustomerDataRemoteObject", customerDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/ClassificationDataRemoteObject", classificationDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/GoodsDataRemoteObject", goodsDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/InventoryDataRemoteObject", inventoryDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/UserDataRemoteObject", userDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/SalesDataRemoteObject", salesDataRemoteObject);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
