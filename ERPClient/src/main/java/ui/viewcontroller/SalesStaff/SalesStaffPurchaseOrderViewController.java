package ui.viewcontroller.SalesStaff;

import blservice.salesblservice.SalesBLService;
import blstubdriver.SalesBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.component.BillPane;
import vo.SalesVO;

import java.io.IOException;
import java.util.ArrayList;

public class SalesStaffPurchaseOrderViewController {
	
	SalesStaffViewController salesStaffViewController;
    SalesStaffPurchaseEditViewController salesStaffPurchaseEditViewController;

    SalesBLService salesBLService = new SalesBLService_Stub();

    ArrayList<SalesVO> draft;
    ArrayList<SalesVO> submitted;
    ArrayList<SalesVO> pass;
    ArrayList<SalesVO> failed;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    BillPane billPane;

    @FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");

        draft = salesBLService;
        submitted = salesBLService;
        pass = salesBLService;
        failed = salesBLService;

    }
    
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showPurchaseOrderList(){
    	salesStaffViewController.showPurchaseOrderView();
    }
    
    public void clickAddButton(){
    	showPurchaseEditView();
    	salesStaffPurchaseEditViewController.addPurchaseOrder();
    }
    
    public void showPurchaseEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/PurchaseOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffPurchaseEditViewController = pageLoader.getController();
            salesStaffPurchaseEditViewController.setSalesStaffPurchaseOrderViewController(this);
            salesStaffViewController.showPurchaseOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
