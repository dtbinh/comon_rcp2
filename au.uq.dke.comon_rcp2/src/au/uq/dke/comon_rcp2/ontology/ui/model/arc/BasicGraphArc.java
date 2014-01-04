package au.uq.dke.comon_rcp2.ontology.ui.model.arc;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import org.eclipse.zest.layouts.LayoutBendPoint;
import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.constraints.LayoutConstraint;

import au.uq.dke.comon_rcp2.ontology.ui.model.arc.arrowhead.ArrowHead;
import au.uq.dke.comon_rcp2.ontology.ui.model.arc.label.ArcLabel;
import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicGraphNode;
import au.uq.dke.comon_rcp2.ontology.ui.style.BasicGraphArcStyle;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArcStyle;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PPaintContext;

public class BasicGraphArc extends PPath implements GraphArc {
	private static final long serialVersionUID = 1530720146193007435L;

	private static final double CURVE_FACTOR_BASE_OFFSET = 8.0;
	private static final double SELF_ARC_DIAMETER = 20.0;

	private final Object userObject;
	private Object type;

	private Object graphData;

	private GraphNode src;
	private GraphNode dest;

	private Object layoutObject;

	private boolean selected;
	private boolean highlighted;

	private GraphArcStyle style;

	// private GeneralPath path;
	private int curveFactor = 0;

	private boolean showArrowHead;
	private ArrowHead endArrowHead;
	private ArrowHead startArrowHead;
	private String tooltip;

	private boolean inverted;

	//private Icon icon;
	private PImage image;

	private ArcLabel arcLabel;

	private Point2D middlePoint;

	public Point2D getMiddlePoint() {
		return middlePoint;
	}

	public BasicGraphArc(Object userObject, GraphNode src, GraphNode dest) {
		this(userObject, src, dest, UNKNOWN_TYPE);
	}

	public BasicGraphArc(Object userObject, GraphNode src, GraphNode dest,
			Object type) {
		this(userObject, src, dest, null, type);
	}

	public BasicGraphArc(Object userObject, GraphNode src, GraphNode dest,
			Icon icon, Object type) {
		super();

		this.userObject = userObject;
		this.src = src;
		this.dest = dest;
		setType(type);

		// this.path = new GeneralPath();
		this.curveFactor = 0;

		this.selected = false;
		this.highlighted = false;

		this.showArrowHead = true;
		this.endArrowHead = new ArrowHead();
		this.startArrowHead = new ArrowHead();

		this.inverted = false;

//		if (icon != null) {
//			this.icon = icon;
//			image = new PImage(((ImageIcon) icon).getImage());
//			addChild(image);
//		}

		this.style = new BasicGraphArcStyle();
		String name = ((OntologyRelationship)this.getUserObject()).getName();

		this.arcLabel = new ArcLabel(this, name);
		addChild(arcLabel);

		// this.updateArcPath();
	}

	public void setShowArrowHead(boolean showArrowHead) {
		this.showArrowHead = showArrowHead;
	}

	public Icon getIcon() {
		return null;
		//return icon;
	}

	public Object getUserObject() {
		return userObject;
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = (type != null ? type : UNKNOWN_TYPE);
	}

	public GraphNode getSource() {
		return src;
	}

	public GraphNode getDestination() {
		return dest;
	}

	public void setCurveFactor(int curveFactor) {
		this.curveFactor = curveFactor;
	}

