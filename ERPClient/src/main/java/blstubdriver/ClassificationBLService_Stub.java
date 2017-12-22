package blstubdriver;

import java.util.ArrayList;

import blservice.classificationblservice.ClassificationBLService;
import util.ResultMessage;
import vo.ClassificationVO;
import vo.GoodsVO;

/**
 * Created on 2017/10/22
 * @author 巽
 *
 */
@SuppressWarnings("deprecation")
public class ClassificationBLService_Stub implements ClassificationBLService{

	ArrayList<ClassificationVO> dataSet;
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

		dataSet = new ArrayList<ClassificationVO>();
		ClassificationVO vo1 = new ClassificationVO("01", "灯", null, new ArrayList<ClassificationVO>(), data);
		ClassificationVO vo2 = new ClassificationVO("02", "落地灯", vo1, new ArrayList<ClassificationVO>(), data);
		ClassificationVO vo3 = new ClassificationVO("03", "台灯", vo1, new ArrayList<ClassificationVO>(), data);
		ClassificationVO vo4 = new ClassificationVO("04", "吊灯", vo2, new ArrayList<ClassificationVO>(), data);
		ClassificationVO vo5 = new ClassificationVO("05", "霓虹灯", vo1, new ArrayList<ClassificationVO>(), null);
		dataSet.add(vo1);
		dataSet.add(vo2);
		dataSet.add(vo3);
		dataSet.add(vo4);
		dataSet.add(vo5);
	}

	public ArrayList<ClassificationVO> show() {
		return dataSet;
	}

	public ArrayList<ClassificationVO> find(String keyword) {
		ArrayList<ClassificationVO> ret = new ArrayList<ClassificationVO>();
		for(ClassificationVO vo : dataSet){
			if(vo.name.contains(keyword)){
				ret.add(vo);
			}
		}
		return ret;
	}

	public ClassificationVO showDetails(String ID) {
		for(ClassificationVO vo : dataSet){
			if(vo.ID.equals(ID)){
			    System.out.println("Show Detail SUCCEED");
				return vo;
			}
		}
		return null;
	}

	public ResultMessage add(ClassificationVO vo) {
		for(ClassificationVO cvo : dataSet){
			if(cvo.ID.equals(vo.ID)){
				System.out.println("add classification failed");
				return ResultMessage.FAILED;
			}
		}
		dataSet.add(vo);
		System.out.println("add classification success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(String ID) {
		for(ClassificationVO cvo : dataSet){
			if(cvo.ID.equals(ID)){
				dataSet.remove(cvo);
				System.out.println("delete classification success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("delete classification failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage update(ClassificationVO vo) {
		for(ClassificationVO cvo : dataSet){
			if(cvo.ID.equals(vo.ID)){
				dataSet.remove(cvo);
				dataSet.add(vo);
				System.out.println("update classification success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("update classification failed");
		return ResultMessage.FAILED;
	}

	public String getNewID() {
		return String.format("%02d", dataSet.size() + 1);
	}

}
