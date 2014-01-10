package au.uq.dke.comon_rcp2.database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Program extends BasicRecord {
	public Program(String name){
		super(name);
	}

	public Program() {

	}
	
}
