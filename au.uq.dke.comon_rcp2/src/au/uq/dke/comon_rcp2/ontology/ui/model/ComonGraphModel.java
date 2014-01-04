package au.uq.dke.comon_rcp2.ontology.ui.model;

import java.util.Collection;

import au.uq.dke.comon_rcp2.ontology.ui.model.arc.BasicGraphArc;
import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicGraphNode;
import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;


public class ComonGraphModel  extends DefaultGraphModel implements IGraphModel{

	@Override
	public GraphArc addArc(Object userObject, GraphNode src, GraphNode dest) {
		return super.addArc(userObject, src, dest, null);
	}



	@Override
	public GraphNode addNode(Object userObject) {
		return super.addNode(userObject);
	}

	@Override
	public void removeNode(Object userObject) {
		super.removeNode(userObject);
		
	}

	@Override
	public void removeArc(Object userObject) {
		super.removeArc(userObject);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public GraphNode getNode(Object userObject) {
		return super.getNode(userObject);
	}

	@Override
	public boolean containsNode(GraphNode node) {
		return super.containsNode(node);
	}

	@Override
	public GraphArc getArc(Object userObject) {
		return super.getArc(userObject);
	}

	@Override
	public boolean containsArc(GraphArc arc) {
		return super.containsArc(arc);
	}

	@Override
	public Collection<GraphNode> getConnectedNodes(Object nodeUserObject) {
		return super.getConnectedNodes(nodeUserObject);
	}

	@Override
	public GraphNode getSourceNode(Object arcUserObject) {
		return super.getSourceNode(arcUserObject);
	}

	@Override
	public GraphNode getDestinationNode(Object arcUserObject) {
		return super.getDestinationNode(arcUserObject);
	}

	@Override
	public Collection<GraphNode> getVisibleNodes() {
		return super.getVisibleNodes();
	}

	@Override
	public Collection<GraphArc> getVisibleArcs() {
		return super.getVisibleArcs();
	}

	@Override
	public Collection<Object> getArcTypes() {
		return super.getArcTypes();
	}

	@Override
	public void addGraphModelListener(GraphModelListener listener) {
		super.addGraphModelListener(listener);
	}

	@Override
	public void removeGraphModelListener(GraphModelListener listener) {
		super.removeGraphModelListener(listener);
	}

	@Override
	public void arrangeArcs(GraphNode src, GraphNode dest) {
		super.arrangeArcs(src, dest);
	}

	@Override
	public Collection<GraphNode> getAllNodes() {
		return super.getAllNodes();
	}


	@Override
	public Collection<GraphArc> getAllArcs() {
		return super.getAllArcs();
	}



	@Override
	public boolean containsNode(Object userObject) {
		GraphNode graphNode = new BasicGraphNode(userObject);
		return super.containsNode(graphNode);
	}



	@Override
	public boolean containsArc(Object userObject) {
		GraphArc graphArc = new BasicGraphArc(userObject, null, null);
		return super.containsArc(graphArc);
	}


}
