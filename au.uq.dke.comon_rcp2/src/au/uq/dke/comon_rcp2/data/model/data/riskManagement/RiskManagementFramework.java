package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement.BusinessProcess;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskManagementFramework extends RiskManagement {
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> associatedBusinessProcesses = new BasicRecordSet<BusinessProcess>();

	public Set<BusinessProcess> getAssociatedBusinessProcesses() {
		return associatedBusinessProcesses;
	}

	public void setAssociatedBusinessProcesses(
			Set<BusinessProcess> associatedBusinessProcesses) {
		this.associatedBusinessProcesses = associatedBusinessProcesses;
	}

	public Set<RiskManagementProcess> getAssociatedRiskManagementProcesses() {
		return associatedRiskManagementProcesses;
	}

	public void setAssociatedRiskManagementProcesses(
			Set<RiskManagementProcess> associatedRiskManagementProcesses) {
		this.associatedRiskManagementProcesses = associatedRiskManagementProcesses;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskManagementProcess> associatedRiskManagementProcesses = new BasicRecordSet<RiskManagementProcess>();

	public RiskManagementFramework(String name) {
		super(name);
	}
	
	public RiskManagementFramework() {

	}
	

}
