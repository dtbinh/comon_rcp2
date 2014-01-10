package au.uq.dke.comon_rcp2.data.model.data.obligation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.Administrator;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.GoverningBody;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.Regulator;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Regulation extends MandatoryObligation	{
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Administrator administeredBy;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private GoverningBody governingBody;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Regulator regulator;
	
	public Regulation(String name){
		super(name);
	}

	public Administrator getAdministeredBy() {
		return administeredBy;
	}

	public void setAdministeredBy(Administrator administeredBy) {
		this.administeredBy = administeredBy;
	}

	public GoverningBody getGoverningBody() {
		return governingBody;
	}

	public void setGoverningBody(GoverningBody governingBody) {
		this.governingBody = governingBody;
	}

	public Regulator getRegulator() {
		return regulator;
	}

	public void setRegulator(Regulator regulator) {
		this.regulator = regulator;
	}
	public Regulation() {

	}
	

}
