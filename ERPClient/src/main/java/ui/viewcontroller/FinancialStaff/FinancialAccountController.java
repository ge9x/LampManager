package ui.viewcontroller.FinancialStaff;

import blservice.accountblservice.AccountBLService;
import dataservice.accountdataservice.AccountDataService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/14.
 */
public class FinancialAccountController {
    @FXML
    Label addIcon;

    @FXML
    Label searchIcon;

    @FXML
    TilePane accountList;

    private static final int NUM_OF_CELLS = 6;
    private AccountBLService accountBLService;
    private ArrayList<AccountVO> accunts;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        searchIcon.setText("\ue69d");

        accountList.setHgap(50);
        accountList.setVgap(20);
        accountList.setPrefColumns(2);
    }

    public void showAccountList(){

    }
}
