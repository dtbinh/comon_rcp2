package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskTreatment extends RiskManagementProcess {
	public RiskTreatment(String name) {
		super(name);
	}
	
	public RiskTreatment() {

	}
	

}
