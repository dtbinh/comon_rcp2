package au.uq.dke.comon_rcp2.database.model.data.cultureManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.database.model.data.BasicRecord;
import au.uq.dke.comon_rcp2.database.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.database.model.data.obligation.Obligation;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CultureManagement extends BasicRecord	{
	public CultureManagement(String name){
		super(name);
	}
	
    @ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> obligations =  new BasicRecordSet<Obligation>();

	public Set<Obligation> getObligations() {
		return obligations;
	}

	public void setObligations(Set<Obligation> obligations) {
		this.obligations = obligations;
	}
	public CultureManagement() {

	}
	


}
