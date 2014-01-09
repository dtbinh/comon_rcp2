package ca.uvic.cs.chisel.cajun.graph.handlers;

import java.awt.event.InputEvent;

import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.NodeCollection;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;

/**
 * Handles node selection - listens for mouse pressed events on the canvas and
 * updates the selection accordingly.
 * 
 * @author Chris
 * @since 8-Nov-07
 * modified by steve
 */
public class NodeSelectionHandler extends PBasicInputEventHandler {


	private NodeCollection selectedNodes;

	public NodeSelectionHandler(NodeCollection selectedNodes) {
		super();
		this.selectedNodes = selectedNodes;
		PInputEventFilter filter = new PInputEventFilter();
		filter.rejectAllEventTypes();
		filter.setOrMask(InputEvent.BUTTON1_MASK | InputEvent.BUTTON3_MASK);
		filter.setAcceptsMousePressed(true);
		setEventFilter(filter);
	}

	// OVERRIDES

	@Override
	public void mousePressed(PInputEvent e) {
		PNode node = e.getPickedNode();
		if (node instanceof IGraphNode) {
			node.moveToFront();
			nodePressed(e, (IGraphNode) node);
		} else if (node instanceof IGraphArc) {
			node.moveToFront();
			arcPressed(e, (IGraphArc) node);
		} else if (node instanceof PCamera) {
			cameraPressed(e, (PCamera) node);
		}

		super.mousePressed(e);
	}

	private void arcPressed(PInputEvent e, IGraphArc arc) {

	}

	private void cameraPressed(PInputEvent e, PCamera camera) {
		// clear selection
		selectedNodes.clear();
		RotationHandler.ANCHOR_X = -1;
		RotationHandler.ANCHOR_Y = -1;
	}

	private void nodePressed(PInputEvent e, IGraphNode displayNode) {
		if (e.isControlDown()) {
			selectedNodes.addOrRemoveNode(displayNode);
		} else if (e.isShiftDown()) {
			selectedNodes.addNode(displayNode);
		} else {
			if (e.isRightMouseButton()) {
				// right click - only set if the node isn't already selected
				if (!selectedNodes.containsNode(displayNode)) {
					selectedNodes.setNode(displayNode);
				}
			} else {
				// left click - always select just this node
				selectedNodes.setNode(displayNode);
			}
		}
	}

}
