package org.ERPServer;


import java.io.IOException;

import com.sun.javafx.css.StyleManager;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmi.RemoteHelper;
import ui.common.ServerUIController;

public class Server extends Application{
	ServerUIController serverUIController;
	double xOffset;
    double yOffset;
	
    public Server(){
//        new RemoteHelper();
    }
    public static void main( String[] args ) {
    	launch(args);
//        new Server();
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane root = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/serverUI.fxml"));
            root = loader.load();
            serverUIController = loader.getController();
            serverUIController.setStage(primaryStage);

        }catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);

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

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
	}
}
