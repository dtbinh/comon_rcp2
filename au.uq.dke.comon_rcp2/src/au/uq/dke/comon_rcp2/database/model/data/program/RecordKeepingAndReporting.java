package au.uq.dke.comon_rcp2.database.model.data.program;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RecordKeepingAndReporting extends Program {

	private String coverage;
	private Timestamp lodgementStartTime;
	private Timestamp lodgementEndTime;
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	public Timestamp getLodgementStartTime() {
		return lodgementStartTime;
	}
	public void setLodgementStartTime(Timestamp lodgementStartTime) {
		this.lodgementStartTime = lodgementStartTime;
	}
	public Timestamp getLodgementEndTime() {
		return lodgementEndTime;
	}
	public void setLodgementEndTime(Timestamp lodgementEndTime) {
		this.lodgementEndTime = lodgementEndTime;
	}
	public String getSubmissionTo() {
		return submissionTo;
	}
	public void setSubmissionTo(String submissionTo) {
		this.submissionTo = submissionTo;
	}
	public Set<Role> getAffectedRoles() {
		return affectedRoles;
	}
	public void setAffectedRoles(Set<Role> affectedRoles) {
		this.affectedRoles = affectedRoles;
	}
	private String submissionTo;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();

	public RecordKeepingAndReporting(String name) {
		super(name);
	}
	public RecordKeepingAndReporting() {

	}
	
}
