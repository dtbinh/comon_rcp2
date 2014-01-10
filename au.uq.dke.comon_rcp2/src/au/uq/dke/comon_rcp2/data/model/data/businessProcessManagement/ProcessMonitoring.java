package au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;


@Entity
public class ProcessMonitoring extends BusinessProcessManagement {
	public ProcessMonitoring(){
		
	}
	
	public ProcessMonitoring(String name){
		super(name);
	}

	private Set<ProcessActivity> processActivities =  new BasicRecordSet<ProcessActivity>();


    @ManyToMany(cascade = CascadeType.PERSIST)
	public Set<ProcessActivity> getProcessActivities() {
		return processActivities;
	}

	public void setProcessActivities(Set<ProcessActivity> objectives) {
		this.processActivities = objectives;
	}
	

}
