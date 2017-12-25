package org.ERPClient;
/**
 * Created by Kry·L on 2017/11/10.
 */

import com.sun.javafx.css.Style;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmi.*;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;



public class Main extends Application {
    Network network;

    MainUIController mainUIController;

    double xOffset;
    double yOffset;

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
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("/css/main.css").toString());

        //窗口拖动
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

//        无关闭按钮（为测试方便先注释掉了）
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

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
