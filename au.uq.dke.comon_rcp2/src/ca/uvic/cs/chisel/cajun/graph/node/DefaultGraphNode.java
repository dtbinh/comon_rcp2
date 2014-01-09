package ca.uvic.cs.chisel.cajun.graph.node;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.zest.layouts.constraints.BasicEntityConstraint;
import org.eclipse.zest.layouts.constraints.LabelLayoutConstraint;
import org.eclipse.zest.layouts.constraints.LayoutConstraint;

import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.childrennode.BasicIconNode;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.childrennode.GraphTextNode;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.childrennode.HiddenChildrenCountIcon;
import au.uq.dke.comon_rcp2.ontology.graph.style.BasicGraphNodeStyle;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.PSWTPath;

public class DefaultGraphNode extends PSWTPath implements IGraphNode {

	private static final long serialVersionUID = 3223950711940456476L;

	private BasicIconNode tableIconNode;
	private BasicIconNode dashboardIconNode;

	private INodeUserObject userObject;
	private Object type;
	private String tooltip;

	private Map<Icon, PImage> overlayIconMap = new HashMap<Icon, PImage>();
	private Collection<Icon> overlayIcons = new ArrayList<Icon>();

	private GraphTextNode textNode;
	private PImage pImage;
	private int iconWidth = 0;
	private int iconHeight = 0;

	private IGraphNodeStyle style;

	private boolean selected;
	private boolean highlighted;
	private boolean matching;
	private boolean fixedLocation;

	private double xInLayout = 0;
	private double yInLayout = 0;
	protected double wInLayout = 0;
	protected double hInLayout = 0;
	private Object layoutInformation;

	private double xFactor, yFactor;
	private Object graphData;

	private List<ChangeListener> changeListeners;

	private Collection<IGraphArc> arcs;

	private Ellipse2D ellipse;
	private double BOUNDS_ELLIPSE_FACTOR = 3.0; // the bound is bigger than the
												// text
	// size so we can draw a ellipse in
	private double ELLIPSE_FACTOR = 1.2f; // the distance eclipse is within each
											// side of the bounds

	private HiddenChildrenCountIcon childrenCountIcon;

	private final static int MAX_TOOLTIP_LINES = 20;
	
	private PSWTPath shapePath;

	
	// This nodes uses an internal Ellipse2D to define its shape.
	public Ellipse2D getEllipse() {
		if (ellipse == null)
			ellipse = new Ellipse2D.Double();
		return ellipse;
	}

	public DefaultGraphNode(INodeUserObject userObject) {
		super();


		this.userObject = userObject;

		this.changeListeners = new ArrayList<ChangeListener>();

		this.style = new BasicGraphNodeStyle();
		this.selected = false;
		this.highlighted = false;
		this.matching = false;

		this.arcs = new ArrayList<IGraphArc>();

		this.setPickable(true);
		this.setChildrenPickable(true);

		shapePath = PSWTPath.createEllipse(300, 25, 100, 50);
		shapePath.setPaint(Color.yellow);
		shapePath.setPickable(false);
        this.addChild(shapePath);

        textNode = new GraphTextNode(this);
		// textNode.setHorizontalAlignment(Component.CENTER_ALIGNMENT);
		// make this node match the text size
		// textNode.setConstrainWidthToTextWidth(true);
		// textNode.setConstrainHeightToTextHeight(true);
		textNode.setPickable(false);
		textNode.setText(userObject.getText());

		//childrenCountIcon = new HiddenChildrenCountIcon(this, "1");
		//childrenCountIcon.setHorizontalAlignment(Component.CENTER_ALIGNMENT);
		// make this node match the text size
		//childrenCountIcon.setConstrainWidthToTextWidth(true);
		//childrenCountIcon.setConstrainHeightToTextHeight(true);
		//childrenCountIcon.setPickable(false);

		shapePath.addChild(textNode);
		// addChild(childrenCountIcon);

		setType(type);


		initBounds();

	}

	public void removeChangeListener(ChangeListener l) {
		changeListeners.remove(l);
	}

	public void addChangeListener(ChangeListener l) {
		changeListeners.add(l);
	}

	public Object getUserObject() {
		return userObject;
	}

	public IGraphNodeStyle getNodeStyle() {
		return style;
	}

	public void setNodeStyle(IGraphNodeStyle style) {
		if ((style != null) && (this.style != style)) {
			// this.style = style;
			invalidateFullBounds();
			invalidatePaint();
		}
	}

	@Deprecated
	public Object getType() {
		return type;
	}

	@Deprecated
	public void setType(Object type) {
		this.type = (type == null ? UNKNOWN_TYPE : type);
	}

	public boolean isVisible() {
		return getVisible();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		// hide or show the arcs for this node
		for (IGraphArc arc : arcs) {
			// this method handles whether or not to show the arc
			// checks if the src and dest nodes are visible
			arc.setVisible(visible);
		}
	}

