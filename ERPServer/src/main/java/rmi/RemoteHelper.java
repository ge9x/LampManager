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
        try {
            accountDataRemoteObject = new AccountDataRemoteObject();
            financeDataRemoteObject = new FinanceDataRemoteObject();
            LocateRegistry.createRegistry(8080);
            Naming.bind("rmi://127.0.0.1:8080/AccountDataRemoteObject",
                    accountDataRemoteObject);
            Naming.bind("rmi://127.0.0.1:8080/FinanceDataRemoteObject",financeDataRemoteObject);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
