package au.uq.dke.comon_rcp2.ontology.model;

import javax.persistence.Entity;

@Entity
public class RelationType extends OntologyItem {

	public String type;

	public RelationType(){
		
	}
	
	public RelationType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
