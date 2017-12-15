package bl.goodsbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.GoodsVO;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
@SuppressWarnings("deprecation")
public class MockGoods extends Goods {

	@Override
	public ArrayList<GoodsVO> show() {
		ArrayList<GoodsVO> ret = new ArrayList<GoodsVO>();
		GoodsVO vo = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, null, 200, 25, 250, 2500, 250, 2500);
		ret.add(vo);
		return ret;
	}

	@Override
	public ArrayList<GoodsVO> find(String keyword) {
		if("圣洁牌经典黑白配台灯".contains(keyword)){
			ArrayList<GoodsVO> ret = new ArrayList<GoodsVO>();
			GoodsVO vo = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, null, 200, 25, 250, 2500, 250, 2500);
			ret.add(vo);
			return ret;
		}
		else{
			return null;
		}
	}

	@Override
	public GoodsVO showDetails(String ID) {
		if(ID.equals("0100001")){
			GoodsVO vo = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, null, 200, 25, 250, 2500, 250, 2500);
			return vo;
		}
		else{
			return null;
		}
	}

	@Override
	public ResultMessage add(GoodsVO vo) {
		if(vo.name.equals("圣洁牌经典黑白配台灯")){
			return ResultMessage.EXIST;
		}
		else{
			System.out.println("Add Goods succeed");
			return ResultMessage.SUCCESS;
		}
	}

	@Override
	public ResultMessage delete(String ID) {
		if(ID.equals("0100001")){
			System.out.println("delete Goods succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public ResultMessage update(GoodsVO vo) {
		if(vo.ID.equals("0100001")){
			System.out.println("update Goods succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public String getNewID(String classificationID) {
		return "0100002";
	}

}
