package bl.classificationbl;

import java.util.ArrayList;

import blservice.classificationblservice.ClassificationBLService;
import util.ResultMessage;
import vo.ClassificationVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class ClassificationController implements ClassificationBLService{
	
	private Classification classification;

	public ClassificationController(){
		classification = new Classification();
	}
	
	public ArrayList<ClassificationVO> show() {
		return null;
	}

	public ArrayList<ClassificationVO> find(String keyword) {
		return null;
	}

	public ClassificationVO showDetails(String ID) {
		return null;
	}

	public ResultMessage add(ClassificationVO vo) {
		return null;
	}

	public ResultMessage delete(String ID) {
		return null;
	}

	public ResultMessage update(ClassificationVO vo) {
		return null;
	}

	public String getNewID() {
		return null;
	}
	

}
