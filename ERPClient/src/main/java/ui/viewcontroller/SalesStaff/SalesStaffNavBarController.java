package ui.viewcontroller.SalesStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SalesStaffNavBarController {
	
	@FXML
	Label CustomerIcon;
	
	@FXML
	Label PurchaseOrderIcon;
	
	@FXML
	Label ReturnOrderIcon;
	
	@FXML
	Label SalesIcon;
	
	@FXML
	Label SalesReturnIcon;
	
    @FXML
    public void initialize() {
        CustomerIcon.setText("\ue634");
        PurchaseOrderIcon.setText("\ue689");
        ReturnOrderIcon.setText("\ue67f");
        SalesIcon.setText("\ue693");
        SalesReturnIcon.setText("\ue6e5");
    }

    public void clickCustomerButton(){

    }

}
