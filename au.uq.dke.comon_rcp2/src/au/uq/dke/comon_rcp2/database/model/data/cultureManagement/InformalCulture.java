package au.uq.dke.comon_rcp2.database.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class InformalCulture extends ComplianceCulture	{
	public InformalCulture(String name){
		super(name);
	}
	public InformalCulture() {

	}
	
}
