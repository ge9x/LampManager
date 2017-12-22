package po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by zlk on 2017/12/18
 */

@Entity
@Table(name = "initialization")
public class InitializationPO implements Serializable{
private static final long servialVersionUID=45345768282732L;
	
	private int ID;
    private String date;
    private List<InitCustomerPO> initCustomerPOS;
    private List<InitAccountPO> initAccountPOS;
    private List<InitGoodsPO> initGoodsPOS;
    private List<InitClassificationPO> initClassificationPOS;
    
    public InitializationPO(){};

   
    public InitializationPO( String date, List<InitCustomerPO> initCustomerPOS,
			List<InitAccountPO> initAccountPOS, List<InitGoodsPO> initGoodsPOS,
			List<InitClassificationPO> initClassificationPOS) {
		super();
		this.date = date;
		this.initCustomerPOS = initCustomerPOS;
		this.initAccountPOS = initAccountPOS;
		this.initGoodsPOS = initGoodsPOS;
		this.initClassificationPOS = initClassificationPOS;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}

	@Column(name = "date")
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "initialization")
	public List<InitCustomerPO> getInitCustomerPOS() {
		return initCustomerPOS;
	}


	public void setInitCustomerPOS(List<InitCustomerPO> initCustomerPOS) {
		this.initCustomerPOS = initCustomerPOS;
	}

	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "initialization")
	public List<InitAccountPO> getInitAccountPOS() {
		return initAccountPOS;
	}


	public void setInitAccountPOS(List<InitAccountPO> initAccountPOS) {
		this.initAccountPOS = initAccountPOS;
	}

	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "initialization")
	public List<InitGoodsPO> getInitGoodsPOS() {
		return initGoodsPOS;
	}


	public void setInitGoodsPOS(List<InitGoodsPO> initGoodsPOS) {
		this.initGoodsPOS = initGoodsPOS;
	}

	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "initialization")
	public List<InitClassificationPO> getInitClassificationPOS() {
		return initClassificationPOS;
	}


	public void setInitClassificationPOS(List<InitClassificationPO> initClassificationPOS) {
		this.initClassificationPOS = initClassificationPOS;
	}


}
