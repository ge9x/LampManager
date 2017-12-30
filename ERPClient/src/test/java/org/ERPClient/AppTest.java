package org.ERPClient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import rmi.Network;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class AppTest{

    Network network;
    public AppTest(){
        linkToServer();
    }
    private void linkToServer() {
        try {
            network = new Network("127.0.0.1",8000);
            network.initNetwork();
            System.out.println("linked");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
