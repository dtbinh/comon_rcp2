package au.uq.dke.comon_rcp2.ontology.model;

import javax.persistence.Entity;

@Entity
public class OntologyRelationType extends OntologyItem {

	public String type;

	public OntologyRelationType(){
		
	}
	
	public OntologyRelationType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
