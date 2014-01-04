package ca.uvic.cs.chisel.cajun.graph;

import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

/**
 * Handles events relating to a {@link IGraphModel}.
 *
 * @author Chris
 * @since  9-Nov-07
 */
public interface GraphModelListener {

	public void graphCleared();
	
	public void graphNodeAdded(IGraphNode node);
	public void graphNodeRemoved(IGraphNode node);

	public void graphArcAdded(IGraphArc arc);
	public void graphArcRemoved(IGraphArc arc);
	
	public void graphNodeTypeAdded(Object nodeType);
	public void graphArcTypeAdded(Object arcType);
	
}
