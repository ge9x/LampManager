package bl.classificationbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.ClassificationVO;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockClassification extends Classification{

	@Override
	public ArrayList<ClassificationVO> show() {
		ArrayList<ClassificationVO> ret = new ArrayList<ClassificationVO>();
		ClassificationVO vo = new ClassificationVO("01", "台灯", null, null, null);
		ret.add(vo);
		return ret;
	}

	@Override
	public ArrayList<ClassificationVO> find(String keyword) {
		if("台灯".contains(keyword)){
			ArrayList<ClassificationVO> ret = new ArrayList<ClassificationVO>();
			ClassificationVO vo = new ClassificationVO("01", "台灯", null, null, null);
			ret.add(vo);
			return ret;
		}
		else{
			return null;
		}
	}

	@Override
	public ClassificationVO showDetails(String ID) {
		if(ID.equals("01")){
			ClassificationVO vo = new ClassificationVO("01", "台灯", null, null, null);
			return vo;
		}
		else{
			return null;
		}
	}

	@Override
	public ResultMessage add(ClassificationVO vo) {
		if(vo.name.equals("台灯")){
			return ResultMessage.EXIST;
		}
		else{
            System.out.println("Add Classification succeed");
			return ResultMessage.SUCCESS;
		}
	}

	@Override
	public ResultMessage delete(String ID) {
		if(ID.equals("01")){
			System.out.println("Delete Classification succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public ResultMessage update(ClassificationVO vo) {
		if(vo.ID.equals("01")){
			System.out.println("Update Classification succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public String getNewID() {
		return "02";
	}

}
