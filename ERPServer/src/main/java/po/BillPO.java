package po;

import java.util.Date;

import util.BillState;
import util.BillType;

public class BillPO {
	 /**
     * 单据最后修改时间
     */
    private Date date;

    /**
     * 单据编号
     */
    private String ID;

    /**
     * 单据状态
     */
    private BillState state;

    /**
     * 单据类型
     */
    private BillType type;
    
    public BillPO(String ID, Date date, BillType type, BillState state){
    	this.ID = ID;
    	this.date = date;
    	this.type = type;
    	this.state = state;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
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
