package ui.viewcontroller.common;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.viewcontroller.Admin.AdminViewController;
import ui.viewcontroller.FinancialStaff.FinancialViewController;
import ui.viewcontroller.GeneralManager.GeneralManagerViewController;
import ui.viewcontroller.InventoryStaff.InventoryViewController;
import ui.viewcontroller.SalesStaff.SalesStaffViewController;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/14.
 */
public class MainUIController {

    @FXML
    BorderPane borderPane;


    UserBLService userBLService = new UserBLService_Stub();
    Stage primaryStage;

    public MainUIController(){
    }

    public void showFinancialStaffView(){
        resizeToPage();
        showStateBar();
        FinancialViewController financialViewController = new FinancialViewController(this);
    }
    public void showSalesStaffView(){
        resizeToPage();
        showStateBar();
        SalesStaffViewController salesStaffViewController = new SalesStaffViewController(this);
    }
    public void showGeneralManagerView(){
        resizeToPage();
        showStateBar();
        GeneralManagerViewController generalManagerViewController = new GeneralManagerViewController(this);
    }
    public void showAdminView(){
        resizeToPage();
        showStateBar();
        AdminViewController adminViewController = new AdminViewController(this);
    }
    public void showInventoryView(){
        resizeToPage();
        InventoryViewController inventoryViewController = new InventoryViewController(this);
    }



    public void showLogin(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/common/Login.fxml"));
            Node root = loader.load();

            LoginViewController loginViewController = loader.getController();
            loginViewController.setMainUIController(this);

//            resizeToLogin();
            setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将界面大小调整为适合登录界面
     */
    public void resizeToLogin(){

        borderPane.setMinHeight(500);
        borderPane.setMinWidth(828);


    }

    /**
     * 将界面大小调整为适合工作页界面
     */
    public void resizeToPage(){
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(1000);
    }

    public void showStateBar(){
        try {
            FXMLLoader stateBarLoader = new FXMLLoader();
            stateBarLoader.setLocation(getClass().getResource("/view/common/StateBar.fxml"));
            Pane stateBar = stateBarLoader.load();
            StateBarController stateBarController = stateBarLoader.getController();
            stateBarController.setMainUIController(this);


            setTop(stateBar);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void close(){
        primaryStage.close();
    }
    public void setCenter(Node node){
        borderPane.setCenter(node);
    }

    public void setLeft(Node node){
        borderPane.setLeft(node);
    }

    public void setTop(Node node){
        borderPane.setTop(node);
    }

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

}
