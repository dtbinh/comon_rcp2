package au.uq.dke.comon_rcp2.data.model.data.program;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement.ProcessRule;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlIdentificationAndDefinition extends ControlAndMonitoring	{
	@ManyToMany( cascade = {CascadeType.PERSIST} )
	private Set<ProcessRule> processRules = new BasicRecordSet<ProcessRule>();

	public ControlIdentificationAndDefinition(String name){
		super(name);
	}

	public Set<ProcessRule> getProcessRules() {
		return processRules;
	}

	public void setProcessRules(Set<ProcessRule> processRules) {
		this.processRules = processRules;
	}

	public ControlIdentificationAndDefinition() {

	}
	
}
