package ca.uvic.cs.chisel.cajun.graph.handlers;


import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class NodeExpandCollapseListener extends PBasicInputEventHandler {
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
	private void expandCollapseNode(IGraphNode IGraphNode) {
		IGraphNode.setHighlighted(false);
		IGraphNode.moveToFront();


		boolean isExpanded = false;// if any one of the children is visible,
									// then it is expanded
		
		return;
	}

}
