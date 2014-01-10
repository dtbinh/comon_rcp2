package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecord;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskManagement extends BasicRecord {
	public RiskManagement(String name) {
		super(name);
	}
	public RiskManagement() {

	}
	

}
