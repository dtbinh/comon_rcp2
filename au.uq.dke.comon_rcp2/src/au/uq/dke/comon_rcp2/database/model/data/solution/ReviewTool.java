package au.uq.dke.comon_rcp2.database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ReviewTool extends Tool	{
	public ReviewTool(String name){
		super(name);
	}
	
	public ReviewTool() {

	}
	

}
