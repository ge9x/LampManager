package ui.viewcontroller.common;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/14.
 */
public class MainUIController {

    @FXML
    BorderPane borderPane;

    UserBLService userBLService = new UserBLService_Stub();
    public MainUIController() {

    }
    public void showFinancialStaffView(){

    }


    public void showLogin(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/common/Login.fxml"));
            Node root = loader.load();
            borderPane.setPrefHeight(500);
            borderPane.setPrefWidth(828);
            setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCenter(Node node){
        borderPane.setCenter(node);
    }

    public void setLeft(Node node){
        borderPane.setLeft(node);
    }


}
