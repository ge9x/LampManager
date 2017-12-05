package ExcelUtil.model;

import java.util.List;
public class Model {

    private final Class clzz;
    private final List<?> data;

    /**
     *
     * @param data - list of clzz
     */
    private Model(Class clzz, List data) {
        this.clzz = clzz;
        this.data = data;

    }

    /**
     *
     * @param clzz
     * @param data
     * @return
     */
    public static Model of(Class clzz, List data) {
        return new Model(clzz, data);
    }

    public Class getClzz() {
        return clzz;
    }

    public List getData() {
        return data;
    }

}
