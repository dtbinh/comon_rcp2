package ca.uvic.cs.chisel.cajun.graph.node;

import java.awt.geom.Rectangle2D;
import java.util.Collection;

import org.eclipse.zest.layouts.LayoutEntity;

import au.uq.dke.comon_rcp2.application.views.navigator.model.INavigatorNodeBean;
import ca.uvic.cs.chisel.cajun.graph.GraphItem;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;

public interface IGraphNode extends GraphItem, LayoutEntity {

	/**
	 * Returns all the connect nodes. It does this by looking at all the arcs and collecting the
	 * nodes for each.
	 */
	public Collection<IGraphNode> getConnectedNodes();

	/**
	 * Returns all the arcs that have this node as a source or destination.
	 */
	public Collection<IGraphArc> getArcs();

	/**
	 * Returns the incoming and/or outgoing arcs attached to this node. If both incoming and
	 * outgoing is true then all arcs are returned. If both incoming and outgoing are false then an
	 * empty list is returned.
	 * 
	 * @param incoming if true then incoming arcs will be returned
	 * @param outgoing if true then outgoing arcs will be returned
	 * @return the matching arcs
	 */
	public Collection<IGraphArc> getArcs(boolean incoming, boolean outgoing);

	public IGraphNodeStyle getNodeStyle();

	public void setNodeStyle(IGraphNodeStyle style);

	public String getText();
	public void setText(String text);

	public Rectangle2D getBounds();
	public boolean setBounds(double x, double y, double w, double h);
	public boolean setLocation(double x, double y);
	public boolean setSize(double w, double h);

	// these methods are used by the GraphModel
	void addArc(IGraphArc arc);
	void removeArc(IGraphArc arc);

	// used with searching
	boolean isMatching();
	void setMatching(boolean matching);

	public boolean isVisible();
	public void setVisible(boolean visible);

	public boolean isFixedLocation();
	public void setFixedLocation(boolean fixedLocation);
}
