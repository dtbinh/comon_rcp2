package au.uq.dke.comon_rcp2.database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Solution extends BasicRecord	{
	public Solution(String name){
		super(name);
	}
	public Solution(){
		
	}
	

}
