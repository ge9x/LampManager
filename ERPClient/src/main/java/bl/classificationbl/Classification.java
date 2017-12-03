package bl.classificationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.jboss.jandex.VoidType;

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
	
	private ClassificationVO vo;
	private ClassificationDataService classificationDataService;

	public ArrayList<ClassificationVO> show() throws RemoteException {
		ArrayList<ClassificationPO> pos = classificationDataService.show();
		ArrayList<ClassificationVO> ret = new ArrayList<>();
		for(ClassificationPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
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
}
