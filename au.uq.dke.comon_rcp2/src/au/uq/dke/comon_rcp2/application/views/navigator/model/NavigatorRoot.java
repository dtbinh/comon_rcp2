package au.uq.dke.comon_rcp2.application.views.navigator.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.PlatformObject;

import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModel;

public class NavigatorRoot extends PlatformObject {
	
	
	
	public Collection getOntologyRoots(){
		
		OntologyGraphModel ontologyGraphModel = OntologyGraphModel.getInstance();
		
		
		Collection roots = new ArrayList();
		roots.add(ontologyGraphModel.getRootGraphNode());
		
		return roots;
	}

}
