package au.uq.dke.comon_rcp2.database.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CostType extends BasicRecord	{
	public CostType(String name){
		super(name);
	}
	public CostType() {

	}
	
}
