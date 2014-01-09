package au.uq.dke.comon_rcp2.intergratingtest;

import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;
import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelation;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelationType;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class GraphLayoutTest {
	OntologyGraphModelImpl graphModel = OntologyGraphModelImpl.getInstance();
	
	public void populateMockData() {
	    OntologyClass srcObject = new OntologyClass("src");
	    OntologyClass dstObject = new OntologyClass("dst");
	    OntologyRelationType relType = new OntologyRelationType("has subclass");
	    
	    OntologyRelation rel = new OntologyRelation (srcObject, dstObject, relType);
	    
	    IGraphNode srcNode = graphModel.addNode(srcObject);
	    IGraphNode dstNode = graphModel.addNode(dstObject);
	    graphModel.addArc(rel, srcNode, dstNode);
	    graphModel.generateTreeInfo();

		
	}
}
