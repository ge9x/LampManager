package blservice.formblservice;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/4.
 */
public class SalesDetailsInput {
    public LocalDate startDate;
    public LocalDate endDate;
    public String goodName;
    public String customerNam;
    public String salesman;
    public String inventory;

    public SalesDetailsInput(LocalDate startDate, LocalDate endDate, String goodName, String customerNam, String salesman, String inventory) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.goodName = goodName;
        this.customerNam = customerNam;
        this.salesman = salesman;
        this.inventory = inventory;
    }
}
