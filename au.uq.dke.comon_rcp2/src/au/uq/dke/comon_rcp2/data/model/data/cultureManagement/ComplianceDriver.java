package au.uq.dke.comon_rcp2.data.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ComplianceDriver extends OrganizationalCommitment	{
	public ComplianceDriver(String name){
		super(name);
	}

	public ComplianceDriver() {

	}
	
}
