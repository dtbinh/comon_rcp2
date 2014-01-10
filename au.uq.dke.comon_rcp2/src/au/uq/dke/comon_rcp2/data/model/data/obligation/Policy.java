package au.uq.dke.comon_rcp2.data.model.data.obligation;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.GoverningBody;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Policy extends MandatoryObligation {
	private String versionNumber;
	

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<GoverningBody> governingBodies =  new BasicRecordSet<GoverningBody>();

	public Policy(String name) {
		super(name);
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Set<GoverningBody> getGoverningBodies() {
		return governingBodies;
	}

	public void setGoverningBodies(Set<GoverningBody> governingBodies) {
		this.governingBodies = governingBodies;
	}
	public Policy() {

	}
	
}
