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
public class ClassificationBLService_Stub implements ClassificationBLService{

	ArrayList<ClassificationVO> dataSet;
	
	{
		dataSet = new ArrayList<ClassificationVO>();
		ClassificationVO vo1 = new ClassificationVO("01", "灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
		ClassificationVO vo2 = new ClassificationVO("02", "落地灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
		ClassificationVO vo3 = new ClassificationVO("03", "台灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
		ClassificationVO vo4 = new ClassificationVO("04", "吊灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
		ClassificationVO vo5 = new ClassificationVO("05", "霓虹灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
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

	public ClassificationVO showDetails(String id) {
		for(ClassificationVO vo : dataSet){
			if(vo.id.equals(id)){
				return vo;
			}
		}
		return null;
	}

	public ResultMessage add(ClassificationVO vo) {
		for(ClassificationVO cvo : dataSet){
			if(cvo.id.equals(vo.id)){
				return ResultMessage.FAILED;
			}
		}
		dataSet.add(vo);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(String id) {
		for(ClassificationVO cvo : dataSet){
			if(cvo.id.equals(id)){
				dataSet.remove(cvo);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage update(ClassificationVO vo) {
		for(ClassificationVO cvo : dataSet){
			if(cvo.id.equals(vo.id)){
				dataSet.remove(cvo);
				dataSet.add(vo);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public String getNewID() {
		return String.format("%02d", dataSet.size() + 1);
	}

}
