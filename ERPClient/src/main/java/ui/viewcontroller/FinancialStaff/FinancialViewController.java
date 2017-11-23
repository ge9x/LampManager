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
    private FinancialReceiptController financialReceiptController;
    private MainUIController mainUIController;

    public FinancialViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/financialStaff/FinancialStaffNavbar.fxml"));
            Pane navbar = navbarLoader.load();
            financialNavbarController = navbarLoader.getController();
            financialNavbarController.setFinancialViewController(this);

            mainUIController.setLeft(navbar);
        }catch (IOException e){
                e.printStackTrace();
        }
        financialNavbarController.clickAccountButton();

    }
    public void showAccountView(){

        mainUIController.setCenter(null);

        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/Account.fxml"));
            Pane page = pageLoader.load();
            financialAccountController = pageLoader.getController();
            financialAccountController.setFinancialViewController(this);

            mainUIController.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        financialAccountController.showAccountList();
    }
    public void showReceiptView(){
        mainUIController.setCenter(null);

        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/Receipt.fxml"));
            Pane page = pageLoader.load();
            financialReceiptController = pageLoader.getController();
            financialReceiptController.setFinancialViewController(this);

            mainUIController.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        financialReceiptController.showReceiptList();
    }

}
