package au.uq.dke.comon_rcp2.database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class GoverningBody extends Body	{
	public GoverningBody(String name){
		super(name);
	}
	public GoverningBody() {

	}
	
}
