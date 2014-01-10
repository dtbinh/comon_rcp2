package au.uq.dke.comon_rcp2.database.model.data.program;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.relatedEntity.Administrator;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.ObligationPriority;
import au.uq.dke.comon_rcp2.database.model.data.relatedType.Status;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ObligationIdentificationAndAssessment extends Program {

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private ObligationPriority priority;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Status status;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Administrator adminsteredBy;

	private String changes;
	private Date changesDate;

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public Date getChangesDate() {
		return changesDate;
	}

	public void setChangesDate(Date changesDate) {
		this.changesDate = changesDate;
	}

	public ObligationIdentificationAndAssessment(String name) {
		super(name);
	}

	public ObligationPriority getPriority() {
		return priority;
	}

	public void setPriority(ObligationPriority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Administrator getAdminsteredBy() {
		return adminsteredBy;
	}

	public void setAdminsteredBy(Administrator adminsteredBy) {
		this.adminsteredBy = adminsteredBy;
	}

	public ObligationIdentificationAndAssessment() {

	}
	
}
