package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import vo.AccountVO;

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

    @FXML
    public void initialize(){
        BankIcon.setText("\ue607");
        DeleteIcon.setText("\ue619");
        EditIcon.setText("\ue601");
    }

    public void setAccount(AccountVO account){
        BankName.setText(account.accountName);
        Balance.setText(getMoneyString(account.money));
    }

    String getMoneyString(double money){
        String raw = String.valueOf(money);
        if (!raw.contains(".")){
            raw = raw + ".00";
        }
        if ((raw.length() - raw.lastIndexOf(".")) < 3){
            for (int i = 0; i < 3-(raw.length() - raw.lastIndexOf(".")); i++){
                raw = raw + "0";
            }
        }
        for (int i = raw.lastIndexOf(".")-3; i >= 0; i=i-3){
            raw = raw.substring(0,i) + "," + raw.substring(i);
        }
        return "￥"+raw;
    }

    public void clickEditButton(){

    }
    public void clickDeleteButton(){

    }
}
