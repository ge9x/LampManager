package bl.initializationbl;

import po.InitClassificationPO;
import vo.ClassificationVO;
import vo.GoodsVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockClassificationList extends ClassificationList {
    @Override
    public ArrayList<InitClassificationPO> getClassifications() {
        ClassificationVO classification = new ClassificationVO("05", "霓虹灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
        ArrayList<ClassificationVO> classificationVOS = new ArrayList<ClassificationVO>();
        classificationVOS.add(classification);
        return null;
    }
}
