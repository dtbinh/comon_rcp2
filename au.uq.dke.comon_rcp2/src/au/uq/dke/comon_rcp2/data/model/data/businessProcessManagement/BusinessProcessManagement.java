package au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecord;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BusinessProcessManagement extends BasicRecord	{
	public BusinessProcessManagement(){
		
	}
	
	public BusinessProcessManagement(String name){
		super(name);
	}

}
