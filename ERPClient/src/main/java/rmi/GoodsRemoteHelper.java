package rmi;

import java.rmi.Remote;

import dataservice.goodsdataservice.GoodsDataService;

/**
 * Created on 2017/12/4
 * @author 巽
 *
 */
public class GoodsRemoteHelper {
    private Remote remote;
    private static GoodsRemoteHelper goodsRemoteHelper = new GoodsRemoteHelper();
    public static GoodsRemoteHelper getInstance(){
        return goodsRemoteHelper;
    }
    private GoodsRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public GoodsDataService getGoodsDataService(){
        return (GoodsDataService)remote;
    }
}
