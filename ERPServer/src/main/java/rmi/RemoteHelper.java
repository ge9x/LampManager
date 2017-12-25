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
        PromotionDataRemoteObject promotionDataRemoteObject;
        UserDataRemoteObject userDataRemoteObject;
        SalesDataRemoteObject salesDataRemoteObject;
        InitializationDataRemoteObject initializationDataRemoteObject;
        LogDataRemoteObject logDataRemoteObject;
        
        try {
            accountDataRemoteObject = new AccountDataRemoteObject();
            financeDataRemoteObject = new FinanceDataRemoteObject();
            customerDataRemoteObject=new CustomerDataRemoteObject();
            classificationDataRemoteObject=new ClassificationDataRemoteObject();
            goodsDataRemoteObject=new GoodsDataRemoteObject();
            inventoryDataRemoteObject=new InventoryDataRemoteObject();
            promotionDataRemoteObject = new PromotionDataRemoteObject();
            userDataRemoteObject = new UserDataRemoteObject();
            salesDataRemoteObject=new SalesDataRemoteObject();
            initializationDataRemoteObject = new InitializationDataRemoteObject();
            logDataRemoteObject = new LogDataRemoteObject();

            String ip = "127.0.0.1";
            int port = 8000;

            LocateRegistry.createRegistry(port);

            String url = ip + ":"+port;
            Naming.bind("rmi://"+url+"/AccountDataRemoteObject",
                    accountDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/FinanceDataRemoteObject",financeDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/CustomerDataRemoteObject", customerDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/ClassificationDataRemoteObject", classificationDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/GoodsDataRemoteObject", goodsDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/InventoryDataRemoteObject", inventoryDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/PromotionDataRemoteObject", promotionDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/UserDataRemoteObject", userDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/SalesDataRemoteObject", salesDataRemoteObject);
            Naming.bind("rmi://" + url + "/InitializationDataRemoteObject", initializationDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8000/LogDataRemoteObject", logDataRemoteObject);

            System.out.println("start successfully");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
