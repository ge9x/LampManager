package blservice.formblservice;

import util.BillType;
import util.FilterType;

import java.util.Date;

/**
 * Created by Kry·L on 2017/11/4.
 */
public class DocumentDetailsInput {
    public String startDate;
    public String endDate;
    public BillType billType;
    public FilterType filterType;
    public String keyword;

    public DocumentDetailsInput(String startDate, String endDate, BillType billType, FilterType filterType,String keyword) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.billType = billType;
        this.filterType = filterType;
        this.keyword = keyword;
    }
}
