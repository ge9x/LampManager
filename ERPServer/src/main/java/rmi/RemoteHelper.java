package rmi;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kry·L on 2017/11/29.
 */
public class RemoteHelper {
	private static Registry registry;
	
    public RemoteHelper(int port){
        initServer(port);
    }
    public void initServer(int port){
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
        MessageDataRemoteObject messageDataRemoteObject;
        
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
            messageDataRemoteObject = new MessageDataRemoteObject();

//            String ip = "127.0.0.1";
            String ip = "";
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//            int port = 8000;

            registry = LocateRegistry.createRegistry(port);

            String url = ip + ":"+port;
            Naming.bind("rmi://"+url+"/AccountDataRemoteObject",
                    accountDataRemoteObject);
            Naming.bind("rmi://"+url+"/FinanceDataRemoteObject",financeDataRemoteObject);
            Naming.bind("rmi://"+url+"/CustomerDataRemoteObject", customerDataRemoteObject);
            Naming.bind("rmi://"+url+"/ClassificationDataRemoteObject", classificationDataRemoteObject);
            Naming.bind("rmi://"+url+"/GoodsDataRemoteObject", goodsDataRemoteObject);
            Naming.bind("rmi://"+url+"/InventoryDataRemoteObject", inventoryDataRemoteObject);
            Naming.bind("rmi://"+url+"/PromotionDataRemoteObject", promotionDataRemoteObject);
            Naming.bind("rmi://"+url+"/UserDataRemoteObject", userDataRemoteObject);
            Naming.bind("rmi://"+url+"/SalesDataRemoteObject", salesDataRemoteObject);
            Naming.bind("rmi://" + url + "/InitializationDataRemoteObject", initializationDataRemoteObject);
            Naming.bind("rmi://"+url+"/LogDataRemoteObject", logDataRemoteObject);
            Naming.bind("rmi://"+url+"/MessageDataRemoteObject", messageDataRemoteObject);

            System.out.println("start successfully");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
    
    public static void disableNetwork() {
    	try {
			UnicastRemoteObject.unexportObject(registry, true);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}
