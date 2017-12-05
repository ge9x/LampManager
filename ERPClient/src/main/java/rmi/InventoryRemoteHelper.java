package rmi;

import java.rmi.Remote;

import dataservice.inventorydataservice.InventoryDataService;

/**
 * Created on 2017/12/4
 * @author 巽
 *
 */
public class InventoryRemoteHelper {
    private Remote remote;
    private static InventoryRemoteHelper inventoryionRemoteHelper = new InventoryRemoteHelper();
    public static InventoryRemoteHelper getInstance(){
        return inventoryionRemoteHelper;
    }
    private InventoryRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public InventoryDataService getInventoryDataService(){
        return (InventoryDataService)remote;
    }
}
