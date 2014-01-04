package au.uq.dke.comon_rcp2.ontology.ui.model;

import java.util.Collection;
import java.util.Map;

import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public interface IGraphModel {
	
	//insert
	public IGraphNode addNode(Object userObject);
	public IGraphArc addArc(Object userObject, IGraphNode src, IGraphNode dest);
	
	//remove
	
	public void removeNode(Object userObject);
	public void removeArc(Object userObject);

	public void clear();
	
	//find	
	public IGraphNode getNode(Object userObject);
	public IGraphArc getArc(Object userObject);

	public boolean containsNode(Object userObject);
	public boolean containsArc(Object userObject);
	
	public boolean containsNode(IGraphNode node);
	public boolean containsArc(IGraphArc arc);
	
	public Collection<IGraphNode> getConnectedNodes(Object nodeUserObject);
	//public Collection<GraphArc> getConnectedArcs(Object nodeUserObject);

	public IGraphNode getSourceNode(Object arcUserObject);
	public IGraphNode getDestinationNode(Object arcUserObject);

	//aggregate methods
	public Collection<IGraphNode> getAllNodes();
	public Collection<IGraphArc> getAllArcs();	

	public Collection<IGraphNode> getVisibleNodes();
	public Collection<IGraphArc> getVisibleArcs();

	public Collection<Object> getArcTypes();

	
	//modify : no modify of nodes since they have no real data
	
	//listenr
	public void addGraphModelListener(GraphModelListener listener);
	public void removeGraphModelListener(GraphModelListener listener);

	//arrange
	public void arrangeArcs(IGraphNode src, IGraphNode dest); 
}
