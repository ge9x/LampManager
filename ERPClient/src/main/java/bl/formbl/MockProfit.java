package bl.formbl;

import vo.ProfitVO;

import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class MockProfit extends Profit {
    @Override
    public ProfitVO getProfit(Date startDate, Date endDate) {
        ProfitVO profitVO = new ProfitVO(new Date(), new Date(), 10000, 3000,
                200, 400, 900, 9500, 5000,
                4000, 1000, 500, 5500, 4000);
        return profitVO;
    }
}
