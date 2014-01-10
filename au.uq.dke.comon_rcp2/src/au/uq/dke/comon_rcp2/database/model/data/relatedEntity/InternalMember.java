package au.uq.dke.comon_rcp2.database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class InternalMember extends Member	{
	public InternalMember(String name){
		super(name);
	}
	public InternalMember() {

	}
	
}
