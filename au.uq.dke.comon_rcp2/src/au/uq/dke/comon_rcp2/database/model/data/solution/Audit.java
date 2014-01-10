package au.uq.dke.comon_rcp2.database.model.data.solution;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.Auditor;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Audit extends Service	{
	
	public Auditor getAuditor() {
		return auditor;
	}
	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}
	public Serializable getDocumentID() {
		return documentID;
	}
	public void setDocumentID(Serializable documentID) {
		this.documentID = documentID;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Auditor auditor;
	
	private Serializable documentID;
	
	public Audit(String name){
		super(name);
	}
	public Audit() {

	}
	
}
