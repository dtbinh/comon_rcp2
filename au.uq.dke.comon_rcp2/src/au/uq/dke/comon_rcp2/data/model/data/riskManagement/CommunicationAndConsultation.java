package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.program.Role;
import au.uq.dke.comon_rcp2.data.model.data.relatedType.CommunicationAndConsulationStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CommunicationAndConsultation extends RiskManagementProcess {
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskAssessment> associatedRisks = new BasicRecordSet<RiskAssessment>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> targetedRoles = new BasicRecordSet<Role>();
	
	public Set<RiskAssessment> getAssociatedRisks() {
		return associatedRisks;
	}
	public void setAssociatedRisks(Set<RiskAssessment> associatedRisks) {
		this.associatedRisks = associatedRisks;
	}
	public Set<Role> getTargetedRoles() {
		return targetedRoles;
	}
	public void setTargetedRoles(Set<Role> targetedRoles) {
		this.targetedRoles = targetedRoles;
	}
	public CommunicationAndConsulationStatus getStatus() {
		return status;
	}
	public void setStatus(CommunicationAndConsulationStatus status) {
		this.status = status;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private CommunicationAndConsulationStatus status;
	

	public CommunicationAndConsultation(String name) {
		super(name);
	}
	public CommunicationAndConsultation() {

	}
	

}
