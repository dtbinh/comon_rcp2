package au.uq.dke.comon_rcp2.data.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ExternalParameter extends Parameter	{
	public ExternalParameter(String name){
		super(name);
	}
	
	public ExternalParameter() {

	}
	
}
