package po;

import util.BillState;
import util.BillType;

public class BillPO {
	 /**
     * 单据最后修改时间
     */
    private String date;

    /**
     * 单据编号
     */
    private int ID;

    /**
     * 单据状态
     */
    private BillState state;

    /**
     * 单据类型
     */
    private BillType type;
    
    public BillPO(){ }
    
    public BillPO(String date, BillType type, BillState state){
    	this.date = date;
    	this.type = type;
    	this.state = state;
    }
    
    public BillPO(int ID, String date, BillType type, BillState state){
    	this.ID = ID;
    	this.date = date;
    	this.type = type;
    	this.state = state;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
	}
    
    
}
