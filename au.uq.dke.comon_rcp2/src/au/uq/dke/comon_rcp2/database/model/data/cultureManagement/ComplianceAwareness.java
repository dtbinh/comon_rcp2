package au.uq.dke.comon_rcp2.database.model.data.cultureManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.database.model.data.businessProcessManagement.BusinessProcess;
import au.uq.dke.comon_rcp2.database.model.data.solution.Training;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ComplianceAwareness extends OrganizationalCommitment {

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> businessProcesses = new BasicRecordSet<BusinessProcess>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Training> trainings = new BasicRecordSet<Training>();

	
	public ComplianceAwareness(String name) {
		super(name);
	}
	
	public ComplianceAwareness(){
		
	}

	public Set<BusinessProcess> getBusinessProcesses() {
		return businessProcesses;
	}

	public void setBusinessProcesses(Set<BusinessProcess> businessProcesses) {
		this.businessProcesses = businessProcesses;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

}
