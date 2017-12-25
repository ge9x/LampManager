package bl.initializationbl;

import po.InitGoodsPO;
import vo.GoodsVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockGoodsList extends GoodsList {
    @Override
    public ArrayList<InitGoodsPO> getGoods() {
        GoodsVO goods = new GoodsVO("05000005", "后现代主义七彩霓虹灯", "LLL", "霓虹灯", "栖霞区仓库", 7, 3, 23333.3, 250000, 25000, 255555);
        ArrayList<GoodsVO> goodsVOS = new ArrayList<GoodsVO>();
        goodsVOS.add(goods);
        return null;
    }
}
