package au.uq.dke.comon_rcp2.database.model.data.resource;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Resource extends BasicRecord	{
	public Resource(String name){
		super(name);
	}
	public Resource() {

	}
	

}
