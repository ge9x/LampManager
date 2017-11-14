package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/14.
 */
public class FinancialViewController {

    private FinancialNavbarController financialNavbarController;
    public FinancialViewController(){
        try {

            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/financialStaff/FinancialStaffNavbar.fxml"));
            Pane navbar = navbarLoader.load();

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/FinancialStaffPage"));
            BorderPane borderPane = pageLoader.load();

            borderPane.setLeft(navbar);


        } catch (IOException e) {
            e.printStackTrace();
        }

        financialNavbarController.clickAccountButton();

    }

}
