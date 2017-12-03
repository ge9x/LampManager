package bl.classificationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import blservice.classificationblservice.ClassificationBLService;
import blservice.classificationblservice.ClassificationInfo;
import util.ResultMessage;
import vo.ClassificationVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class ClassificationController implements ClassificationBLService, ClassificationInfo{
	
	private Classification classification;

	public ClassificationController(){
		classification = new Classification();
	}
	
	public ArrayList<ClassificationVO> show() {
		try {
			return classification.show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
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

	public HashMap<String, String> getAllClassification() {
		ArrayList<ClassificationVO> all = show();
		HashMap<String, String> ret = new HashMap<>();
		for(ClassificationVO vo : all){
			ret.put(vo.ID, vo.name);
		}
		return ret;
	}

}
