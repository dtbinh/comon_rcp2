package au.uq.dke.comon_rcp2.database.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Obligation extends BasicRecord	{
	public Obligation(String name){
		super(name);
	}
	public Obligation() {

	}
	
}
