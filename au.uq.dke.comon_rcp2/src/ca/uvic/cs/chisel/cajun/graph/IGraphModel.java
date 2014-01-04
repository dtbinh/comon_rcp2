package ca.uvic.cs.chisel.cajun.graph;

import java.util.Collection;

import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public interface IGraphModel {
	
	/**
	 * Removes all nodes and arcs from the model.
	 * The listeners aren't removed.
	 * Also fires an event.
	 */
	public void clear();
	
	public Collection<IGraphNode> getAllNodes();
	public Collection<IGraphNode> getVisibleNodes();
	public IGraphNode getNode(Object userObject);
	public boolean containsNode(IGraphNode node);
	public Collection<IGraphNode> getConnectedNodes(Object nodeUserObject);
	public Collection<IGraphArc> getArcs(Object nodeUserObject);
	public Collection<Object> getNodeTypes();
	
	public Collection<IGraphArc> getAllArcs();
	public Collection<IGraphArc> getVisibleArcs();
	public IGraphArc getArc(Object userObject);
	public boolean containsArc(IGraphArc arc);
	public IGraphNode getSourceNode(Object arcUserObject);
	public IGraphNode getDestinationNode(Object arcUserObject);
	public Collection<Object> getArcTypes();
	
	public void addGraphModelListener(GraphModelListener listener);
	public void removeGraphModelListener(GraphModelListener listener);
	
}
