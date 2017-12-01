package org.ERPClient;
/**
 * Created by Kry·L on 2017/11/10.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmi.AccountRemoteHelper;
import rmi.FinanceRemoteHelper;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main extends Application {
    private AccountRemoteHelper accountRemoteHelper;
    private FinanceRemoteHelper financeRemoteHelper;

    MainUIController mainUIController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        linkToServer();

        Pane root = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/common/mainView.fxml"));
            root = loader.load();
            mainUIController = loader.getController();
            mainUIController.setStage(primaryStage);
            mainUIController.showLogin();

        }catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

//        无关闭按钮（为测试方便先注释掉了）
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }

    private void linkToServer() {
        try {
            accountRemoteHelper = AccountRemoteHelper.getInstance();
            financeRemoteHelper = FinanceRemoteHelper.getInstance();
            accountRemoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8080/AccountDataRemoteObject"));
            financeRemoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8080/FinanceDataRemoteObject"));
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
