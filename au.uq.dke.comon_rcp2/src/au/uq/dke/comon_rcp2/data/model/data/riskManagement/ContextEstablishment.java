package au.uq.dke.comon_rcp2.data.model.data.riskManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.ExternalParameter;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.InternalParameter;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.RiskCriterion;
import au.uq.dke.comon_rcp2.data.model.data.relatedType.ContextApplicability;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ContextEstablishment extends RiskManagementProcess {
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ExternalParameter> externalParameters = new BasicRecordSet<ExternalParameter>();
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<InternalParameter> internalParameters = new BasicRecordSet<InternalParameter>();
	
	public Set<ExternalParameter> getExternalParameters() {
		return externalParameters;
	}
	public void setExternalParameters(Set<ExternalParameter> externalParameters) {
		this.externalParameters = externalParameters;
	}
	public Set<InternalParameter> getInternalParameters() {
		return internalParameters;
	}
	public void setInternalParameters(Set<InternalParameter> internalParameters) {
		this.internalParameters = internalParameters;
	}
	public Set<RiskCriterion> getRiskCriteria() {
		return riskCriteria;
	}
	public void setRiskCriteria(Set<RiskCriterion> riskCriteria) {
		this.riskCriteria = riskCriteria;
	}
	public ContextApplicability getApplicability() {
		return applicability;
	}
	public void setApplicability(ContextApplicability applicability) {
		this.applicability = applicability;
	}
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskCriterion> riskCriteria = new BasicRecordSet<RiskCriterion>();
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private ContextApplicability applicability;
	

	public ContextEstablishment(String name) {
		super(name);
	}
	public ContextEstablishment() {

	}
	

}
