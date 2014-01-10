package au.uq.dke.comon_rcp2.data.model.data.obligation;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.Area;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.GoverningBody;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.IndustrySector;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CodeOfPractice extends VoluntaryObligation	{
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Area> applicableAreas =  new BasicRecordSet<Area>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<IndustrySector> applicableIndustrySectors =  new BasicRecordSet<IndustrySector>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<GoverningBody> governingBodies=  new BasicRecordSet<GoverningBody>();
	
	public CodeOfPractice(String name){
		super(name);
	}

	public Set<Area> getApplicableAreas() {
		return applicableAreas;
	}

	public void setApplicableAreas(Set<Area> applicableAreas) {
		this.applicableAreas = applicableAreas;
	}

	public Set<IndustrySector> getApplicableIndustrySectors() {
		return applicableIndustrySectors;
	}

	public void setApplicableIndustrySectors(
			Set<IndustrySector> applicableIndustrySectors) {
		this.applicableIndustrySectors = applicableIndustrySectors;
	}

	public Set<GoverningBody> getGoverningBodies() {
		return governingBodies;
	}

	public void setGoverningBodies(Set<GoverningBody> governingBodies) {
		this.governingBodies = governingBodies;
	}
	public CodeOfPractice() {

	}
	
}
