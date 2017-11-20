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

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/admin/User.fxml"));
            Pane page = pageLoader.load();
            adminUserViewController = pageLoader.getController();

            mainUIController.setLeft(navbar);
            mainUIController.setCenter(page);

        } catch (IOException e) {
            e.printStackTrace();
        }

        adminNavBarController.clickUserButton();

    }
}
