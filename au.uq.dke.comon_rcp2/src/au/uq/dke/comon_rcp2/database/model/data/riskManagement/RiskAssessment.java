package au.uq.dke.comon_rcp2.database.model.data.riskManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.metawidget.inspector.annotation.UiHidden;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.database.model.data.businessProcessManagement.ProcessActivity;
import au.uq.dke.comon_rcp2.database.model.data.obligation.Obligation;
import au.uq.dke.comon_rcp2.database.model.data.program.Stakeholder;
import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.Reference;
import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.RiskCriterion;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.RiskEvaluationStatus;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.RiskPossibilityOfOccurence;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.RiskPossibilityToControl;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.RiskType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskAssessment extends RiskManagementProcess {
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ProcessActivity> affectedProcesses= new BasicRecordSet<ProcessActivity>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Stakeholder> affectedStakeholders = new BasicRecordSet<Stakeholder>();
	//@UiComesAfter("name")
	@UiHidden
	private String associatedDamage;
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> associatedObligations= new BasicRecordSet<Obligation>();
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskPossibilityOfOccurence possibilityOfOccurence;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskPossibilityToControl possibilityToControl;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Reference reference;
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskCriterion> riskCriteria = new BasicRecordSet<RiskCriterion>();
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskType riskType;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskEvaluationStatus status;
	public RiskAssessment() {

	}
	public RiskAssessment(String name) {
		super(name);
	}
	public Set<ProcessActivity> getAffectedProcesses() {
		return affectedProcesses;
	}
	public Set<Stakeholder> getAffectedStakeholders() {
		return affectedStakeholders;
	}
	public String getAssociatedDamage() {
		return associatedDamage;
	}
	public Set<Obligation> getAssociatedObligations() {
		return associatedObligations;
	}
	public RiskPossibilityOfOccurence getPossibilityOfOccurence() {
		return possibilityOfOccurence;
	}
	public RiskPossibilityToControl getPossibilityToControl() {
		return possibilityToControl;
	}
	public Reference getReference() {
		return reference;
	}
	public Set<RiskCriterion> getRiskCriteria() {
		return riskCriteria;
	}
	public RiskType getRiskType() {
		return riskType;
	}
	public RiskEvaluationStatus getStatus() {
		return status;
	}
	public void setAffectedProcesses(Set<ProcessActivity> affectedProcesses) {
		this.affectedProcesses = affectedProcesses;
	}
	
	public void setAffectedStakeholders(Set<Stakeholder> affectedStakeholders) {
		this.affectedStakeholders = affectedStakeholders;
	}


	public void setAssociatedDamage(String associatedDamage) {
		this.associatedDamage = associatedDamage;
	}
	
	
	public void setAssociatedObligations(Set<Obligation> associatedObligations) {
		this.associatedObligations = associatedObligations;
	}

	public void setPossibilityOfOccurence(
			RiskPossibilityOfOccurence possibilityOfOccurence) {
		this.possibilityOfOccurence = possibilityOfOccurence;
	}
	
	public void setPossibilityToControl(
			RiskPossibilityToControl possibilityToControl) {
		this.possibilityToControl = possibilityToControl;
	}
	
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	public void setRiskCriteria(Set<RiskCriterion> riskCriteria) {
		this.riskCriteria = riskCriteria;
	}

	
	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}
	public void setStatus(RiskEvaluationStatus status) {
		this.status = status;
	}
	

}
