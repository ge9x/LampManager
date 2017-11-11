package org.ERPClient;
/**
 * Created by Kry·L on 2017/11/10.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/common/Login.fxml"));
        Pane root = loader.load();

        primaryStage.setTitle("Lamp Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

//        无关闭按钮（为测试方便先注释掉了）
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }
}
