package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import ui.component.DialogFactory;
import util.Money;
import util.ResultMessage;
import vo.AccountVO;

import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/19.
 */
public class FinancialAccountCellController {

    @FXML
    Label BankName;

    @FXML
    Label DeleteIcon;

    @FXML
    Label EditIcon;

    @FXML
    Text Balance;

    @FXML
    Label BankIcon;
    AccountVO account;
    FinancialAccountController financialAccountController;
    @FXML
    public void initialize(){
        BankIcon.setText("\ue607");
        DeleteIcon.setText("\ue619");
        EditIcon.setText("\ue601");
    }

    public void setAccount(AccountVO account){
        this.account = account;
        BankName.setText(account.accountName);
        Balance.setText(Money.getMoneyString(account.money));
    }



    public void clickEditButton(){
        Dialog dialog = DialogFactory.getTextInputDialog();
        dialog.setHeaderText("请输入新的银行账户名称");
        Optional result = dialog.showAndWait();

        if (result.isPresent()) {
            String newName = (String)result.get();
            account.accountName = newName;
            financialAccountController.editAccount(account);
        }
    }
    public void clickDeleteButton(){
        Dialog alert = DialogFactory.getConfirmationAlert();
        alert.setHeaderText("确定要删除此账户？");
        Optional result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            financialAccountController.deleteAccount(account.accountID);
        }

    }
    public void setFinancialAccountController(FinancialAccountController financialAccountController){
        this.financialAccountController = financialAccountController;
    }

}
