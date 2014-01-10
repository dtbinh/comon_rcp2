package au.uq.dke.comon_rcp2.database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class DetectiveControl extends ControlIdentificationAndDefinition	{
	public DetectiveControl(String name){
		super(name);
	}

	public DetectiveControl() {

	}
	
}
