package ca.uvic.cs.chisel.cajun.graph.handlers;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.List;

import org.semanticweb.owlapi.model.OWLEntity;

import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;
import ca.uvic.cs.chisel.cajun.graph.node.DefaultGraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * Handles zooming.
 * 
 * @author Chris
 * @since 8-Nov-07
 */
public class RotationHandler extends PBasicInputEventHandler {

	public static double ANCHOR_X = -1;
	public static double ANCHOR_Y = -1;

	private double currentX;
	private double currentY;
	private double lastX;
	private double lastY;
	private double deltaRadians;
	private double vecCurrentX;
	private double vecCurrentY;
	private double vecLastX;
	private double vecLastY;
	private IGraphNode anchorIGraphNode;

	public RotationHandler() {
		super();
	}

	@Override
	public void mousePressed(PInputEvent event) {

		// TODO Auto-generated method stub
		// if (event.isMiddleMouseButton()) {
		// if (ANCHOR_X >= 0 && ANCHOR_Y >= 0) {
		// return;
		// }
		// this.ANCHOR_X = event.getPosition().getX();
		// this.ANCHOR_Y = event.getPosition().getY();
		// }
		super.mousePressed(event);
	}

	@Override
	public void mouseDragged(PInputEvent event) {
		if (!event.isMiddleMouseButton()) {
			return;
		}

		//TODO get root
		IGraphNode rootNode = null;
		
		anchorIGraphNode = rootNode;
		ANCHOR_X = ((DefaultGraphNode) anchorIGraphNode).getCenterX();
		ANCHOR_Y = ((DefaultGraphNode) anchorIGraphNode).getCenterY();

		if (this.ANCHOR_X == -1l && this.ANCHOR_Y == -1l) {

		}

		if (this.ANCHOR_X != -1l && this.ANCHOR_Y != -1l) {
			PNode node = event.getPickedNode();
			if (node instanceof IGraphNode) {
				node.moveToFront();
			}
			currentX = event.getPosition().getX();
			currentY = event.getPosition().getY();
			lastX = currentX - event.getDelta().getWidth();
			lastY = currentY - event.getDelta().getHeight();

			vecCurrentX = (currentX - ANCHOR_X);
			vecCurrentY = (currentY - ANCHOR_Y);

			int sign = (vecCurrentX * vecLastY - vecLastX * vecCurrentY) > 0 ? -1
					: 1;

			vecLastX = lastX - ANCHOR_X;
			vecLastY = lastY - ANCHOR_Y;

			double currentLengthPower2 = Math.sqrt(vecCurrentX * vecCurrentX
					+ vecCurrentY * vecCurrentY);
			double lastLengthPower2 = Math.sqrt(vecLastX * vecLastX + vecLastY
					* vecLastY);

			if (currentLengthPower2 == 0 || lastLengthPower2 == 0) {
				return;
			}

			double theta = (vecCurrentX * vecLastX + vecCurrentY * vecLastY)
					/ (currentLengthPower2 * lastLengthPower2);
			deltaRadians = Math.acos(theta);

			AffineTransform rotateTransform = AffineTransform
					.getRotateInstance(sign * deltaRadians, ANCHOR_X, ANCHOR_Y);

			// rotate all visible nodes
			Collection<IGraphNode> visibleNodes = OntologyGraphModelImpl.getInstance().getVisibleNodes();
			List<OWLEntity> desendantsNode = null;
//			List<OWLEntity> desendantsNode = EntryPoint.getGraphModel().getDesendantList(
//					(OWLEntity) anchorIGraphNode.getUserObject(), false);

			for (IGraphNode  IGraphNode : visibleNodes) {
				// if (IGraphNode.getText().contains("Compliance")) {
				{
					if (IGraphNode == null) {
						continue;
					}
					Point2D lastLocation = new Point2D.Double(
							((DefaultGraphNode) IGraphNode).getCenterX(),
							((DefaultGraphNode) IGraphNode).getCenterY());
					Point2D newLocation = new Point2D.Double();
					if (!newLocation.equals(lastLocation)) {
						rotateTransform.transform(lastLocation, newLocation);
						IGraphNode.setLocation(
								newLocation.getX()
										- ((DefaultGraphNode) IGraphNode)
												.getWidth() / 2,
								newLocation.getY()
										- ((DefaultGraphNode) IGraphNode)
												.getHeight() / 2);
					}
				}
			}
//			for (OWLEntity nodeEntity : desendantsNode) {
//				// if (IGraphNode.getText().contains("Compliance")) {
//				{
//					IGraphNode IGraphNode = EntryPoint.getGraphModel().getNode(nodeEntity);
//					if (IGraphNode == null) {
//						continue;
//					}
//					Point2D lastLocation = new Point2D.Double(
//							((DefaultIGraphNode) IGraphNode).getCenterX(),
//							((DefaultIGraphNode) IGraphNode).getCenterY());
//					Point2D newLocation = new Point2D.Double();
//					if (!newLocation.equals(lastLocation)) {
//						rotateTransform.transform(lastLocation, newLocation);
//						IGraphNode.setLocation(
//								newLocation.getX()
//										- ((DefaultIGraphNode) IGraphNode)
//												.getWidth() / 2,
//								newLocation.getY()
//										- ((DefaultIGraphNode) IGraphNode)
//												.getHeight() / 2);
//					}
//				}
//			}

		}
		super.mouseDragged(event);

	}
}
