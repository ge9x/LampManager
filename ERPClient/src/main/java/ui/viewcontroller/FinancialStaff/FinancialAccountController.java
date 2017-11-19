package ui.viewcontroller.FinancialStaff;

import blservice.accountblservice.AccountBLService;
import blstubdriver.AccountBLService_Stub;
import dataservice.accountdataservice.AccountDataService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import vo.AccountVO;

import java.io.IOException;
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
    private FXMLLoader[] loaders = new FXMLLoader[NUM_OF_CELLS];
    private VBox[] cells = new VBox[NUM_OF_CELLS];
    AccountBLService accountBLService = new AccountBLService_Stub();
    ArrayList<AccountVO> accounts;


    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
        searchIcon.setText("\ue69d");

        accountList.setHgap(50);
        accountList.setVgap(30);
        accountList.setPrefColumns(2);


        for (int i = 0; i < NUM_OF_CELLS; i++){
            try {
                FXMLLoader accountLoader = new FXMLLoader();
                accountLoader.setLocation(getClass().getResource("/view/financialStaff/AccountCell.fxml"));
                VBox cell = accountLoader.load();
                loaders[i] = accountLoader;
                cells[i] = cell;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showAccountList();
    }

    public void showAccountList(){
        accounts = accountBLService.show();

        for (int i =0; i < accounts.size(); i++){
            FinancialAccountCellController accountCellController = loaders[i].getController();
            accountCellController.setAccount(accounts.get(i));
            accountList.getChildren().add(cells[i]);
        }
    }

}
