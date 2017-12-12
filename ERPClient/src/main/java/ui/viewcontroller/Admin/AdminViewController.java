package ui.viewcontroller.Admin;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;

public class AdminViewController {
	private AdminNavBarController adminNavBarController;
	private AdminUserViewController adminUserViewController;
	private MainUIController mainUIController;
	
    public AdminViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/admin/AdminNavBar.fxml"));
            Pane navbar = navbarLoader.load();
            adminNavBarController = navbarLoader.getController();
            adminNavBarController.setAdminViewController(this);

            mainUIController.setLeft(navbar);

        } catch (IOException e) {
            e.printStackTrace();
        }

        adminNavBarController.clickUserButton();

    }
    
    public void showUserView(){
    	mainUIController.setCenter(null);

        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/admin/User.fxml"));
            Pane page = pageLoader.load();
            adminUserViewController = pageLoader.getController();
            adminUserViewController.setAdminViewController(this);

            mainUIController.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        adminUserViewController.showUserList();
    }
    
    public void showUserDetail(Pane userDetail){
    	mainUIController.setCenter(null);
        mainUIController.setCenter(userDetail);
    }
    
    public void showUserAddView(Pane userAdd){
    	mainUIController.setCenter(null);
        mainUIController.setCenter(userAdd);
    }
}
