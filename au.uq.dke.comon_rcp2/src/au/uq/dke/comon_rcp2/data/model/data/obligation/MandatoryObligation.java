package au.uq.dke.comon_rcp2.data.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class MandatoryObligation extends Obligation	{
	public MandatoryObligation(String name){
		super(name);
	}
	public MandatoryObligation() {

	}
	
}
