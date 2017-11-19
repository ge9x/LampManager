package org.ERPClient;
/**
 * Created by Kry·L on 2017/11/10.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.viewcontroller.common.LoginViewController;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

public class Main extends Application {

    MainUIController mainUIController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
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
}
