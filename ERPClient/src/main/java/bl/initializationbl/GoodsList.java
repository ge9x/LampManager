package bl.initializationbl;

import blservice.goodsblservice.GoodsInfo;
import po.InitGoodsPO;
import vo.GoodsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class GoodsList {
    private ArrayList<GoodsVO> goodsVOS;
    private GoodsInfo goodsInfo;

    public ArrayList<GoodsVO> getGoods(){

        return goodsVOS;
    }

    public ArrayList<GoodsVO> posTovos(List<InitGoodsPO> initGoodsPOS) {
        ArrayList<GoodsVO> vos = new ArrayList<>();
        for (InitGoodsPO po : initGoodsPOS){
            vos.add(new GoodsVO(po.getID()+"",po.getName(),po.getModel(),po.getClassificationName(),po.getGoodsNumber(),0,po.getBuyingPrice(),po.getRecentBuyingPrice(),po.getRetailPrice(),po.getRecentRetailPrice()));
        }
        return vos;
    }
}
