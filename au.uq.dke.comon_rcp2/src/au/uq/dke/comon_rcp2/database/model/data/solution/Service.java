package au.uq.dke.comon_rcp2.database.model.data.solution;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.database.model.data.obligation.Obligation;
import au.uq.dke.comon_rcp2.database.model.data.program.Role;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.ServiceStatus;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Service extends Solution	{
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> associatedObligations = new BasicRecordSet<Obligation>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> associatedRoles= new BasicRecordSet<Role>();
	
	public Set<Obligation> getAssociatedObligations() {
		return associatedObligations;
	}

	public void setAssociatedObligations(Set<Obligation> associatedObligations) {
		this.associatedObligations = associatedObligations;
	}

	public Set<Role> getAssociatedRoles() {
		return associatedRoles;
	}

	public void setAssociatedRoles(Set<Role> associatedRoles) {
		this.associatedRoles = associatedRoles;
	}

	public ServiceStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceStatus status) {
		this.status = status;
	}

	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private ServiceStatus status;
	
	public Service(String name){
		super(name);
	}
	
	public Service(){
		
	}
	
	

}
