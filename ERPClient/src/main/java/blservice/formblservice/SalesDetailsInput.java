package blservice.formblservice;

import util.FilterType;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/4.
 */
public class SalesDetailsInput {
    public String startDate;
    public String endDate;
    public String keyword;
    public FilterType filterType;
    public SalesDetailsInput(String startDate, String endDate, String keyword, FilterType filter) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.keyword = keyword;
        this.filterType = filter;
    }
}
