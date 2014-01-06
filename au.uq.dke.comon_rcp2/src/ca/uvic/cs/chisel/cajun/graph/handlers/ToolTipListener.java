package ca.uvic.cs.chisel.cajun.graph.handlers;

import org.semanticweb.owlapi.model.OWLEntity;

import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.ui.ontology.FrameTooltipNode;
import ca.uvic.cs.chisel.cajun.graph.AbstractGraph;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;

/**
 * Handles input events on the graph.
 * 
 * @author seanf
 */
public class ToolTipListener extends PBasicInputEventHandler {
	private static final int DOUBLE_CLICK = 2;

	private AbstractGraph graph;
	
	private FrameTooltipNode toolTip;
	private DefaultGraphNode currentNode;

	public ToolTipListener(AbstractGraph graph) {
		this.graph = graph;

		PInputEventFilter filter = new PInputEventFilter();
		filter.rejectAllEventTypes();
		filter.setAcceptsMousePressed(true);
		filter.setAcceptsMouseMoved(true);
		
		this.setEventFilter(filter);
	}
	
	public void mouseMoved(PInputEvent event) {
		if(event.getPickedNode() instanceof GraphNode) {
			if(!event.getPickedNode().equals(currentNode)) {
				//showToolTip((DefaultGraphNode)event.getPickedNode());
			}
		}
		else if(currentNode != null) {
			hideToolTip(currentNode);
			currentNode = null;
		}
	}

	public void mousePressed(PInputEvent event) {
		hideToolTip(currentNode);
		
		if (event.isLeftMouseButton()) {
			if(event.getClickCount() == 1 && event.isControlDown()) {
				showToolTip((DefaultGraphNode)event.getPickedNode());
				currentNode = null;
			}
		}
	}
	
	private void hideToolTip(DefaultGraphNode node) {
		PCamera camera = graph.getCanvas().getCamera();
		if(toolTip != null) {
			camera.removeChild(toolTip);
			camera.repaint();
			toolTip = null;
		}
	}
	
	private void showToolTip(DefaultGraphNode node) {
		PCamera camera = graph.getCanvas().getCamera();
		hideToolTip(node);
		
//		toolTip = new FrameTooltipNode(graphModel.getOwlModelManager(), graph, node, (OWLEntity)node.getUserObject());
		
		try{
			camera.addChild(toolTip);
		}catch(NullPointerException e){
			System.out.println("no tool tip");
		}
		camera.repaint();
		
		currentNode = node;
	}

}
