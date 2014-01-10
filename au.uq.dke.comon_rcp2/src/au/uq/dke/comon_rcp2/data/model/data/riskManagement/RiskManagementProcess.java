package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskManagementProcess extends RiskManagement {
	public RiskManagementProcess(String name) {
		super(name);
	}
	public RiskManagementProcess() {

	}
	

}
