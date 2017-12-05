package rmi;

import java.rmi.Remote;

import dataservice.classificationdataservice.ClassificationDataService;

/**
 * Created on 2017/12/4
 * @author 巽
 *
 */
public class ClassificationRemoteHelper {
    private Remote remote;
    private static ClassificationRemoteHelper classificationRemoteHelper = new ClassificationRemoteHelper();
    public static ClassificationRemoteHelper getInstance(){
        return classificationRemoteHelper;
    }
    private ClassificationRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public ClassificationDataService getClassificationDataService(){
        return (ClassificationDataService)remote;
    }
}
