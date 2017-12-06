package ui.viewcontroller.Admin;

import java.io.IOException;
import java.util.ArrayList;

import bl.userbl.UserController;
import blservice.userblservice.UserBLService;
import blstubdriver.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import ui.viewcontroller.SalesStaff.SalesStaffCustomerCellController;
import vo.CustomerVO;
import vo.UserVO;

public class AdminUserViewController {

	@FXML
    Label addIcon;

    @FXML
    Label searchIcon;

    @FXML
    TilePane UserList;
    
    @FXML
    ComboBox<String> Filters;
    
    @FXML
    TextField Search;
    
    private ArrayList<FXMLLoader> loaders = new ArrayList<FXMLLoader>();
    private ArrayList<VBox> cells = new ArrayList<VBox>();
    
    UserBLService userBLService = new UserController();
    AdminViewController adminViewController;
    AdminUserAddViewController adminUserAddViewController;
    ArrayList<UserVO> users = new ArrayList<>();
    
    @FXML
    public void initialize(){
    	addIcon.setText("\ue61e");
        searchIcon.setText("\ue69d");
        
        UserList.setHgap(95);
        UserList.setVgap(40);
        UserList.setPrefColumns(3);
        
        Filters.getItems().addAll("编号", "关键字");
    }
    
    public void setAdminViewController(AdminViewController adminViewController){
    	this.adminViewController = adminViewController;
    }
    
    public void showUserList(){
    	UserList.getChildren().clear();
        cells.clear();
        loaders.clear();
	    users = userBLService.show();
	        
	    for (int i = 0; i < users.size(); i++){
	        try {
	            FXMLLoader userLoader = new FXMLLoader();
	            userLoader.setLocation(getClass().getResource("/view/admin/UserCell.fxml"));
	            VBox cell = userLoader.load();
	            loaders.add(userLoader);
	            cells.add(cell);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	    for (int i =0; i < users.size(); i++){
	        AdminUserCellController userCellController = loaders.get(i).getController();
	        userCellController.setUser(users.get(i));
	        userCellController.setAdminUserViewController(this);
	        UserList.getChildren().add(cells.get(i));
	    }
    }
    
    public void showUserDetail(Pane userDetail){
    	adminViewController.showUserDetail(userDetail);
    }
    
    public void clickSearchButton(){
    	cells.clear();
    	loaders.clear();
    	ArrayList<UserVO> searchResult = new ArrayList<UserVO>();
    	String filters = Filters.getSelectionModel().getSelectedItem();
    	String keyword = Search.getText();
    	
    	if(filters.equals("编号")){
    		searchResult = userBLService.findUsersByID(keyword);
    	}
    	else if(filters.equals("关键字")){
    		searchResult = userBLService.findUsersByKeywords(keyword);
    	}
    	
    	UserList.getChildren().clear();
        
        for (int i = 0; i < searchResult.size(); i++){
            try {
                FXMLLoader customerLoader = new FXMLLoader();
                customerLoader.setLocation(getClass().getResource("/view/admin/UserCell.fxml"));
                VBox cell = customerLoader.load();
                loaders.add(customerLoader);
                cells.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i =0; i < searchResult.size(); i++){
            AdminUserCellController adminUserCellController = loaders.get(i).getController();
            adminUserCellController.setUser(searchResult.get(i));
            adminUserCellController.setAdminUserViewController(this);
            UserList.getChildren().add(cells.get(i));
        }
    }
    
    public void clickAddButton(){
    	Pane userAdd = null;
    	try{
	    	FXMLLoader userAddLoader = new FXMLLoader();
	    	userAddLoader.setLocation(getClass().getResource("/view/admin/UserAdd.fxml"));
	        userAdd = userAddLoader.load();
	        
	        adminUserAddViewController = userAddLoader.getController();
	        adminUserAddViewController.setAdminUserViewController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	adminViewController.showUserAddView(userAdd);
    }
    
    public void clickReturnButton(){
    	adminViewController.showUserView();
    }
    
    public void clickDeleteButton(UserVO user){
    	users.remove(user);
    	adminViewController.showUserView();
    }
}
