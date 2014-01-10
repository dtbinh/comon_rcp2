package au.uq.dke.comon_rcp2.database.model.data.program;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import au.uq.dke.comon_rcp2.database.model.data.relatedType.ControlRank;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlSelection extends ControlAndMonitoring	{
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private ControlIdentificationAndDefinition control;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private ControlRank controlRank;
	
	
	
	public ControlIdentificationAndDefinition getControl() {
		return control;
	}

	public void setControl(ControlIdentificationAndDefinition control) {
		this.control = control;
	}

	public ControlRank getControlRank() {
		return controlRank;
	}

	public void setControlRank(ControlRank controlRank) {
		this.controlRank = controlRank;
	}

	public ControlSelection(String name){
		super(name);
	}
	
	public ControlSelection() {

	}
	

}
