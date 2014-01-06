package au.uq.dke.comon_rcp2.ontology.graph.view;

import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;

public class Graph extends FlatGraph{
	
	
	public Graph(OntologyGraphModelImpl graphModel){
		super();
		super.setModel(graphModel);
	}


}
