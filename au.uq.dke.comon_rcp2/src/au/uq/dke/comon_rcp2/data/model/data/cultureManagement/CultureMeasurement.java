package au.uq.dke.comon_rcp2.data.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CultureMeasurement extends CultureManagement	{
	public CultureMeasurement(String name){
		super(name);
	}
	public CultureMeasurement() {

	}
	
}
