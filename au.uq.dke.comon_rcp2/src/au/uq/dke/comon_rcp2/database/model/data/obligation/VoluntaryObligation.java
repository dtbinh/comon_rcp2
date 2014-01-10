package au.uq.dke.comon_rcp2.database.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class VoluntaryObligation extends Obligation	{
	public VoluntaryObligation(String name){
		super(name);
	}

	public VoluntaryObligation() {

	}
	
}
