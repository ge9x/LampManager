package org.ERPServer;


import rmi.RemoteHelper;

public class Server{
    public Server(){
        new RemoteHelper();
    }
    public static void main( String[] args ) {
        new Server();
    }
}
