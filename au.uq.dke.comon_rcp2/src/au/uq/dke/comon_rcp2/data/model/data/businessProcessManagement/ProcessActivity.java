package au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;


@Entity
public class ProcessActivity extends BusinessProcess {
	
	public ProcessActivity(){
		
	}
	
	public ProcessActivity(String name){
		super(name);
	}

	private Set<ProcessObjective> processObjectives =  new BasicRecordSet<ProcessObjective>();


    @ManyToMany(cascade = CascadeType.PERSIST)
	public Set<ProcessObjective> getProcessObjectives() {
		return processObjectives;
	}

	public void setProcessObjectives(Set<ProcessObjective> objectives) {
		this.processObjectives = objectives;
	}


}
