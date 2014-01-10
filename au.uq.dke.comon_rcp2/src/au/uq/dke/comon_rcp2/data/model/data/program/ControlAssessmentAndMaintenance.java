package au.uq.dke.comon_rcp2.data.model.data.program;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.data.model.data.relatedType.ControlAssessmentType;
import au.uq.dke.comon_rcp2.data.model.data.relatedType.Status;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlAssessmentAndMaintenance extends ControlAndMonitoring	{
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private ControlAssessmentType assessmentType;
	
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Status status;
	
	public ControlAssessmentType getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(ControlAssessmentType assessmentType) {
		this.assessmentType = assessmentType;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMaintenanceTask() {
		return maintenanceTask;
	}
	public void setMaintenanceTask(String maintenanceTask) {
		this.maintenanceTask = maintenanceTask;
	}
	private String maintenanceTask;
	
	
	public ControlAssessmentAndMaintenance(String name){
		super(name);
	}
	public ControlAssessmentAndMaintenance() {

	}
	

}
