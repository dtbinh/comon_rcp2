package au.uq.dke.comon_rcp2.ontology.ui.model;

import java.util.Collection;
import java.util.Map;

import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;

public interface IGraphModel {
	
	//insert
	public GraphNode addNode(Object userObject);
	public GraphArc addArc(Object userObject, GraphNode src, GraphNode dest);
	
	//remove
	
	public void removeNode(Object userObject);
	public void removeArc(Object userObject);

	public void clear();
	
	//find	
	public GraphNode getNode(Object userObject);
	public GraphArc getArc(Object userObject);

	public boolean containsNode(Object userObject);
	public boolean containsArc(Object userObject);
	
	public boolean containsNode(GraphNode node);
	public boolean containsArc(GraphArc arc);
	
	public Collection<GraphNode> getConnectedNodes(Object nodeUserObject);
	//public Collection<GraphArc> getConnectedArcs(Object nodeUserObject);

	public GraphNode getSourceNode(Object arcUserObject);
	public GraphNode getDestinationNode(Object arcUserObject);

	//aggregate methods
	public Collection<GraphNode> getAllNodes();
	public Collection<GraphArc> getAllArcs();	

	public Collection<GraphNode> getVisibleNodes();
	public Collection<GraphArc> getVisibleArcs();

	public Collection<Object> getArcTypes();

	
	//modify : no modify of nodes since they have no real data
	
	//listenr
	public void addGraphModelListener(GraphModelListener listener);
	public void removeGraphModelListener(GraphModelListener listener);

	//arrange
	public void arrangeArcs(GraphNode src, GraphNode dest); 
}