	public int getCurveFactor() {
		return curveFactor;
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public GraphArcStyle getArcStyle() {
		return style;
	}

	public void setArcStyle(GraphArcStyle style) {
		if ((style != null) && (this.style != style)) {
			this.style = style;
			invalidateFullBounds();
			invalidatePaint();
		}
	}

	public boolean isVisible() {
		return getVisible();
	}

	@Override
	public void setVisible(boolean visible) {

		if (!visible) {
			// hide the arc
			super.setVisible(false);
		} else if (visible && getSource().isVisible()
				&& getDestination().isVisible()) {
			// only show the arc if both the source and destination nodes are
			// visible
			super.setVisible(true);
		}
	}

	@Override
	public String toString() {
		GraphNode src = (isInverted() ? getDestination() : getSource());
		GraphNode dest = (isInverted() ? getSource() : getDestination());
		// return src + " -- " + getType() + " --> " + dest;
		return "\nSRC: " + src + "\n" + getType() + "\n" + "DST: " + dest
				+ "\n";
	}

	public String getTooltip() {
		// if (tooltip == null) {
		// return toString();
		// }
		// return tooltip;
		return null;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		// if (this.selected != selected) {
		// this.selected = selected;
		// arrowHead.setSelected(selected);
		// invalidatePaint();
		// }
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		// if (this.highlighted != highlighted) {
		// this.highlighted = highlighted;
		// arrowHead.setHighlighted(highlighted);
		// invalidatePaint();
		// }
	}

	public void updateArcPath() {
		reset();

		BasicGraphNode srcNode = (BasicGraphNode) src;
		BasicGraphNode destNode = (BasicGraphNode) dest;

		// invert the arc path which will invert the arrowhead
		Rectangle2D srcBounds = (isInverted() ? destNode.getBounds() : srcNode
				.getBounds());
		Rectangle2D destBounds = (isInverted() ? srcNode.getBounds() : destNode
				.getBounds());
		double startX = srcBounds.getCenterX();
		double startY = srcBounds.getCenterY();
		double endX = destBounds.getCenterX();
		double endY = destBounds.getCenterY();

		if (src == dest) {
			final double diam = SELF_ARC_DIAMETER * (curveFactor + 1);
			startX = (float) (srcBounds.getX() + srcBounds.getWidth() - 1);
			endX = startX;
			moveTo((float) startX, (float) startY);
			append(new Ellipse2D.Double(startX, startY - diam / 2.0, diam, diam),
					false);

			endArrowHead.setPoint(new Point2D.Double(startX + diam, startY));
			endArrowHead.setSlope(-Double.MAX_VALUE);
		} else {
			Segment segment = new Segment(startX, startY, endX, endY);
			this.middlePoint = segment.getMidPoint();
			endArrowHead.setSlope(segment.getSlope());
			startArrowHead.setSlope(segment.getSlope());

			// moveTo((float) startX, (float) startY);
			moveTo((float) segment.getStartIntersectionPoint().getX(), (float) segment
					.getStartIntersectionPoint().getY());
			if (curveFactor == 0) {
				// lineTo((float) endX, (float) endY);
				lineTo((float) segment.getEndIntersectionPoint().getX(), (float) segment
						.getEndIntersectionPoint().getY());
				// arrowHead.setPoint(segment.getArrowPoint());
				// arrowHead2.setPoint(segment.getArrowPoint2());
				endArrowHead.setPoint(segment.getEndArrowPoint());
				startArrowHead.setPoint(segment.getStartArrowPoint());

				double w = arcLabel.getBounds().width;
				double h = arcLabel.getBounds().height;

				Point2D middlePoint = getMiddlePoint();
				arcLabel.setBounds(middlePoint.getX() - w / 2.0,
						middlePoint.getY() - h / 2.0, w, h);

				// childrenCountIcon.setBounds(middlePoint.getX() - w/2.0 ,
				// middlePoint.getY() - h/2.0, w, h);

				// childrenCountIcon.setBounds(0,0,0,0);
			} else {
				// the distance that the ctrl point should be offset in the y
				// direction
				double lineLength = segment.getLineLength();
				double yOffset = (curveFactor + 1) * lineLength
						/ CURVE_FACTOR_BASE_OFFSET;
				Point2D.Double arrowHeadPoint = new Point2D.Double(
						lineLength / 3.0, yOffset / 3.0);
				Point2D arrowHeadPointT = segment.getLineTransform().transform(
						arrowHeadPoint, new Point2D.Double());
				endArrowHead.setPoint(arrowHeadPointT);

				Point2D ctrlPoint = new Point2D.Double(lineLength / 2.0,
						yOffset);
				Point2D ctrlPointT = segment.getLineTransform().transform(
						ctrlPoint, new Point2D.Double());
				quadTo((float) ctrlPointT.getX(), (float) ctrlPointT.getY(),
						(float) endX, (float) endY);

				double w = arcLabel.getBounds().width;
				double h = arcLabel.getBounds().height;

				Point2D labelPoint = new Point2D.Double(lineLength / 2.0,
						yOffset / 2.0);
				Point2D labelPointT = segment.getLineTransform().transform(
						labelPoint, new Point2D.Double());
				arcLabel.setBounds(labelPointT.getX() - w / 2.0,
						labelPointT.getY() - h / 2.0, w, h);

				// childrenCountIcon.setBounds(labelPointT.getX() - w/2.0 ,
				// labelPointT.getY() - h/2.0, w, h);

			}
		}
		endArrowHead.setPointRight(endX >= startX);
		startArrowHead.setPointRight(endX <= startX);

		invalidatePaint();
		invalidateFullBounds();
	}

	public boolean equals(Object o) {
		BasicGraphArc arc = (BasicGraphArc) o;
		return this.getUserObject().equals(arc.getUserObject());
	}

	public int hashCode() {
		return this.getUserObject().hashCode();
	}

	public LayoutEntity getSourceInLayout() {
		return src;
	}

	public LayoutEntity getDestinationInLayout() {
		return dest;
	}

	public void setLayoutInformation(Object layoutInformation) {
		this.layoutObject = layoutInformation;
	}

	public Object getLayoutInformation() {
		return layoutObject;
	}

	public Object getGraphData() {
		return graphData;
	}

	public void setGraphData(Object data) {
		this.graphData = data;
	}

	public void clearBendPoints() {
	}

	public void setBendPoints(LayoutBendPoint[] bendPoints) {
	}

	public void populateLayoutConstraint(LayoutConstraint constraint) {
	}

	@Override
	public Stroke getStroke() {
		return style.getStroke(this);
	}

	@Override
	public Paint getStrokePaint() {
		return style.getPaint(this);
	}

	@Override
	protected void paint(PPaintContext paintContext) {
		Graphics2D g2 = paintContext.getGraphics();

		Stroke stroke = getStroke();
		Paint paint = getStrokePaint();
		
//		Stroke stroke = new PFixedWidthStroke(3f);
//		Paint paint = Color.green;
		
		if ((stroke != null) && (paint != null)) {
			g2.setPaint(paint);
			g2.setStroke(stroke);
			g2.draw(getPathReference());

			if (showArrowHead) {
				Shape shape = endArrowHead.getShape();
				g2.fill(shape);
				g2.setStroke(ArrowHead.STROKE);
				g2.draw(shape);
			}

			if (this.getType().toString().contains("associate")) {
				Shape shape = startArrowHead.getShape();
				g2.fill(shape);
				g2.setStroke(ArrowHead.STROKE);
				g2.draw(shape);
			}
		}
	}

	private class Segment {

		private double lineSlope;
		private double lineLength;
		private Point2D srcPtT;
		private Point2D midPtT;
		private Point2D destPtT;
		private AffineTransform lineT;

		private Point2D startArrowPoint;
		private Point2D endArrowPoint;
		private Point2D startIntersectionPoint;
		private Point2D endIntersectionPoint;

		
		public Point2D getStartIntersectionPoint() {
			return startIntersectionPoint;
		}

		public Point2D getEndIntersectionPoint() {
			return endIntersectionPoint;
		}

		public Point2D getStartArrowPoint() {
			return startArrowPoint;
		}

		public Point2D getEndArrowPoint() {
			return endArrowPoint;
		}


		/**
		 * Build a segment from the specified source to the specified
		 * destination
		 * 
		 * @param srcX
		 * @param srcY
		 * @param destX
		 * @param destY
		 */
		public Segment(double srcX, double srcY, double destX, double destY) {

			double lineDx = destX - srcX;
			double lineDy = destY - srcY;
			this.lineSlope = lineDy / lineDx;

			Rectangle2D srcEllipseFrame = ((BasicGraphNode) BasicGraphArc.this
					.getSource()).getEllipse().getFrame();
			Rectangle2D dstEllipseFrame = ((BasicGraphNode) BasicGraphArc.this
					.getDestination()).getEllipse().getFrame();
			
			
			Point2D.Double srcIntersectionPoints[] = getIntersectionPoints(srcEllipseFrame, 4);			
			Point2D.Double dstIntersectionPoints[] = getIntersectionPoints(dstEllipseFrame, 4);
			
			Map<Double, Point2D.Double> intersectionPointsMap = new HashMap<Double, Point.Double>();
			intersectionPointsMap.put(srcIntersectionPoints[0].x, srcIntersectionPoints[0]);
			intersectionPointsMap.put(srcIntersectionPoints[1].x, srcIntersectionPoints[1]);
			intersectionPointsMap.put(dstIntersectionPoints[0].x, dstIntersectionPoints[0]);
			intersectionPointsMap.put(dstIntersectionPoints[1].x, dstIntersectionPoints[1]);
			
			double intersectionPointsX[] = new double[] { srcIntersectionPoints[0].x,
					srcIntersectionPoints[1].x, dstIntersectionPoints[0].x,
					dstIntersectionPoints[1].x,
					
			};
			
			Arrays.sort(intersectionPointsX);

			///
			Point2D.Double srcArrowIntersectionPoints[] = getIntersectionPoints(srcEllipseFrame, 2 * ArrowHead.getArrowSizeConstant());
			Point2D.Double dstArrowIntersectionPoints[] = getIntersectionPoints(dstEllipseFrame, 2 * ArrowHead.getArrowSizeConstant());

			Map<Double, Point2D.Double> arrowIntersectionPointsMap = new HashMap<Double, Point.Double>();
			arrowIntersectionPointsMap.put(srcArrowIntersectionPoints[0].x, srcArrowIntersectionPoints[0]);
			arrowIntersectionPointsMap.put(srcArrowIntersectionPoints[1].x, srcArrowIntersectionPoints[1]);
			arrowIntersectionPointsMap.put(dstArrowIntersectionPoints[0].x, dstArrowIntersectionPoints[0]);
			arrowIntersectionPointsMap.put(dstArrowIntersectionPoints[1].x, dstArrowIntersectionPoints[1]);

			double arrowIntersectionPointsX[] = new double[] { srcArrowIntersectionPoints[0].x,
					srcArrowIntersectionPoints[1].x, dstArrowIntersectionPoints[0].x,
					dstArrowIntersectionPoints[1].x,

			};

			Arrays.sort(arrowIntersectionPointsX);
			
			
			
			if (srcEllipseFrame.getCenterX() < dstEllipseFrame.getCenterX()) {
				this.startArrowPoint = new Point2D.Double(arrowIntersectionPointsX[1],
						arrowIntersectionPointsMap.get(arrowIntersectionPointsX[1]).y);
				this.endArrowPoint = new Point2D.Double(arrowIntersectionPointsX[2],
						arrowIntersectionPointsMap.get(arrowIntersectionPointsX[2]).y);
				
				this.startIntersectionPoint = new Point2D.Double(intersectionPointsX[1],
						intersectionPointsMap.get(intersectionPointsX[1]).y);
				this.endIntersectionPoint = new Point2D.Double(intersectionPointsX[2],
						intersectionPointsMap.get(intersectionPointsX[2]).y);
				
				
			} else {
				this.startArrowPoint = new Point2D.Double(arrowIntersectionPointsX[2],
						arrowIntersectionPointsMap.get(arrowIntersectionPointsX[2]).y);
				this.endArrowPoint = new Point2D.Double(arrowIntersectionPointsX[1],
						arrowIntersectionPointsMap.get(arrowIntersectionPointsX[1]).y);
				
				this.startIntersectionPoint = new Point2D.Double(intersectionPointsX[2],
						intersectionPointsMap.get(intersectionPointsX[2]).y);
				this.endIntersectionPoint = new Point2D.Double(intersectionPointsX[1],
						intersectionPointsMap.get(intersectionPointsX[1]).y);
				
		}
			
			

			double lineTheta = Math.atan(-(1.0 / lineSlope));
			double lineAngle = (lineDy < 0) ? lineTheta + (3.0 / 2.0) * Math.PI
					: lineTheta + (1.0 / 2.0) * Math.PI;
			this.lineLength = Point2D.distance(srcX, srcY, destX, destY);

			lineT = new AffineTransform();
			lineT.concatenate(AffineTransform.getRotateInstance(lineAngle,
					srcX, srcY));
			lineT.concatenate(AffineTransform.getTranslateInstance(srcX, srcY));

			Point2D.Double srcPt = new Point2D.Double(0, 0);
			Point2D.Double destPt = new Point2D.Double(lineLength, 0);
			Point2D.Double midPt = new Point2D.Double(lineLength / 2.0, 0);
			Point2D.Double arrowPt = new Point2D.Double(lineLength * 0.75, 0);
			Point2D.Double arrowPt2 = new Point2D.Double(lineLength * 0.25, 0);

			srcPtT = lineT.transform(srcPt, new Point2D.Double());
			midPtT = lineT.transform(midPt, new Point2D.Double());// real middle
																	// point
			destPtT = lineT.transform(destPt, new Point2D.Double());
		}

		private Point2D.Double[] getIntersectionPoints(Rectangle2D ellipseFrame, double increment) {
			
			
			Point2D.Double intersectionPoint1;
			Point2D.Double intersectionPoint2;

			double width = ellipseFrame.getWidth() + increment;
			double height = ellipseFrame.getHeight() + increment;

			double shiftX = ellipseFrame.getCenterX();
			double shiftY = ellipseFrame.getCenterY();

			double x1 = width
					* height
					/ (2 * Math.sqrt((height * height + lineSlope * lineSlope
							* width * width)));
			double y1 = x1 * this.lineSlope;

			double x2 = -1 * x1 + shiftX;
			double y2 = -1 * y1 + shiftY;

			x1 = x1 + shiftX;
			y1 = y1 + shiftY;

			intersectionPoint1 = new Point2D.Double(x1, y1);
			intersectionPoint2 = new Point2D.Double(x2, y2);

			return new Point2D.Double[] { intersectionPoint1, intersectionPoint2 };

		}

		public double getSlope() {
			return lineSlope;
		}

		public double getLineLength() {
			return lineLength;
		}

		public Point2D getMidPoint() {
			return midPtT;
		}

		public AffineTransform getLineTransform() {
			return lineT;
		}

	}
}
