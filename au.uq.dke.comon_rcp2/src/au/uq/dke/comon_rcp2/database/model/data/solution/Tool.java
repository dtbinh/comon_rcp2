package au.uq.dke.comon_rcp2.database.model.data.solution;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.ComplianceTask;
import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.ToolFeature;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Tool extends Solution	{
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private ComplianceTask associatedComplianceTask;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ToolFeature> features = new BasicRecordSet<ToolFeature>();
	
	
	public ComplianceTask getAssociatedComplianceTask() {
		return associatedComplianceTask;
	}

	public void setAssociatedComplianceTask(ComplianceTask associatedComplianceTask) {
		this.associatedComplianceTask = associatedComplianceTask;
	}

	public Set<ToolFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<ToolFeature> features) {
		this.features = features;
	}

	public Tool(String name){
		super(name);
	}
	
	public Tool() {

	}
	

}
