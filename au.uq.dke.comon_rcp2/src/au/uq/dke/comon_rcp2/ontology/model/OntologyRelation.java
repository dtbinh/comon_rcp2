package au.uq.dke.comon_rcp2.ontology.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import au.uq.dke.comon_rcp2.ontology.graph.model.facade.IArcUserObject;

@Entity
public class OntologyRelation extends OntologyItem implements IArcUserObject{
	

	@Override
	@Transient
	public String getLabel() {
		// TODO Auto-generated method stub
		return "type";
	}
	
	public OntologyRelation(){
		
	}
	
	public OntologyRelation(OntologyClass srcClass, OntologyClass dstClass, RelationType relationType){
		this.srcClass = srcClass;
		this.dstClass = dstClass;
		this.relationType = relationType;
	}
	

	@OneToOne
	public OntologyClass getSrcClass() {
		return srcClass;
	}

	public void setSrcClass(OntologyClass srcClass) {
		this.srcClass = srcClass;
	}

	@OneToOne
	public OntologyClass getDstClass() {
		return dstClass;
	}

	public void setDstClass(OntologyClass dstClass) {
		this.dstClass = dstClass;
	}

	@OneToOne
	public RelationType getRelationType() {
		return relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	OntologyClass srcClass;
	
	OntologyClass dstClass;
	
	RelationType relationType;

}
