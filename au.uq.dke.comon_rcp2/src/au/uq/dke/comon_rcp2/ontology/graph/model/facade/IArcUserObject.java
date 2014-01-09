package au.uq.dke.comon_rcp2.ontology.graph.model.facade;

import javax.persistence.OneToOne;

import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;

public interface IArcUserObject {
	public String getLabel();
	
	public OntologyClass getSrcClass();
	public OntologyClass getDstClass();

}
