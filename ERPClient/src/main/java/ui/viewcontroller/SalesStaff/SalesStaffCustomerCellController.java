package ui.viewcontroller.SalesStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SalesStaffCustomerCellController {

	@FXML
	Label CustomerIcon;
	
	@FXML
	Label LevelIcon;
	
	@FXML
	Label CustomerName;
	
	@FXML
	Label CustomerID;
	
	@FXML
	Label CustomerType;
	
	@FXML
	Label Level;
	
	@FXML
	Label DetailIcon;
	
    @FXML
    public void initialize(){
        CustomerIcon.setText("\ue671");
        DetailIcon.setText("\ue601");
    }
}