	// TODO: redirect to graph model to get result
	public Collection<IGraphArc> getArcs() {
		return arcs;
	}

	// TODO: redirect to graph model to get result
	public Collection<IGraphArc> getArcs(boolean incoming, boolean outgoing) {
		Collection<IGraphArc> graphArcs;
		if (incoming && outgoing) {
			graphArcs = getArcs();
		} else if (!incoming && !outgoing) {
			graphArcs = Collections.emptyList();
		} else {
			graphArcs = new ArrayList<IGraphArc>();
			for (IGraphArc arc : getArcs()) {
				if (incoming && (arc.getDestination() == this)) {
					graphArcs.add(arc);
				} else if (outgoing && (arc.getSource() == this)) {
					graphArcs.add(arc);
				}
			}
		}
		return graphArcs;
	}

	// TODO: redirect to graph model to get result
	public void addArc(IGraphArc arc) {
		if (!this.arcs.contains(arc)) {
			this.arcs.add(arc);
		}
	}

	public void removeArc(IGraphArc arc) {
		this.arcs.remove(arc);
	}

	public Collection<IGraphNode> getConnectedNodes() {
		ArrayList<IGraphNode> connectedNodes = new ArrayList<IGraphNode>();
		for (IGraphArc arc : getArcs()) {
			IGraphNode src = arc.getSource();
			IGraphNode dest = arc.getDestination();
			IGraphNode nodeToAdd = null;
			if (this == src) {
				nodeToAdd = dest;
			} else if (this == dest) {
				nodeToAdd = src;
			}
			if ((nodeToAdd != null) && !connectedNodes.contains(nodeToAdd)) {
				connectedNodes.add(nodeToAdd);
			}
		}
		return connectedNodes;
	}

	public boolean hasAttribute(Object key) {
		return (getAttribute(key) != null);
	}

	public void removeAttribute(Object key) {
		addAttribute(key, null);
	}

	public String getText() {
		return ("get text of node");
		// return ((INodeUserObject)userObject).getText();

	}

	public void setText(String s) {
		if (s == null) {
			s = "";
		}

		if (s.contains("#")) {
			s = s.substring(s.lastIndexOf('#') + 1);
		}
		// TODO let user choose between eliding the label and splitting into
		// lines?

		// textNode.setText(StringUtil.splitTextIntoLines(s,
		// UIConstants.MAX_LINES, UIConstants.MAX_TEXT_CHARS));
	}

	/**
	 * Restricts the number of characters in the text node. If the string is too
	 * long it is chopped and appended with "...".
	 * 
	 * @param text
	 *            the string to possibly elide
	 * @return the elided string, or the original if text isn't longer than the
	 *         max allowed chars
	 */

	/**
	 * Splits the text into lines. Attempts to split at word breaks if possible.
	 * Also puts a cap on the max number of lines.
	 */

	@Override
	public String toString() {
		return this.getText();

	}

	public String getTooltip() {
		return "";
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		if (this.selected != selected) {
			this.selected = selected;
			updateArcs();
			textNode.invalidatePaint();
			//childrenCountIcon.invalidatePaint();
			invalidatePaint();
		}
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		if (this.highlighted != highlighted) {
			this.highlighted = highlighted;
			bubbleNode();
			textNode.invalidatePaint();
			//childrenCountIcon.invalidatePaint();
			invalidatePaint();
		}
	}

	public boolean isMatching() {
		return matching;
	}

	public void setMatching(boolean matching) {
		if (this.matching != matching) {
			this.matching = matching;
			invalidatePaint();
		}
	}

	/**
	 * Scales the node back to normal size if the canvas is currently scaled
	 * below the regular size.
	 */
	protected void bubbleNode() {
		PCamera camera = ((PLayer) this.getParent()).getCamera(0);
		double viewScale = camera.getViewScale();

		if (highlighted) {
			if (viewScale < 1.0) {
				double scaleFactor = 1.0 / viewScale;

				double unscaledWidth = this.getGlobalBounds().width;
				double unscaledHeight = this.getGlobalBounds().height;
				double scaledWidth = this.getGlobalBounds().width * viewScale;
				double scaledHeight = this.getGlobalBounds().height * viewScale;

				this.scaleAboutPoint(scaleFactor, getX(), getY());

				xFactor = (unscaledWidth - scaledWidth) / 2;
				yFactor = (unscaledHeight - scaledHeight) / 2;
				this.translate(-1 * xFactor, -1 * yFactor);
			}
		} else {
			if (xFactor > 0) {
				this.translate(xFactor, yFactor);
				this.scaleAboutPoint(1.0 / getScale(), getX(), getY());
			}

			xFactor = 0;
			yFactor = 0;
		}
	}

