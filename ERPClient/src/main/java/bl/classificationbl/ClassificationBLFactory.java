package bl.classificationbl;

import blservice.classificationblservice.ClassificationBLService;
import blservice.classificationblservice.ClassificationInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class ClassificationBLFactory {
	private static ClassificationController classificationController;

	public synchronized static ClassificationBLService getBLService() {
		if (classificationController == null) {
			classificationController = new ClassificationController();
		}
		return classificationController;
	}

	public synchronized static ClassificationInfo getInfo() {
		if (classificationController == null) {
			classificationController = new ClassificationController();
		}
		return classificationController;
	}
}
