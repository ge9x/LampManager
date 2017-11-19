package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/14.
 */
public class FinancialViewController {

    private FinancialNavbarController financialNavbarController;
    private FinancialAccountController financialAccountController;
    private MainUIController mainUIController;

    public FinancialViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/financialStaff/FinancialStaffNavbar.fxml"));
            Pane navbar = navbarLoader.load();
            financialNavbarController = navbarLoader.getController();

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/Account.fxml"));
            Pane page = pageLoader.load();
            financialAccountController = pageLoader.getController();

            mainUIController.setLeft(navbar);
            mainUIController.setCenter(page);

        } catch (IOException e) {
            e.printStackTrace();
        }

        financialNavbarController.clickAccountButton();

    }

}
