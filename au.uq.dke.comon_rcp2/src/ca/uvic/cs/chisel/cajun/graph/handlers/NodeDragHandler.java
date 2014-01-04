package ca.uvic.cs.chisel.cajun.graph.handlers;

import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * Handles dragging {@link IGraphNode}s around the canvas.
 * 
 * @author Chris
 * @since  9-Nov-07
 */
public class NodeDragHandler extends PDragEventHandler {
	public NodeDragHandler() {
		super();
		setMoveToFrontOnPress(true);
	}

	@Override
	public void processEvent(PInputEvent event, int type) {
		super.processEvent(event, type);
	}

	// OVERRIDES

	@Override
	protected void startDrag(PInputEvent e) {
		super.startDrag(e);
		e.setHandled(true);
	}

	@Override
	protected void drag(PInputEvent e) {
		//super.drag(e);
		PNode pnode = getDraggedNode();
		if (pnode instanceof IGraphNode) {
			// MOVE node
			IGraphNode graphNode = (IGraphNode) pnode;
			PDimension d = e.getDeltaRelativeTo(pnode);
			pnode.localToParent(d);
			double dx = d.getWidth();
			double dy = d.getHeight();
			graphNode.setLocation(pnode.getX() + dx, pnode.getY() + dy);
		}
	}

}
