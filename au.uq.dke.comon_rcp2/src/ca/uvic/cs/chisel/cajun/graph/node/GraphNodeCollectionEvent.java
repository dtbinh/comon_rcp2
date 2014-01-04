package ca.uvic.cs.chisel.cajun.graph.node;

import java.util.Collection;

/**
 * Contains the added nodes and removed nodes from a {@link NodeCollection}.
 *
 * @author Chris
 * @since  20-Nov-07
 */
public class GraphNodeCollectionEvent {

	private NodeCollection nodeCollection;
	private Collection<IGraphNode> newNodes;
	private Collection<IGraphNode> oldNodes;
	
	public GraphNodeCollectionEvent(NodeCollection nodeCollection, Collection<IGraphNode> oldNodes, Collection<IGraphNode> newNodes) {
		this.nodeCollection = nodeCollection;
		this.oldNodes = oldNodes;
		this.newNodes = newNodes;
	}
	
	public NodeCollection getNodeCollection() {
		return nodeCollection;
	}
	
	public Collection<IGraphNode> getNewNodes() {
		return newNodes;
	}
	
	public Collection<IGraphNode> getOldNodes() {
		return oldNodes;
	}

	@Override
	public String toString() {
		return "GraphNodeCollectionEvent: " + oldNodes + " -> " + newNodes;
	}
}
