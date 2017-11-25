package ui.viewcontroller.Admin;

import java.util.ArrayList;

import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import vo.UserVO;

public class AdminUserViewController {

	@FXML
    Label addIcon;

    @FXML
    Label searchIcon;

    @FXML
    TilePane UserList;
    
    private ArrayList<FXMLLoader> loaders = new ArrayList<FXMLLoader>();
    private ArrayList<VBox> cells = new ArrayList<VBox>();
    
    UserBLService userBLService = new UserBLService_Stub();
    AdminViewController adminViewController;
    ArrayList<UserVO> users;
    
    @FXML
    public void initialize(){
    	
    }
}
