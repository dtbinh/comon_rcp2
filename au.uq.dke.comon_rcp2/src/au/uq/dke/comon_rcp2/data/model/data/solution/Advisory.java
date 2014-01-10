package au.uq.dke.comon_rcp2.data.model.data.solution;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.Consultant;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Advisory extends Service	{

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Consultant consultant;
	public Consultant getConsultant() {
		return consultant;
	}
	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}
	public Advisory(String name){
		super(name);
	}
	public Advisory() {

	}
	

}
