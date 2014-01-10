package au.uq.dke.comon_rcp2.database.model.data.solution;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.AssuranceRequiredCondition;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Assurance extends Service	{
	
	public AssuranceRequiredCondition getRequiredCondition() {
		return requiredCondition;
	}

	public void setRequiredCondition(AssuranceRequiredCondition requiredCondition) {
		this.requiredCondition = requiredCondition;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private AssuranceRequiredCondition requiredCondition;
	
	public Assurance(String name){
		super(name);
	}

	public Assurance() {
	}
	

}
