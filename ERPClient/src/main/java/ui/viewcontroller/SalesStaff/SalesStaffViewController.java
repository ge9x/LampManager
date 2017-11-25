package ui.viewcontroller.SalesStaff;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;

public class SalesStaffViewController {

	private SalesStaffNavBarController salesStaffNavBarController;
	private SalesStaffCustomerInfoViewController salesStaffCustomerInfoController;
	private MainUIController mainUIController;
	
    public SalesStaffViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/salesStaff/SalesStaffNavBar.fxml"));
            Pane navbar = navbarLoader.load();
            salesStaffNavBarController = navbarLoader.getController();

            mainUIController.setLeft(navbar);

        } catch (IOException e) {
            e.printStackTrace();
        }

        salesStaffNavBarController.setSalesStaffViewController(this);
        salesStaffNavBarController.clickCustomerButton();

    }
    
    public void showCustomerList(){
    	mainUIController.setCenter(null);

        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/CustomerInfo.fxml"));
            Pane page = pageLoader.load();
            salesStaffCustomerInfoController = pageLoader.getController();
            salesStaffCustomerInfoController.setSalesStaffViewController(this);

            mainUIController.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        salesStaffCustomerInfoController.showCustomerList();
    }
    
    public void showCustomerDetail(Pane customerDetail){
    	mainUIController.setCenter(customerDetail);
    }
}
