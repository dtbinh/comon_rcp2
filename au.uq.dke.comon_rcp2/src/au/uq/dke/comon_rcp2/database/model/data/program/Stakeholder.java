package au.uq.dke.comon_rcp2.database.model.data.program;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.database.model.data.relatedType.StakeholderType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Stakeholder extends Structure	{
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private StakeholderType stakeholderType;
	
	public StakeholderType getStakeholderType() {
		return stakeholderType;
	}
	public void setStakeholderType(StakeholderType stakeholderType) {
		this.stakeholderType = stakeholderType;
	}
	private String obligation;
	
	public String getObligation() {
		return obligation;
	}
	public void setObligation(String obligation) {
		this.obligation = obligation;
	}
	public Stakeholder(String name){
		super(name);
	}
	public Stakeholder() {

	}
	

}
