package ui.viewcontroller.FinancialStaff;

import blservice.accountblservice.AccountBLService;
import blstubdriver.AccountBLService_Stub;
import dataservice.accountdataservice.AccountDataService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import vo.AccountVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
    FinancialViewController financialViewController;
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
            accountCellController.setFinancialAccountController(this);
            accountList.getChildren().add(cells[i]);
        }
    }

    public void deleteAccount(String name){

    }
    public void editAccount(String ID){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Lamp Manager");
        dialog.setHeaderText("请输入新的银行账户名称");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setId("CancelButton");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/css/textInput.css").toExternalForm());
        Optional result = dialog.showAndWait();


        String newName = (String)result.get();
//        accountBLService.updateAccount();
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }
    private AccountVO findVOByID(String ID){
        for (AccountVO account:accounts){
            if (account.accountID == ID)
                return account;
        }
        return null;
    }
}
