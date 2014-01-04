package ca.uvic.cs.chisel.cajun.actions;

import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.IGraph;
import ca.uvic.cs.chisel.cajun.graph.IGraphModel;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import ca.uvic.cs.chisel.cajun.resources.ResourceHandler;

public class ClearOrphansAction extends CajunAction {
	private static final long serialVersionUID = 2406231898001180745L;

	private static final String ACTION_NAME = "Remove Orphan Nodes";
	
	private DefaultGraphModel model;
	private IGraph graph;
	
	public ClearOrphansAction(IGraphModel model, IGraph graph) {
		super(ACTION_NAME, ResourceHandler.getIcon("chart_line_delete.png"));
		this.model = (DefaultGraphModel)model;
		this.graph = graph;
	}
	
	@Override
	public void doAction() {
		boolean graphChanged = false;
		
		IGraphNode nodes[] = model.getAllNodes().toArray(new IGraphNode[model.getAllNodes().size()]);
		
		// goes through all nodes and hides any that have no arcs or have no visible arcs
		for(IGraphNode node : nodes) {
			if(node.getArcs().size() == 0) {
				graphChanged = true;
				model.removeNode(node.getUserObject());
			}
			else {
				boolean found = false;
				for(IGraphArc arc : node.getArcs()) {
					if(arc.isVisible()) {
						found = true;
					}
				}
				if(!found) {
					graphChanged = true;
					model.removeNode(node.getUserObject());
				}
			}
		}
		
		// re-layout the graph if it changed
		if(graphChanged) graph.performLayout();
	}
}
