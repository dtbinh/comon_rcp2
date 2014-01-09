package au.uq.dke.comon_rcp2.ontology.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;

@Entity
public class OntologyClass extends OntologyItem implements INodeUserObject{
	
	public String name = null;
	
	public OntologyClass(){
		
	}
	
	public OntologyClass(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Transient
	public String getText() {
		// TODO Auto-generated method stub
		return this.getName();
	}

}
