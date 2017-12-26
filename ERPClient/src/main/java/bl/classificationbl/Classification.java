package bl.classificationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.goodsbl.Goods;
import bl.logbl.LogController;
import blservice.logblservice.LogInfo;
import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;
import po.GoodsPO;
import rmi.ClassificationRemoteHelper;
import util.OperationObjectType;
import util.OperationType;
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
	private LogInfo logInfo;
	
	public Classification(){
		classificationDataService = ClassificationRemoteHelper.getInstance().getClassificationDataService();
		logInfo = new LogController();
	}

	public ArrayList<ClassificationVO> show() throws RemoteException {
		ArrayList<ClassificationPO> pos = classificationDataService.show();
		ArrayList<ClassificationVO> ret = new ArrayList<>();
		for(ClassificationPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
	}

	public ArrayList<ClassificationVO> find(String keyword) throws RemoteException {
		ArrayList<ClassificationPO> pos = classificationDataService.findFuzzilyByName(keyword);
		ArrayList<ClassificationVO> ret = new ArrayList<>();
		for(ClassificationPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
	}

	public ClassificationVO showDetails(String ID) throws NumberFormatException, RemoteException {
		ClassificationPO found = classificationDataService.findByID(Integer.parseInt(ID));
		return poToVO(found);
	}

	public ResultMessage add(ClassificationVO vo) throws RemoteException {
		ArrayList<ClassificationPO> repeated = classificationDataService.findFullyByName(vo.name);
		if(repeated.size() > 0){
			return ResultMessage.EXIST;
		}
		else{
			ResultMessage ret = classificationDataService.add(this.voToPO(vo));
			if(ret == ResultMessage.SUCCESS){
				ClassificationPO added = classificationDataService.findFullyByName(vo.name).get(0);
				logInfo.record(OperationType.ADD, OperationObjectType.CLASSIFICATION, added.toString());
			}
			return ret;
		}
	}

	public ResultMessage delete(String ID) throws NumberFormatException, RemoteException {
		ClassificationPO found = classificationDataService.findByID(Integer.parseInt(ID));
		if(found == null){
			return ResultMessage.NOT_EXIST;
		}
		else if(found.getChidren().isEmpty() && found.getGoods().isEmpty()){
			ResultMessage  ret = classificationDataService.delete(found);
			if(ret == ResultMessage.SUCCESS){
				logInfo.record(OperationType.DELETE, OperationObjectType.CLASSIFICATION, found.toString());
			}
			return ret;
		}
		else{
			return ResultMessage.FAILED;
		}
	}

	/**
	 * 约定：对商品分类的修改只能修改名字
	 */
	public ResultMessage update(ClassificationVO vo) throws NumberFormatException, RemoteException {
		ClassificationPO toUpdate = classificationDataService.findByID(Integer.parseInt(vo.ID));
		if(toUpdate == null){
			return ResultMessage.NOT_EXIST;
		}
		ArrayList<ClassificationPO> repeated = classificationDataService.findFullyByName(vo.name);
		if(repeated.size() > 0){	// 重名
			return ResultMessage.EXIST;
		}
		else{	// 改名以后无重名
			toUpdate.setName(vo.name);
			ResultMessage ret = classificationDataService.update(toUpdate);
			if(ret == ResultMessage.SUCCESS){
				logInfo.record(OperationType.UPDATE, OperationObjectType.CLASSIFICATION, toUpdate.toString());
			}
			return ret;
		}
		
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
			ClassificationVO father = null;
			if(po.getFather() != null){
				father = new ClassificationVO();
				father.name = po.getFather().getName();
			}
			ArrayList<ClassificationVO> chidren = new ArrayList<>();
			for(ClassificationPO toAdd : po.getChidren()){
				ClassificationVO child = new ClassificationVO();
				child.name = toAdd.getName();
				chidren.add(child);
			}
			ArrayList<GoodsVO> goods = new ArrayList<>();
			for(GoodsPO aGoods : po.getGoods()){
				goods.add(Goods.poToVO(aGoods));
			}
			ClassificationVO ret = new ClassificationVO(ID, po.getName(), father, chidren, goods);
			return ret;
		}
	}
	
	/**
	 * 仅限于添加新商品分类时使用<br>
	 * 默认父商品分类存在（第一个商品分类为“灯”，由系统自动添加为根节点）<br>
	 * 默认尚未包含子商品分类<br>
	 * 默认尚未包含商品
	 */
	private ClassificationPO voToPO(ClassificationVO vo) throws NumberFormatException, RemoteException {
		ClassificationPO father  = classificationDataService.findByID(Integer.parseInt(vo.father.ID));
		ArrayList<ClassificationPO> chidren = new ArrayList<>();	// 添加商品分类时默认尚未包含子商品分类，故为空
		ArrayList<GoodsPO> goods = new ArrayList<>();	// 添加商品分类时默认尚未包含商品，故为空
		ClassificationPO ret = new ClassificationPO(vo.name, father, chidren, goods);
		return ret;
	}
	
	/**
	 * 以商品分类的名称进行完全匹配查找
	 * @param name 商品分类的名称
	 * @return 找到的商品分类（默认符合条件的结果<=1）
	 * @throws RemoteException
	 */
	protected ClassificationPO exactlyFindByName(String name) throws RemoteException{
		ArrayList<ClassificationPO> pos = classificationDataService.findFullyByName(name);
		if(pos.size() > 0){
			return pos.get(0);
		}
		else{
			return null;
		}
	}
}
