package au.uq.dke.comon_rcp2.database.model.data.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.database.model.data.businessProcessManagement.BusinessProcess;
import au.uq.dke.comon_rcp2.database.model.data.program.Role;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class NonMonetaryResource extends Resource	{

	private String obligation;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> associatedProcess = new BasicRecordSet<BusinessProcess>();

	public String getObligation() {
		return obligation;
	}
	public void setObligation(String obligation) {
		this.obligation = obligation;
	}
	public Set<BusinessProcess> getAssociatedProcess() {
		return associatedProcess;
	}
	public void setAssociatedProcess(Set<BusinessProcess> associatedProcess) {
		this.associatedProcess = associatedProcess;
	}
	public Set<Role> getAffectedRoles() {
		return affectedRoles;
	}
	public void setAffectedRoles(Set<Role> affectedRoles) {
		this.affectedRoles = affectedRoles;
	}
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();

	
	public NonMonetaryResource(String name){
		super(name);
	}
	public NonMonetaryResource() {

	}
	

}
