package ui.viewcontroller.GeneralManager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;

public class GeneralManagerViewController {

	private GeneralManagerNavBarController generalManagerNavBarController;
	private GeneralManagerExaminationViewController generalManagerExaminationViewController;
	private MainUIController mainUIController;
	
    public GeneralManagerViewController(MainUIController mainUIController){
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navbarLoader = new FXMLLoader();
            navbarLoader.setLocation(getClass().getResource("/view/generalManager/GeneralManagerNavBar.fxml"));
            Pane navbar = navbarLoader.load();
            generalManagerNavBarController = navbarLoader.getController();
            generalManagerNavBarController.setGeneralManagerViewController(this);

            mainUIController.setLeft(navbar);

        } catch (IOException e) {
            e.printStackTrace();
        }

        generalManagerNavBarController.clickExaminationButton();

    }
    
    public void showExaminationView(){
    	mainUIController.setCenter(null);

        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/generalManager/Examination.fxml"));
            Pane page = pageLoader.load();
            generalManagerExaminationViewController = pageLoader.getController();
            generalManagerExaminationViewController.setGeneralManagerViewController(this);

            mainUIController.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        generalManagerExaminationViewController.showBillList();
    }
}
