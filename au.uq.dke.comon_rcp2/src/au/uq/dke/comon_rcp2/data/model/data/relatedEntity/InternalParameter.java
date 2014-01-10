package au.uq.dke.comon_rcp2.data.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class InternalParameter extends Parameter	{
	public InternalParameter(String name){
		super(name);
	}
	
	public InternalParameter() {

	}
	
}
