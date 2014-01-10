package au.uq.dke.comon_rcp2.database.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class OrganizationalCommitment extends BasicRecord	{
	public OrganizationalCommitment(String name){
		super(name);
	}
	public OrganizationalCommitment() {

	}
	
}
