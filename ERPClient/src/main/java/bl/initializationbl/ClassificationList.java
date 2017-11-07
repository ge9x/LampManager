package bl.initializationbl;

import blservice.classificationblservice.ClassificationInfo;
import vo.ClassificationVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class ClassificationList {
    private ArrayList<ClassificationVO> classificationVOS;
    private ClassificationInfo classificationInfo;

    public ArrayList<ClassificationVO> getClassifications(){
        return classificationVOS;
    }
}
