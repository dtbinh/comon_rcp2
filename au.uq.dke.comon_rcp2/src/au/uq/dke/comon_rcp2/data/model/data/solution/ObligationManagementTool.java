package au.uq.dke.comon_rcp2.data.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ObligationManagementTool extends Tool	{
	public ObligationManagementTool(String name){
		super(name);
	}
	
	public ObligationManagementTool() {

	}
	

}
