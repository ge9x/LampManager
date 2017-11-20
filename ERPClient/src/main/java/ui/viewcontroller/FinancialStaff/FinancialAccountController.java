package ui.viewcontroller.FinancialStaff;

import blservice.accountblservice.AccountBLService;
import blstubdriver.AccountBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import ui.component.DialogFactory;
import util.ResultMessage;
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
        accountList.getChildren().clear();
        accounts = accountBLService.show();

        for (int i =0; i < accounts.size(); i++){
            FinancialAccountCellController accountCellController = loaders[i].getController();
            accountCellController.setAccount(accounts.get(i));
            accountCellController.setFinancialAccountController(this);
            accountList.getChildren().add(cells[i]);
        }
    }

    public void deleteAccount(String ID){
        Dialog alert = DialogFactory.getConfirmationAlert();
        alert.setHeaderText("确定要删除此账户？");
        Optional result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            accountBLService.deleteAccount(ID);
            showAccountList();
        }
    }
    public void editAccount(String ID){
        Dialog dialog = DialogFactory.getTextInputDialog();
        dialog.setHeaderText("请输入新的银行账户名称");
        Optional result = dialog.showAndWait();

        if (result.isPresent()) {
            String newName = (String)result.get();
            AccountVO account = findVOByID(ID);
            account.accountName = newName;
            ResultMessage re = accountBLService.updateAccount(account);
            if (re == ResultMessage.SUCCESS){
                showAccountList();
            }
        }

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
