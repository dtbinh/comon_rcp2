package ca.uvic.cs.chisel.cajun.graph;

import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class GraphModelAdapter implements GraphModelListener {
	
	public void graphCleared() {
	}
	
	public void graphArcAdded(IGraphArc arc) {
	}

	public void graphArcRemoved(IGraphArc arc) {
	}

	public void graphNodeAdded(IGraphNode node) {
	}

	public void graphNodeRemoved(IGraphNode node) {
	}

	public void graphNodeTypeAdded(Object nodeType) {
	}
	
	public void graphArcTypeAdded(Object arcType) {
	}
	
}
