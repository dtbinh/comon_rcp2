package au.uq.dke.comon_rcp2.data.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Client extends BasicRecord	{
	public Client(String name){
		super(name);
	}
	public Client() {

	}
	
}
