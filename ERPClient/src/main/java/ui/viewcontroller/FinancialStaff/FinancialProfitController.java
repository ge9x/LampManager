package ui.viewcontroller.FinancialStaff;

import bl.formbl.FormBLFactory;
import bl.formbl.FormController;
import blservice.formblservice.FormBLService;
import blstubdriver.FormBLService_Stub;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.component.DialogFactory;
import util.Money;
import util.ResultMessage;
import vo.ProfitVO;

import javax.swing.event.ChangeListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialProfitController {
    FinancialViewController financialViewController;
    FormBLService formBLService = FormBLFactory.getBLService();
    ProfitVO profitVO;
    ObservableList<Double> data = FXCollections.observableArrayList();

    @FXML
    Label SalesIncome;

    @FXML
    Label OverflowIncome;

    @FXML
    Label CostAdjIncome;

    @FXML
    Label BuyAndReturnIncome;

    @FXML
    Label VoucherIncome;

    @FXML
    Label SalesExpense;

    @FXML
    Label LossExpense;

    @FXML
    Label GiftExpense;

    @FXML
    Label TotalIncome;

    @FXML
    Label Allowance;

    @FXML
    Label TotalExpense;

    @FXML
    Label Profit;

    @FXML
    JFXDatePicker StartDate;

    @FXML
    JFXDatePicker EndDate;

    @FXML
    public void initialize(){
        StartDate.setValue(LocalDate.now());
        StartDate.valueProperty().addListener(new javafx.beans.value.ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showProfit();
            }
        });
        EndDate.valueProperty().addListener(new javafx.beans.value.ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showProfit();
            }
        });
        EndDate.setValue(LocalDate.now());
        showProfit();
        showData();

        data.addListener(new ListChangeListener<Double>() {
            @Override
            public void onChanged(Change<? extends Double> c) {
                showData();
            }
        });

    }
    public void showData(){
        if (!data.isEmpty()){
            SalesIncome.setText(Money.getMoneyString(data.get(0)));
            OverflowIncome.setText(Money.getMoneyString(data.get(1)));
            CostAdjIncome.setText(Money.getMoneyString(data.get(2)));
            BuyAndReturnIncome.setText(Money.getMoneyString(data.get(3)));
            VoucherIncome.setText(Money.getMoneyString(data.get(4)));
            TotalIncome.setText("总收入: "+Money.getMoneyString(data.get(5)));
            Allowance.setText("折让: "+Money.getMoneyString(data.get(6)));
            SalesExpense.setText(Money.getMoneyString(data.get(7)));
            LossExpense.setText(Money.getMoneyString(data.get(8)));
            GiftExpense.setText(Money.getMoneyString(data.get(9)));
            TotalExpense.setText("总支出: "+Money.getMoneyString(data.get(10)));
            Profit.setText("利润: " + Money.getMoneyString(data.get(11)));

            profitVO = new ProfitVO(StartDate.getValue().toString(),EndDate.getValue().toString(),data.get(0),data.get(1),data.get(2),
                    data.get(3),data.get(4),data.get(5),data.get(6),data.get(7),data.get(8),data.get(9),data.get(10),data.get(11));
        }
    }
    public void showProfit(){
        data.clear();
        profitVO = formBLService.getProfit(StartDate.getValue().toString(), EndDate.getValue().toString());
        data.addAll(profitVO.salesIncome, profitVO.overflowIncome,profitVO.costAdjIncome,
                profitVO.buyAndReturnIncome,profitVO.voucherIncome,profitVO.totalIncome,
                profitVO.allowance,profitVO.salescost,profitVO.lossExpense,
                profitVO.giftExpense,profitVO.totalExpense,profitVO.profit);
    }
    public void clickExportButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出经营情况表");
        fileChooser.setInitialFileName("经营情况表"+LocalDate.now().toString());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel表格", "*.xlxs"));
        File f = fileChooser.showSaveDialog(new Stage());

        if (f != null) {
            ArrayList<ProfitVO> profitVOS = new ArrayList<>();
            profitVOS.add(profitVO);
            ResultMessage re = formBLService.exportProfit(f.getParent(), f.getName(), profitVOS);
            if (re == ResultMessage.SUCCESS) {
                Dialog alert = DialogFactory.getInformationAlert();
                alert.setHeaderText("导出成功");
                alert.show();
            }
        }
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

}
