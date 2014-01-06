package au.uq.dke.comon_rcp2.ontology.graph.model;

import java.util.Collection;
import java.util.Map;

import au.uq.dke.comon_rcp2.ontology.graph.model.facade.IArcUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public interface IOntologyGraphModel {
	
	//insert
	public IGraphNode addNode(INodeUserObject userObject);
	public IGraphArc addArc(IArcUserObject userObject, IGraphNode src, IGraphNode dest);
	
	//remove
	
	public void removeNode(INodeUserObject userObject);
	public void removeArc(IArcUserObject userObject);

	public void clear();
	
	//find	
	public IGraphNode getNode(INodeUserObject userObject);
	public IGraphArc getArc(IArcUserObject userObject);

	public boolean containsNode(INodeUserObject userObject);
	public boolean containsArc(IArcUserObject userObject);
	
	public boolean containsNode(IGraphNode node);
	public boolean containsArc(IGraphArc arc);
	
	public Collection<IGraphNode> getConnectedNodes(INodeUserObject nodeUserObject);
	//public Collection<GraphArc> getConnectedArcs(Object nodeUserObject);

	public IGraphNode getSourceNode(IArcUserObject arcUserObject);
	public IGraphNode getDestinationNode(IArcUserObject arcUserObject);
	
	public Collection<IGraphNode> getChildren(INodeUserObject parentNodeUserObject); 

	//aggregate methods
	public Collection<IGraphNode> getAllNodes();
	public Collection<IGraphArc> getAllArcs();	

	public Collection<IGraphNode> getVisibleNodes();
	public Collection<IGraphArc> getVisibleArcs();

	public Collection<Object> getArcTypes();

	
	//modify : no modify of nodes since they have no real data
	
	//listener
	public void addGraphModelListener(GraphModelListener listener);
	public void removeGraphModelListener(GraphModelListener listener);

	//arrange
	public void arrangeArcs(IGraphNode src, IGraphNode dest); 
}
