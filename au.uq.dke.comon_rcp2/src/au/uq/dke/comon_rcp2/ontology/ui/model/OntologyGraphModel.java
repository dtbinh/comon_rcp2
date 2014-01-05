package au.uq.dke.comon_rcp2.ontology.ui.model;

import java.util.Collection;

import au.uq.dke.comon_rcp2.ontology.ui.model.arc.BasicGraphArc;
import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicGraphNode;
import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;


public class OntologyGraphModel  extends DefaultGraphModel implements IGraphModel{

	@Override
	public IGraphArc addArc(Object userObject, IGraphNode src, IGraphNode dest) {
		return super.addArc(userObject, src, dest);
	}


	@Override
	public IGraphNode addNode(Object userObject) {
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
	public IGraphNode getNode(Object userObject) {
		return super.getNode(userObject);
	}

	@Override
	public boolean containsNode(IGraphNode node) {
		return super.containsNode(node);
	}

	@Override
	public IGraphArc getArc(Object userObject) {
		return super.getArc(userObject);
	}

	@Override
	public boolean containsArc(IGraphArc arc) {
		return super.containsArc(arc);
	}

	@Override
	public Collection<IGraphNode> getConnectedNodes(Object nodeUserObject) {
		return super.getConnectedNodes(nodeUserObject);
	}

	@Override
	public IGraphNode getSourceNode(Object arcUserObject) {
		return super.getSourceNode(arcUserObject);
	}

	@Override
	public IGraphNode getDestinationNode(Object arcUserObject) {
		return super.getDestinationNode(arcUserObject);
	}

	@Override
	public Collection<IGraphNode> getVisibleNodes() {
		return super.getVisibleNodes();
	}

	@Override
	public Collection<IGraphArc> getVisibleArcs() {
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
	public void arrangeArcs(IGraphNode src, IGraphNode dest) {
		super.arrangeArcs(src, dest);
	}

	@Override
	public Collection<IGraphNode> getAllNodes() {
		return super.getAllNodes();
	}


	@Override
	public Collection<IGraphArc> getAllArcs() {
		return super.getAllArcs();
	}



	@Override
	public boolean containsNode(Object userObject) {
		IGraphNode graphNode = new BasicGraphNode(userObject);
		return super.containsNode(graphNode);
	}



	@Override
	public boolean containsArc(Object userObject) {
		IGraphArc graphArc = new BasicGraphArc(userObject, null, null);
		return super.containsArc(graphArc);
	}


	@Override
	public Collection<IGraphNode> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}


}
