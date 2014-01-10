package au.uq.dke.comon_rcp2.database.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ExternalPolicy extends Policy	{
	public ExternalPolicy(String name){
		super(name);
	}
	public ExternalPolicy() {

	}
	
}
