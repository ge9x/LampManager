package ui.viewcontroller.SalesStaff;

import java.io.IOException;
import java.util.ArrayList;

import bl.customerbl.CustomerController;
import blservice.customerblservice.CustomerBLService;
import blstubdriver.CustomerBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import vo.CustomerVO;

public class SalesStaffCustomerInfoViewController {

    @FXML
    Label addIcon;

    @FXML
    Label searchIcon;
    
    @FXML
    VBox customerList;
    
    @FXML
    ComboBox<String> Filters;
    
    @FXML
    TextField Search;
    
    private ArrayList<FXMLLoader> loaders = new ArrayList<FXMLLoader>();
    private ArrayList<HBox> cells = new ArrayList<HBox>();
    
    CustomerBLService customerBLService = new CustomerController();
    SalesStaffViewController salesStaffViewController;
    SalesStaffCustomerAddViewController salesStaffCustomerAddViewController;
    ArrayList<CustomerVO> customers;
    
    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        searchIcon.setText("\ue69d");
        
        Filters.getItems().addAll("编号", "关键字");
        
        customerList.setSpacing(30);
        
        showCustomerList();
    }
    
    public void showCustomerList(){
        customerList.getChildren().clear();
        cells.clear();
        loaders.clear();
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
    
    public void showCustomerDetail(Pane customerDetail){
    	salesStaffViewController.showCustomerDetail(customerDetail);
    }
    
    public void clickReturnButton(){
    	salesStaffViewController.showCustomerList();
    }
    
    public void clickAddButton(){
    	Pane customerAdd = null;
    	try{
	    	FXMLLoader customerAddLoader = new FXMLLoader();
	    	customerAddLoader.setLocation(getClass().getResource("/view/salesStaff/CustomerAdd.fxml"));
	        customerAdd = customerAddLoader.load();
	        
	        salesStaffCustomerAddViewController = customerAddLoader.getController();
	        salesStaffCustomerAddViewController.setSalesStaffCustomerInfoViewController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	salesStaffViewController.showCustomerAddView(customerAdd);
    }
    
    public void clickSearchButton(){
    	cells.clear();
    	loaders.clear();
    	ArrayList<CustomerVO> searchResult = new ArrayList<CustomerVO>();
    	String filters = Filters.getSelectionModel().getSelectedItem();
    	String keyword = Search.getText();
    	
    	if(filters.equals("编号")){
    		searchResult = customerBLService.findCustomerByCustomerID(keyword);
    	}
    	else if(filters.equals("关键字")){
    		searchResult = customerBLService.findCustomerByKeywords(keyword);
    	}
    	
    	customerList.getChildren().clear();
        
        for (int i = 0; i < searchResult.size(); i++){
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

        for (int i =0; i < searchResult.size(); i++){
            SalesStaffCustomerCellController customerCellController = loaders.get(i).getController();
            customerCellController.setCustomer(searchResult.get(i));
            customerCellController.setSalesStaffCustomerInfoViewController(this);
            customerList.getChildren().add(cells.get(i));
        }
    }
    
    public void clickDeleteButton(String customerID){
    	customerBLService.deleteCustomer(customerID);
    	salesStaffViewController.showCustomerList();
    }
}
