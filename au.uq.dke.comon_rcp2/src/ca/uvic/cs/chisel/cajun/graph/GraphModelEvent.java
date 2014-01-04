package ca.uvic.cs.chisel.cajun.graph;

import java.util.ArrayList;
import java.util.Collection;

import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class GraphModelEvent {

	private Collection<IGraphNode> nodes;
	private Collection<IGraphArc> arcs;

	public GraphModelEvent(Collection<IGraphArc> arcs) {
		this(new ArrayList<IGraphNode>(0), arcs);
	}
	
	public GraphModelEvent(Collection<IGraphNode> nodes, Collection<IGraphArc> arcs) {
		this.nodes = nodes;
		this.arcs = arcs;
	}
	
	public Collection<IGraphNode> getNodes() {
		return nodes;
	}
	
	public Collection<IGraphArc> getArcs() {
		return arcs;
	}
	
	@Override
	public String toString() {
		return "GraphModelEvent: nodes=" + nodes + ", arcs=" + arcs;
	}
	
}
