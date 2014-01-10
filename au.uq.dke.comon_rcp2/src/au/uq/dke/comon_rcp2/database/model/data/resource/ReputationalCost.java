package au.uq.dke.comon_rcp2.database.model.data.resource;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.relatedType.DamageType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ReputationalCost extends NonMonetaryResource {

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private DamageType typeOfDamange;
	
	
	public ReputationalCost(String name) {
		super(name);
	}
	public ReputationalCost() {

	}
	

}
