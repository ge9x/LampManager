package bl.initializationbl;

import bl.goodsbl.GoodsBLFactory;
import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsInfo;
import po.GoodsPO;
import po.InitGoodsPO;
import vo.GoodsVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class GoodsList {
    private ArrayList<GoodsVO> goodsVOS;
    private GoodsInfo goodsInfo;

    public GoodsList(){
        goodsInfo = GoodsBLFactory.getInfo();

    }
    public ArrayList<InitGoodsPO> getGoods(){
        ArrayList<InitGoodsPO> pos = new ArrayList<>();
        ArrayList<GoodsVO> goodsVOS = goodsInfo.getAllGoods();

        for (GoodsVO goodsVO : goodsVOS){
            pos.add(new InitGoodsPO(goodsVO.ID,goodsVO.name,goodsVO.model,goodsVO.buyingPrice,goodsVO.retailPrice,goodsVO.recentBuyingPrice,goodsVO.recentRetailPrice,goodsVO.amount,goodsVO.classification));
        }
        return pos;
    }

    public ArrayList<GoodsVO> posTovos(List<InitGoodsPO> initGoodsPOS) {
        ArrayList<GoodsVO> vos = new ArrayList<>();
        for (InitGoodsPO po : initGoodsPOS){
            vos.add(new GoodsVO(po.getID()+"",po.getName(),po.getModel(),po.getClassificationName(),po.getGoodsNumber(),0,po.getBuyingPrice(),po.getRecentBuyingPrice(),po.getRetailPrice(),po.getRecentRetailPrice()));
        }
        return vos;
    }
}
