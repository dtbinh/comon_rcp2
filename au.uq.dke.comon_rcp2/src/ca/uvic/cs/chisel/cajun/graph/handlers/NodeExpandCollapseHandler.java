package ca.uvic.cs.chisel.cajun.graph.handlers;

import java.util.Collection;

import au.uq.dke.comon_rcp2.application.views.graph.OntologyGraph;
import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModel;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class NodeExpandCollapseHandler extends PBasicInputEventHandler {
	private static final int DOUBLE_CLICK = 2;

	public void mousePressed(PInputEvent event) {

		if (event.isLeftMouseButton()) {
			if (event.getClickCount() == DOUBLE_CLICK) {
				if (event.getPickedNode() instanceof IGraphNode) {
					expandCollapseNode((IGraphNode) event.getPickedNode());
				}
			}
		}
	}

	/**
	 * Expands a node if it is not already expanded, otherwise it collapses it.
	 * 
	 * @param IGraphNode
	 *            The node to expand or collapse.
	 */
	private void expandCollapseNode(IGraphNode parentGraphNode) {
		parentGraphNode.setHighlighted(false);
		parentGraphNode.moveToFront();

		boolean isExpanded = false;// if any one of the children is visible,
									// then it is expanded

		Collection<IGraphNode> children = OntologyGraphModel.getInstance()
				.getChildren((INodeUserObject) parentGraphNode.getUserObject());
		
		for(IGraphNode child : children){
			if(child.isVisible() == true){
				isExpanded = true;
				break;
			}
		}
		
		if(isExpanded == true){
			for(IGraphNode child : children){
				child.setVisible(false);
			}
		}else{
			for(IGraphNode child : children){
				child.setVisible(true);
			}
		}
		
		OntologyGraph.getInstance().performLayoutWithoutFilter();

		return;
	}

}
