package ui.viewcontroller.SalesStaff;

import java.io.IOException;
import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import blstubdriver.CustomerBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vo.CustomerVO;

public class SalesStaffCustomerInfoViewController {

    @FXML
    Label addIcon;

    @FXML
    Label searchIcon;
    
    @FXML
    VBox customerList;
    
    private ArrayList<FXMLLoader> loaders = new ArrayList<FXMLLoader>();
    private ArrayList<HBox> cells = new ArrayList<HBox>();
    
    CustomerBLService customerBLService = new CustomerBLService_Stub();
    SalesStaffViewController salesStaffViewController;
    ArrayList<CustomerVO> customers;
    
    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        searchIcon.setText("\ue69d");
        
        customerList.setSpacing(30);
        
        showCustomerList();
    }
    
    public void showCustomerList(){
        customerList.getChildren().clear();
        customers = customerBLService.show();
        
        for (int i = 0; i < customers.size(); i++){
            try {
                FXMLLoader customerLoader = new FXMLLoader();
                customerLoader.setLocation(getClass().getResource("/view/salesStaff/CustomerCell.fxml"));
                HBox cell = customerLoader.load();
                loaders.add(customerLoader);
                cells.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i =0; i < customers.size(); i++){
            SalesStaffCustomerCellController customerCellController = loaders.get(i).getController();
            customerCellController.setCustomer(customers.get(i));
            customerCellController.setSalesStaffCustomerInfoViewController(this);
            customerList.getChildren().add(cells.get(i));
        }
    }
    
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
}
