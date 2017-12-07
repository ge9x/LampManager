package bl.classificationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import blservice.classificationblservice.ClassificationBLService;
import blservice.classificationblservice.ClassificationInfo;
import po.ClassificationPO;
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
		try {
			return classification.find(keyword);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ClassificationVO showDetails(String ID) {
		try {
			return classification.showDetails(ID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(ClassificationVO vo) {
		try {
			return classification.add(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage delete(String ID) {
		try {
			return classification.delete(ID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage update(ClassificationVO vo) {
		try {
			return classification.update(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public String getNewID() {
		try {
			return classification.getNewID();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String, String> getAllClassification() {
		ArrayList<ClassificationVO> all = show();
		HashMap<String, String> ret = new HashMap<>();
		for(ClassificationVO vo : all){
			ret.put(vo.ID, vo.name);
		}
		return ret;
	}

	@Override
	public ClassificationPO getClassificationByName(String name) {
		try {
			return classification.exactlyFindByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
