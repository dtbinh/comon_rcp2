package au.uq.dke.comon_rcp2.data.model.data.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.program.Program;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class RemediationCost extends MonetaryResource	{
	
	private String nonComplianceEvent;
	private String obligation;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Program> associatedProcesses = new BasicRecordSet<Program>();
	
	public String getNonComplianceEvent() {
		return nonComplianceEvent;
	}

	public void setNonComplianceEvent(String nonComplianceEvent) {
		this.nonComplianceEvent = nonComplianceEvent;
	}

	public String getObligation() {
		return obligation;
	}

	public void setObligation(String obligation) {
		this.obligation = obligation;
	}

	public Set<Program> getAssociatedProcesses() {
		return associatedProcesses;
	}

	public void setAssociatedProcesses(Set<Program> associatedProcesses) {
		this.associatedProcesses = associatedProcesses;
	}

	public RemediationCost(String name){
		super(name);
	}

	public RemediationCost() {

	}
	
}
