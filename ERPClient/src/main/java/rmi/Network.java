package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Kry·L on 2017/12/12.
 */
public class Network {

    private String ip;
    private int port;

    private AccountRemoteHelper accountRemoteHelper;
    private FinanceRemoteHelper financeRemoteHelper;
    private CustomerRemoteHelper customerRemoteHelper;
    private ClassificationRemoteHelper classificationRemoteHelper;
    private GoodsRemoteHelper goodsRemoteHelper;
    private InventoryRemoteHelper inventoryRemoteHelper;
    private PromotionRemoteHelper promotionRemoteHelper;
    private UserRemoteHelper userRemoteHelper;
    private SalesRemoteHelper salesRemoteHelper;
    private InitializationRemoteHelper initializationRemoteHelper;
    private LogRemoteHelper logRemoteHelper;
    private MessageRemoteHelper messageRemoteHelper;

    public Network(String ip,int port){
        accountRemoteHelper = AccountRemoteHelper.getInstance();
        financeRemoteHelper = FinanceRemoteHelper.getInstance();
        customerRemoteHelper=CustomerRemoteHelper.getInstance();
        classificationRemoteHelper = ClassificationRemoteHelper.getInstance();
        goodsRemoteHelper = GoodsRemoteHelper.getInstance();
        inventoryRemoteHelper = InventoryRemoteHelper.getInstance();
        promotionRemoteHelper = PromotionRemoteHelper.getInstance();
        userRemoteHelper = UserRemoteHelper.getInstance();
        salesRemoteHelper=SalesRemoteHelper.getInstance();
        initializationRemoteHelper = InitializationRemoteHelper.getInstance();
        logRemoteHelper = LogRemoteHelper.getInstance();
        messageRemoteHelper = MessageRemoteHelper.getInstance();

        this.ip = ip;
        this.port = port;
    }

    public void initNetwork() throws RemoteException, NotBoundException, MalformedURLException {
        String url = "rmi://"+ip+":"+port+"/";
        accountRemoteHelper.setRemote(Naming.lookup(url+"AccountDataRemoteObject"));
        financeRemoteHelper.setRemote(Naming.lookup(url+"FinanceDataRemoteObject"));
        customerRemoteHelper.setRemote(Naming.lookup(url+"CustomerDataRemoteObject"));
        classificationRemoteHelper.setRemote(Naming.lookup(url+"ClassificationDataRemoteObject"));
        goodsRemoteHelper.setRemote(Naming.lookup(url+"GoodsDataRemoteObject"));
        inventoryRemoteHelper.setRemote(Naming.lookup(url+"InventoryDataRemoteObject"));
        promotionRemoteHelper.setRemote(Naming.lookup(url+"PromotionDataRemoteObject"));
        userRemoteHelper.setRemote(Naming.lookup(url+"UserDataRemoteObject"));
        salesRemoteHelper.setRemote(Naming.lookup(url+"SalesDataRemoteObject"));
        initializationRemoteHelper.setRemote(Naming.lookup(url+"InitializationDataRemoteObject"));
        logRemoteHelper.setRemote(Naming.lookup(url + "LogDataRemoteObject"));
        messageRemoteHelper.setRemote(Naming.lookup(url+"MessageDataRemoteObject"));
    }



}
