package au.uq.dke.comon_rcp2.database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Structure extends Program	{
	public Structure(String name){
		super(name);
	}

	public Structure() {

	}
	
}
