package blservice.financeblservice;

import util.BillType;

import java.util.Date;

/**
 * Created by Kry·L on 2017/11/4.
 */
public class DocumentDetailsInput {
    Date startDate;
    Date endDate;
    BillType billType;
    String customerName;
    String salesman;
    String inventory;

    public DocumentDetailsInput(Date startDate, Date endDate, BillType billType, String customerName, String salesman, String inventory) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.billType = billType;
        this.customerName = customerName;
        this.salesman = salesman;
        this.inventory = inventory;
    }
}
