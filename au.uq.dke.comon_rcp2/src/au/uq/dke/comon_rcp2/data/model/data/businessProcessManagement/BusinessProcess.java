package au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BusinessProcess extends BusinessProcessManagement	{
	
	public BusinessProcess(){
		
	}
	
	public BusinessProcess(String name){
		super(name);
	}

}
