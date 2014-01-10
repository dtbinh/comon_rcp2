package au.uq.dke.comon_rcp2.database.model.data.program;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends Program {
	private String changes;
	private Timestamp date;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();
	
	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Set<Role> getAffectedRoles() {
		return affectedRoles;
	}
	public void setAffectedRoles(Set<Role> affectedRoles) {
		this.affectedRoles = affectedRoles;
	}
	public Committee getCommittee() {
		return committee;
	}
	public void setCommittee(Committee committee) {
		this.committee = committee;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Committee committee;

	public Review(String name) {
		super(name);
	}
	public Review() {

	}
	

}