	private void updateArcs() {
		for (IGraphArc arc : arcs) {
			if (isSelected()) {
				arc.setSelected(true);
			} else {
				IGraphNode src = arc.getSource();
				IGraphNode dest = arc.getDestination();
				if (src == dest) {
					arc.setSelected(false);
				} else if (this == src) {
					arc.setSelected(dest.isSelected());
				} else if (this == dest) {
					arc.setSelected(src.isSelected());
				}
			}
		}
	}

	private void fireChangeListeners() {
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(event);
		}
	}

	private void updateArcLocations() {
		for (IGraphArc arc : arcs) {
			arc.updateArcPath();
		}
	}

	/**
	 * Sets the bounds of this node based on the text size. Takes into
	 * consideration the maximum node width too.
	 */

	private void initBounds() {

		PBounds textBounds = textNode.getBounds();
		double tw = textBounds.getWidth();
		double th = textBounds.getHeight();

		if (tw > th) {
			getEllipse().setFrameFromCenter(textBounds.getCenterX(),
					textBounds.getCenterY(), textBounds.getX() - 5,
					textBounds.getY() - (tw - th) / 2 - 5);

		} else {
			getEllipse().setFrameFromCenter(textBounds.getCenterX(),
					textBounds.getCenterY(),
					textBounds.getX() - (th - tw) / 2 - 5,
					textBounds.getY() - 5);

		}

		double ew = getEllipse().getWidth();
		double eh = getEllipse().getHeight();

		double bw = ew * BOUNDS_ELLIPSE_FACTOR;
		double bh = eh * BOUNDS_ELLIPSE_FACTOR;

		this.setSize(bw, bh);

//		textNode.setBounds(10, 10, 30, 30);
//		int a = 1;

	}

	// This method is important to override so that the geometry of
	// the ellipse stays consistent with the bounds geometry.
	public boolean setBounds(double x, double y, double width, double height) {
		if (super.setBounds(x, y, width, height)) {

			double centerX = this.getCenterX();
			double centerY = this.getCenterY();

			// PBounds textBounds = textNode.getBounds();
			// double tw = textBounds.getWidth();
			// double th = textBounds.getHeight();

//			textNode.setBounds(centerX - textNode.getWidth() / 2, centerY
//					- textNode.getHeight() / 2, textNode.getWidth(),
//					textNode.getHeight());
			textNode.setTranslation(centerX - textNode.getWidth() / 2, centerY
					- textNode.getHeight() / 2);
			
//			textNode.setBounds(120, 120, 40, 40);

			if (this.tableIconNode != null) {
				this.tableIconNode
						.setBounds(centerX - 50, centerY - 50, 30, 30);
			}

			if (this.dashboardIconNode != null) {
				this.dashboardIconNode.setBounds(centerX - 50, centerY - 50,
						30, 30);
			}
			getEllipse().setFrame(centerX - getEllipse().getWidth() / 2,
					centerY - getEllipse().getHeight() / 2,
					getEllipse().getWidth(), getEllipse().getHeight());
			
			this.shapePath.setBounds(getEllipse().getBounds2D());

//			double cw = childrenCountIcon.getWidth();
//			double ch = childrenCountIcon.getHeight();
//			double d = Math.max(cw, ch);
//			// childrenCountIcon.setBounds(getX(), getY() + getHeight()/2, d,
//			// d);
//			childrenCountIcon.setBounds(getEllipse().getX() - d / 2,
//					getEllipse().getY() + getEllipse().getHeight() / 2 - d / 2,
//					d, d);

			updateArcLocations();
			invalidatePaint();

			fireChangeListeners();
			return true;
		}
		return false;
	}

	// Non rectangular subclasses need to override this method so
	// that they will be picked correctly and will receive the
	// correct mouse events.
	public boolean intersects(Rectangle2D aBounds) {
		return getEllipse().intersects(aBounds);
	}

	public boolean setLocation(double x, double y) {
		setHighlighted(false);

		return setBounds(x, y, getWidth(), getHeight());
	}

	public boolean setSize(double w, double h) {
		return setBounds(getX(), getY(), w, h);
	}

	public double getCenterX() {
		return (getX() + (getWidth() / 2));
	}

	public double getCenterY() {
		return (getY() + (getHeight() / 2));
	}

	public Object getGraphData() {
		return graphData;
	}

	public void setGraphData(Object data) {
		this.graphData = data;
	}

	public double getXInLayout() {
		return xInLayout;
	}

	public double getYInLayout() {
		return yInLayout;
	}

	public double getWidthInLayout() {
		// return wInLayout;
		return getBounds().width;
	}

	public double getHeightInLayout() {
		// return hInLayout;
		return getBounds().height;
	}

	public void setLocationInLayout(double x, double y) {
		xInLayout = x;
		yInLayout = y;
	}

	public Object getLayoutInformation() {
		return layoutInformation;
	}

	public void setLayoutInformation(Object layoutInformation) {
		this.layoutInformation = layoutInformation;
	}

	public void setSizeInLayout(double width, double height) {
		wInLayout = width;
		hInLayout = height;
	}

	public boolean hasPreferredLocation() {
		return false;
	}

	/**
	 * Populate the specified layout constraint
	 */
	public void populateLayoutConstraint(LayoutConstraint constraint) {
		if (constraint instanceof LabelLayoutConstraint) {
			LabelLayoutConstraint labelConstraint = (LabelLayoutConstraint) constraint;
			labelConstraint.label = getText();
			labelConstraint.pointSize = 18;
		} else if (constraint instanceof BasicEntityConstraint) {
			BasicEntityConstraint basicEntityConstraint = (BasicEntityConstraint) constraint;
			if (this.hasPreferredLocation()) {
				basicEntityConstraint.hasPreferredLocation = true;
				basicEntityConstraint.preferredX = this.getX();
				basicEntityConstraint.preferredY = this.getY();
			}
		}
	}

	public int compareTo(Object o) {
		if (o instanceof DefaultGraphNode) {
			DefaultGraphNode node = (DefaultGraphNode) o;
			return this.getText().compareToIgnoreCase(node.getText());
		}
		return 0;
	}

	@Override
	protected void paint(PPaintContext paintContext) {
		Graphics2D g2 = paintContext.getGraphics();

		// this.setB
		// shrink the bounds slightly to avoid painting outside the bounds

		// can't be null

		// these can be null

		Paint bg = style.getBackgroundPaint(this);
		Paint borderPaint = style.getBorderPaint(this);
		Stroke borderStroke = style.getBorderStroke(this);

		// Paint bg = Color.red;
		// Paint borderPaint = Color.red;
		// Stroke borderStroke = new PFixedWidthStroke(3f);

		Shape ellipseShape = getEllipse();
		Shape fullShape = this.getBoundsReference();
		

		// 0. paint the ellipse
		g2.setPaint(Color.blue);
		g2.setStroke(borderStroke);
		g2.draw(ellipseShape);

		// // 1. paint the background shape
		// if (bg != null) {
		// g2.setPaint(Color.green);
		// g2.fill(fullShape);
		// }

		// 2. paint the border
		if ((borderPaint != null) && (borderStroke != null)) {
			// g2.setPaint(borderPaint);
			g2.setPaint(Color.green);
			g2.setStroke(borderStroke);
			g2.draw(fullShape);
		}

		// childrenCountIcon.setBounds(
		// getEllipse().getX() - childrenCountIcon.getWidth() / 2,
		// getEllipse().getY() + getEllipse().getHeight() / 2
		// - childrenCountIcon.getHeight() / 2,
		// childrenCountIcon.getWidth(), childrenCountIcon.getHeight());
		//
		// //super.paint(paintContext);
		// // addOverlayIcons(style.getOverlayIcons(this));
	}

	/**
	 * @depriate
	 * @param paintContext
	 */

	/**
	 * If necessary, creates the overlay icons as PImage's and adds them to this
	 * node as a child object. If it is already created, the overlayIcon is
	 * repositioned.
	 * 
	 * @param icon
	 *            The icon to set as the overlayIcon.
	 */
	private void addOverlayIcons(Collection<Icon> icons) {
		if (icons == null || !icons.equals(overlayIcons)) {
			for (PImage overlayIcon : overlayIconMap.values()) {
				removeChild(overlayIcon);
			}
			overlayIconMap.clear();
		}
		if (icons != null) {
			for (Icon icon : icons) {
				PImage overlayIcon = overlayIconMap.get(icon);
				if (overlayIcon == null && icon != null) {
					overlayIcon = new PImage(((ImageIcon) icon).getImage());
					overlayIcon.setPickable(false);
					addChild(overlayIcon);
					overlayIconMap.put(icon, overlayIcon);
				}

				if (overlayIcon != null) {
					Point2D iconPosition = style.getOverlayIconPosition(this,
							icon);
					overlayIcon.setX(iconPosition.getX());
					overlayIcon.setY(iconPosition.getY());
				}
			}
		}

		overlayIcons = icons;
	}

	private GradientPaint updateGradientPaintPoints(GradientPaint gp) {
		int x = (int) getX();
		int y = (int) getY();
		int h = (int) getHeight();
		GradientPaint gradient = new GradientPaint(x, y, gp.getColor1(), x, y
				+ h, gp.getColor2(), gp.isCyclic());
		return gradient;
	}

	public boolean isFixedLocation() {
		return fixedLocation;
	}

	public void setFixedLocation(boolean fixedLocation) {
		this.fixedLocation = fixedLocation;
	}
}
