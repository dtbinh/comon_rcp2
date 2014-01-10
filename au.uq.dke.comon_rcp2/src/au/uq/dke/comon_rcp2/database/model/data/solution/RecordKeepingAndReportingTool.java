package au.uq.dke.comon_rcp2.database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class RecordKeepingAndReportingTool extends Tool	{
	public RecordKeepingAndReportingTool(String name){
		super(name);
	}
	
	public RecordKeepingAndReportingTool() {

	}
	

}
