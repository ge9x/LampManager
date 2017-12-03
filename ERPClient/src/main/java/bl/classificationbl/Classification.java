package bl.classificationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.goodsbl.Goods;
import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;
import po.GoodsPO;
import util.ResultMessage;
import vo.ClassificationVO;
import vo.GoodsVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class Classification {
	private ClassificationDataService classificationDataService;

	public ArrayList<ClassificationVO> show() throws RemoteException {
		ArrayList<ClassificationPO> pos = classificationDataService.show();
		ArrayList<ClassificationVO> ret = new ArrayList<>();
		for(ClassificationPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
	}

	public ArrayList<ClassificationVO> find(String keyword) throws RemoteException {
		ArrayList<ClassificationPO> pos = classificationDataService.findByName(keyword);
		ArrayList<ClassificationVO> ret = new ArrayList<>();
		for(ClassificationPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
	}

	public ClassificationVO showDetails(String ID) throws NumberFormatException, RemoteException {
		ClassificationPO found = classificationDataService.find(Integer.parseInt(ID));
		return poToVO(found);
	}

	public ResultMessage add(ClassificationVO vo) throws RemoteException {
		return classificationDataService.add(voToPO(vo));
	}

	public ResultMessage delete(String ID) throws RemoteException {
		ClassificationPO found = null;
		return classificationDataService.delete(found);
	}

	public ResultMessage update(ClassificationVO vo) throws RemoteException {
		ClassificationPO found = null;
		return classificationDataService.delete(found);
	}

	public String getNewID() throws RemoteException {
		return classificationDataService.getNewID();
	}
	
	public static ClassificationVO poToVO(ClassificationPO po){
		if(po == null){
			return null;
		}
		else{
			String ID = String.format("%02d", po.getID());
			ClassificationVO father = poToVO(po.getFather());
			ArrayList<ClassificationVO> chidren = new ArrayList<>();
			for(ClassificationPO child : po.getChidren()){
				chidren.add(poToVO(child));
			}
			ArrayList<GoodsVO> goods = new ArrayList<>();
			for(GoodsPO aGoods : po.getGoods()){
				goods.add(Goods.poToVO(aGoods));
			}
			ClassificationVO ret = new ClassificationVO(ID, po.getName(), father, chidren, goods);
			return ret;
		}
	}
	
	private ClassificationPO voToPO(ClassificationVO vo) throws NumberFormatException, RemoteException {
		ClassificationPO father  = classificationDataService.find(Integer.parseInt(vo.ID));
		ArrayList<ClassificationPO> chidren = new ArrayList<>();
		for(ClassificationVO child : vo.chidren){
			chidren.add(classificationDataService.find(Integer.parseInt(child.ID)));
		}
		ArrayList<GoodsPO> goods = new ArrayList<>();
		for(GoodsVO aGoods : vo.goods){
//			goods.add(); TODO
		}
		ClassificationPO ret = new ClassificationPO(vo.name, father, chidren, goods);
		return ret;
	}
}
