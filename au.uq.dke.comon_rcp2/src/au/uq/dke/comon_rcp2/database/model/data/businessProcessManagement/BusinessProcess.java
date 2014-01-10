package au.uq.dke.comon_rcp2.database.model.data.businessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BusinessProcess extends BusinessProcessManagement	{
	
	public BusinessProcess(){
		
	}
	
	public BusinessProcess(String name){
		super(name);
	}

}
