package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement.BusinessProcess;
import au.uq.dke.comon_rcp2.data.model.data.program.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MonitorAndReview extends RiskManagementProcess {
	
	private Timestamp dateTime;
	
	
	
	
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public RiskAssessment getRisk() {
		return risk;
	}
	public void setRisk(RiskAssessment risk) {
		this.risk = risk;
	}
	public String getReviewDecision() {
		return reviewDecision;
	}
	public void setReviewDecision(String reviewDecision) {
		this.reviewDecision = reviewDecision;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Set<BusinessProcess> getAffectedProcesses() {
		return affectedProcesses;
	}
	public void setAffectedProcesses(Set<BusinessProcess> affectedProcesses) {
		this.affectedProcesses = affectedProcesses;
	}
	public Set<Role> getAffectedRoles() {
		return affectedRoles;
	}
	public void setAffectedRoles(Set<Role> affectedRoles) {
		this.affectedRoles = affectedRoles;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskAssessment risk;
	
	private String reviewDecision;
	private String action;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> affectedProcesses = new BasicRecordSet<BusinessProcess>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();

	public MonitorAndReview(String name) {
		super(name);
	}
	public MonitorAndReview() {

	}
	

}
