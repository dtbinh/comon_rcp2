package au.uq.dke.comon_rcp2.ontology.ui.view;

import au.uq.dke.comon_rcp2.ontology.ui.model.OntologyGraphModel;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;

public class Graph extends FlatGraph{
	
	
	public Graph(OntologyGraphModel graphModel){
		super();
		super.setModel(graphModel);
	}


}
