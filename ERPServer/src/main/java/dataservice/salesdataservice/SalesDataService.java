package dataservice.salesdataservice;

import java.util.ArrayList;

import po.SalesPO;
import util.ResultMessage;

/**
 * created by zlk on 2017/10/21
 */

public interface SalesDataService {
	/**
	 * 通过单据编号查找单据
	 * 
	 * @param ID
	 * @return 查找到的单据
	 * @author zlk
	 */
	public SalesPO find(String ID);
	/**
	 * 添加单据
	 * 
	 * @param po
	 * @return 是否成功添加单据
	 * @author zlk
	 */
	public ResultMessage add(SalesPO po);
	/**
	 * 更新单据
	 * 
	 * @param po
	 * @return 是否成功更新单据
	 * @author zlk
	 */
	public ResultMessage update(SalesPO po);
	/**
	 * 删除单据
	 * 
	 * @param po
	 * @return 是否成功删除单据
	 * @author zlk
	 */
	public ResultMessage delete(SalesPO po);
	/**
	 * 初始化持久化数据库
	 * 
	 * @author zlk
	 */
	public void init();
	/**
	 * 展示单据
	 * 
	 * @return po列表
	 * @author zlk
	 */
	public ArrayList<SalesPO> show();
}
