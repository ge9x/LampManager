package blstubdriver;

import java.util.ArrayList;

import blservice.goodsblservice.GoodsBLService;
import util.ResultMessage;
import vo.GoodsVO;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class GoodsBLService_Stub implements GoodsBLService{
	ArrayList<GoodsVO> data;
	
	{
		data = new ArrayList<GoodsVO>();
		GoodsVO g1 = new GoodsVO("02000001", "圣洁牌纯黑落地灯", "L", "落地灯", "栖霞区仓库", 700, 250, 233.3, 250, 250, 2500);
		GoodsVO g2 = new GoodsVO("02000002", "圣洁牌纯白落地灯", "M", "落地灯", "鼓楼区仓库", 700, 250, 233.3, 250, 250, 2500);
		GoodsVO g3 = new GoodsVO("03000003", "圣洁牌简洁黑白配台灯", "S", "台灯", "鼓楼区仓库", 7000, 2500, 233.3, 250, 250, 2500);
		GoodsVO g4 = new GoodsVO("04000004", "圣洁牌古典吊灯", "LL", "吊灯", "栖霞区仓库", 70, 250, 2333.3, 2500, 2500, 25000);
		GoodsVO g5 = new GoodsVO("05000005", "圣洁牌后现代主义七彩霓虹灯", "LLL", "霓虹灯", "栖霞区仓库", 7, 3, 23333.3, 250000, 25000, 255555);
		data.add(g1);
		data.add(g2);
		data.add(g3);
		data.add(g4);
		data.add(g5);
	}

	public ArrayList<GoodsVO> show() {
		return data;
	}

	public ArrayList<GoodsVO> find(String keyword) {
		ArrayList<GoodsVO> ret = new ArrayList<GoodsVO>();
		for(GoodsVO vo : data){
			if(vo.name.contains(keyword) || vo.model.contains(keyword)){
				ret.add(vo);
			}
		}
		return ret;
	}

	public GoodsVO showDetails(String id) {
		for(GoodsVO vo : data){
			if(vo.id.equals(id)){
				return vo;
			}
		}
		return null;
	}

	public ResultMessage add(GoodsVO vo) {
		for(GoodsVO gvo : data){
			if(gvo.id.equals(vo.id)){
				return ResultMessage.FAILED;
			}
		}
		data.add(vo);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(String id) {
		for(GoodsVO gvo : data){
			if(gvo.id.equals(id)){
				data.remove(gvo);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage update(GoodsVO vo) {
		for(GoodsVO gvo : data){
			if(gvo.id.equals(vo.id)){
				data.remove(gvo);
				data.add(vo);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public String getNewID() {
		return String.format("%06d", data.size() + 1);
	}

}
