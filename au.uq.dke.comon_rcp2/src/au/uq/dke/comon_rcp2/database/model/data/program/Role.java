package au.uq.dke.comon_rcp2.database.model.data.program;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Role extends Structure	{
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Program> associcatedActivities = new BasicRecordSet<Program>();
	
	public Set<Program> getAssocicatedActivities() {
		return associcatedActivities;
	}
	public void setAssocicatedActivities(Set<Program> associcatedActivities) {
		this.associcatedActivities = associcatedActivities;
	}
	public String getObligation() {
		return obligation;
	}
	public void setObligation(String obligation) {
		this.obligation = obligation;
	}
	private String obligation;
	
	public Role(String name){
		super(name);
	}
	public Role() {

	}
	

}
