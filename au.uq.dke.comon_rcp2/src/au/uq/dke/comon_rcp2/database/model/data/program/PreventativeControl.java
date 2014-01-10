package au.uq.dke.comon_rcp2.database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PreventativeControl extends ControlIdentificationAndDefinition	{


	public PreventativeControl(String name){
		super(name);
	}
	public PreventativeControl() {

	}
	
	
}

