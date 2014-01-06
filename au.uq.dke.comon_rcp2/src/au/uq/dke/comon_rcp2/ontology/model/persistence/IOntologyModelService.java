package au.uq.dke.comon_rcp2.ontology.model.persistence;

import java.util.Collection;

import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelation;
import au.uq.dke.comon_rcp2.ontology.model.RelationType;

public interface IOntologyModelService {

	public Collection<OntologyClass> getAllOntologyClasses();
	
	public Collection<OntologyRelation> getAllOntologyRelations();
	
	public Collection<RelationType> getAllRelationTypes();
}
