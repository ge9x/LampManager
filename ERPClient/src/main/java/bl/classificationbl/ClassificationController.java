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
		return classification.show();
	}

	public ArrayList<ClassificationVO> find(String keyword) {
		return classification.find(keyword);
	}

	public ClassificationVO showDetails(String ID) {
		return classification.showDetails(ID);
	}

	public ResultMessage add(ClassificationVO vo) {
		return classification.add(vo);
	}

	public ResultMessage delete(String ID) {
		return classification.delete(ID);
	}

	public ResultMessage update(ClassificationVO vo) {
		return classification.update(vo);
	}

	public String getNewID() {
		return classification.getNewID();
	}
	

}
