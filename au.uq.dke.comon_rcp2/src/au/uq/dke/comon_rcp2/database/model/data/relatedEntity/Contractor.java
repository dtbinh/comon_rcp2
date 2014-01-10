package au.uq.dke.comon_rcp2.database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Contractor extends BasicRecord	{
	public Contractor(String name){
		super(name);
	}
	public Contractor() {

	}
	
}
