package au.uq.dke.comon_rcp2.database.model.data.resource;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ResistanceManagementCost extends NonMonetaryResource	{
	public ResistanceManagementCost(String name){
		super(name);
	}
	public ResistanceManagementCost() {

	}
	

}
