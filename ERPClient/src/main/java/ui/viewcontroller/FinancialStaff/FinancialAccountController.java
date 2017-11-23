package ui.viewcontroller.FinancialStaff;

import blservice.accountblservice.AccountBLService;
import blstubdriver.AccountBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import javafx.util.Pair;
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

    @FXML
    TextField keywordInput;

    private ArrayList<FXMLLoader> loaders = new ArrayList<>();
    private ArrayList<VBox> cells = new ArrayList<>();

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

        showAccountList();
    }

    public void showAccountList(){
        accountList.getChildren().clear();
        accounts = accountBLService.show();
        for (int i = 0; i < accounts.size(); i++){
            try {
                FXMLLoader accountLoader = new FXMLLoader();
                accountLoader.setLocation(getClass().getResource("/view/financialStaff/AccountCell.fxml"));
                VBox cell = accountLoader.load();
                loaders.add(accountLoader);
                cells.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i =0; i < accounts.size(); i++){
            FinancialAccountCellController accountCellController = loaders.get(i).getController();
            accountCellController.setAccount(accounts.get(i));
            accountCellController.setFinancialAccountController(this);
            accountList.getChildren().add(cells.get(i));
        }
    }
    public void addAccount(){
        Dialog dialog = DialogFactory.getDoubleTextDialog("账户名称","账户余额");
        dialog.setHeaderText("请输入新建账户信息");
        Optional<Pair<String,String>> result = dialog.showAndWait();

        if (result.isPresent()){
            String accountName = null;
            String money = null;
            Pair<String,String> pair = result.get();
            AccountVO account = new AccountVO(null,pair.getKey(),Double.parseDouble(pair.getValue()));
            ResultMessage re = accountBLService.addAccount(account);
            if (re == ResultMessage.SUCCESS){
                showAccountList();
            }
        }
    }
    public void deleteAccount(String ID){
            ResultMessage re = accountBLService.deleteAccount(ID);
        if (re == ResultMessage.SUCCESS){
            showAccountList();
        }
    }
    public void editAccount(AccountVO vo){
        ResultMessage re = accountBLService.updateAccount(vo);
        if (re == ResultMessage.SUCCESS){
            showAccountList();
        }
    }

    public void searchAccount(String keyword){
        accounts = accountBLService.findAccountByName(keyword);
        showAccountList();
    }

    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }


    public void clickAddButton(){
        addAccount();
    }
    public void clickSearchButton(){
        String keyword = keywordInput.getText();
        searchAccount(keyword);
    }
}
