package blservice.formblservice;

import java.util.Date;

/**
 * Created by Kry·L on 2017/11/4.
 */
public class SalesDetailsInput {
    public Date startDate;
    public Date endDate;
    public String goodName;
    public String customerNam;
    public String salesman;
    public String inventory;

    public SalesDetailsInput(Date startDate, Date endDate, String goodName, String customerNam, String salesman, String inventory) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.goodName = goodName;
        this.customerNam = customerNam;
        this.salesman = salesman;
        this.inventory = inventory;
    }
}
