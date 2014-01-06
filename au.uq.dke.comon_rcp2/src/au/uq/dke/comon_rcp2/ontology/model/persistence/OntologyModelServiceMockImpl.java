package au.uq.dke.comon_rcp2.ontology.model.persistence;

import java.util.Collection;

import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelation;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelationType;

public class OntologyModelServiceMockImpl implements IOntologyModelService{
	
	private static IOntologyModelService instance = null;
	private OntologyModelServiceMockImpl(){
		
	}
	
	public static IOntologyModelService getInstance(){
		if(instance == null){
			instance = new OntologyModelServiceMockImpl();
		}
		return instance;
	}

	@Override
	public Collection<OntologyClass> getAllOntologyClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OntologyRelation> getAllOntologyRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OntologyRelationType> getAllRelationTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
