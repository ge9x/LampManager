package ui.viewcontroller.FinancialStaff;

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
import javafx.scene.control.Label;
import vo.ProfitVO;

import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialProfitController {
    FinancialViewController financialViewController;
    FormBLService formBLService = new FormBLService_Stub();
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
            SalesIncome.setText(data.get(0).toString());
            OverflowIncome.setText(data.get(1).toString());
            CostAdjIncome.setText(data.get(2).toString());
            BuyAndReturnIncome.setText(data.get(3).toString());
            VoucherIncome.setText(data.get(4).toString());
            TotalIncome.setText(data.get(5).toString());
            Allowance.setText(data.get(6).toString());
            SalesExpense.setText(data.get(7).toString());
            LossExpense.setText(data.get(8).toString());
            GiftExpense.setText(data.get(9).toString());
            TotalExpense.setText(data.get(10).toString());
            Profit.setText(data.get(11).toString());
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

    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

}
