package ui.viewcontroller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminNavBarController {

	@FXML
	Label UserIcon;
	
	AdminViewController adminViewController;
	
    @FXML
    public void initialize() {
        UserIcon.setText("\ue610");
    }
    
    public void setAdminViewController(AdminViewController adminViewController){
    	this.adminViewController = adminViewController;
    }

    public void clickUserButton(){
    	adminViewController.showUserView();
    }
}
