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

//    ProfitBean profitBean;
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
//    public class ProfitBean {
//        /**
//         * 销售收入
//         */
//        public DoubleProperty salesIncome;
//
//        /**
//         * 商品报溢收入
//         */
//        public DoubleProperty overflowIncome;
//
//        /**
//         * 成本调价收入
//         */
//        public DoubleProperty costAdjIncome;
//
//        /**
//         * 进货退货差价
//         */
//        public DoubleProperty buyAndReturnIncome;
//
//        /**
//         * 代金券与实际收款差额收入
//         */
//        public DoubleProperty voucherIncome;
//
//        /**
//         * 折让后总收入
//         */
//        public DoubleProperty totalIncome;
//
//        /**
//         * 折让
//         */
//        public DoubleProperty allowance;
//
//        /**
//         * 销售成本
//         */
//        public DoubleProperty salescost;
//
//        /**
//         * 商品报损
//         */
//        public DoubleProperty lossExpense;
//
//        /**
//         * 商品赠出
//         */
//        public DoubleProperty giftExpense;
//
//        /**
//         * 总支出
//         */
//        public DoubleProperty totalExpense;
//
//        /**
//         * 利润
//         */
//        public DoubleProperty profit;
//
//        public ProfitBean(double salesIncome, double overflowIncome, double costAdjIncome, double buyAndReturnIncome, double voucherIncome, double totalIncome, double allowance, double salescost, double lossExpense, double giftExpense, double totalExpense, double profit) {
//
//            this.salesIncome = new SimpleDoubleProperty(salesIncome);
//            this.overflowIncome = new SimpleDoubleProperty(overflowIncome);
//            this.costAdjIncome = new SimpleDoubleProperty(costAdjIncome);
//            this.buyAndReturnIncome = new SimpleDoubleProperty(buyAndReturnIncome);
//            this.voucherIncome = new SimpleDoubleProperty(voucherIncome);
//            this.totalIncome = new SimpleDoubleProperty(totalIncome);
//            this.allowance = new SimpleDoubleProperty(allowance);
//            this.salescost = new SimpleDoubleProperty(salescost);
//            this.lossExpense = new SimpleDoubleProperty(lossExpense);
//            this.giftExpense = new SimpleDoubleProperty(giftExpense);
//            this.totalExpense = new SimpleDoubleProperty(totalExpense);
//            this.profit = new SimpleDoubleProperty(profit);
//        }
//
//        public double getSalesIncome() {
//            return salesIncome.get();
//        }
//
//        public DoubleProperty salesIncomeProperty() {
//            return salesIncome;
//        }
//
//        public void setSalesIncome(double salesIncome) {
//            this.salesIncome.set(salesIncome);
//        }
//
//        public double getOverflowIncome() {
//            return overflowIncome.get();
//        }
//
//        public DoubleProperty overflowIncomeProperty() {
//            return overflowIncome;
//        }
//
//        public void setOverflowIncome(double overflowIncome) {
//            this.overflowIncome.set(overflowIncome);
//        }
//
//        public double getCostAdjIncome() {
//            return costAdjIncome.get();
//        }
//
//        public DoubleProperty costAdjIncomeProperty() {
//            return costAdjIncome;
//        }
//
//        public void setCostAdjIncome(double costAdjIncome) {
//            this.costAdjIncome.set(costAdjIncome);
//        }
//
//        public double getBuyAndReturnIncome() {
//            return buyAndReturnIncome.get();
//        }
//
//        public DoubleProperty buyAndReturnIncomeProperty() {
//            return buyAndReturnIncome;
//        }
//
//        public void setBuyAndReturnIncome(double buyAndReturnIncome) {
//            this.buyAndReturnIncome.set(buyAndReturnIncome);
//        }
//
//        public double getVoucherIncome() {
//            return voucherIncome.get();
//        }
//
//        public DoubleProperty voucherIncomeProperty() {
//            return voucherIncome;
//        }
//
//        public void setVoucherIncome(double voucherIncome) {
//            this.voucherIncome.set(voucherIncome);
//        }
//
//        public double getTotalIncome() {
//            return totalIncome.get();
//        }
//
//        public DoubleProperty totalIncomeProperty() {
//            return totalIncome;
//        }
//
//        public void setTotalIncome(double totalIncome) {
//            this.totalIncome.set(totalIncome);
//        }
//
//        public double getAllowance() {
//            return allowance.get();
//        }
//
//        public DoubleProperty allowanceProperty() {
//            return allowance;
//        }
//
//        public void setAllowance(double allowance) {
//            this.allowance.set(allowance);
//        }
//
//        public double getSalescost() {
//            return salescost.get();
//        }
//
//        public DoubleProperty salescostProperty() {
//            return salescost;
//        }
//
//        public void setSalescost(double salescost) {
//            this.salescost.set(salescost);
//        }
//
//        public double getLossExpense() {
//            return lossExpense.get();
//        }
//
//        public DoubleProperty lossExpenseProperty() {
//            return lossExpense;
//        }
//
//        public void setLossExpense(double lossExpense) {
//            this.lossExpense.set(lossExpense);
//        }
//
//        public double getGiftExpense() {
//            return giftExpense.get();
//        }
//
//        public DoubleProperty giftExpenseProperty() {
//            return giftExpense;
//        }
//
//        public void setGiftExpense(double giftExpense) {
//            this.giftExpense.set(giftExpense);
//        }
//
//        public double getTotalExpense() {
//            return totalExpense.get();
//        }
//
//        public DoubleProperty totalExpenseProperty() {
//            return totalExpense;
//        }
//
//        public void setTotalExpense(double totalExpense) {
//            this.totalExpense.set(totalExpense);
//        }
//
//        public double getProfit() {
//            return profit.get();
//        }
//
//        public DoubleProperty profitProperty() {
//            return profit;
//        }
//
//        public void setProfit(double profit) {
//            this.profit.set(profit);
//        }
//    }

}
