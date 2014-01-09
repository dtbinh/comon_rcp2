package ca.uvic.cs.chisel.cajun.graph;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import org.eclipse.swt.widgets.Composite;

import ca.uvic.cs.chisel.cajun.actions.LayoutAction;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArcStyle;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNodeCollectionListener;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNodeStyle;
import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.event.PInputEventListener;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

public interface IGraphSwing {

	// Property change names
	public static final String GRAPH_MODEL_PROPERTY = "model";
	public static final String GRAPH_ARC_STYLE_PROPERTY = "arc style";
	public static final String GRAPH_NODE_STYLE_PROPERTY = "node style";
	
	public void addPropertyChangeListener(PropertyChangeListener pcl);
	public void removePropertyChangeListener(PropertyChangeListener pcl);
	
	/**
	 * Adds a graph model listener.  Use this method if you want
	 * this listener to be kept attached to current graph model,
	 * even when the graph has a new model set.
	 * @param gml the listener
	 * @see IGraphModel#addGraphModelListener(GraphModelListener)
	 */
	public void addGraphModelListener(GraphModelListener gml);
	
	/**
	 * Removes a graph model listener.
	 * @param gml the listener
	 * @see IGraphModel#removeGraphModelListener(GraphModelListener)
	 */
	public void removeGraphModelListener(GraphModelListener gml);
	
	/**
	 * Returns the swing graph component.
	 */
	public Composite getGraphComponent();
	
	/**
	 * Returns the graph canvas.
	 */
	public PSWTCanvas getCanvas();
	
	/** Returns the node right-click context menu. */
//	public PopupMenu getNodeContextMenu();
	/** Returns the arc right-click context menu. */
//	public PopupMenu getArcContextMenu();
	/** Returns the canvas right-click context menu. */
//	public JPopupMenu getCanvasContextMenu();
	
	/**
	 * @return the current {@link IGraphModel}, won't be null
	 */
	public IGraphModel getModel();
	
	/**
	 * Sets the {@link IGraphModel} and fires a PropertyChangeEvent using the
	 * {@link IGraphSwing#GRAPH_MODEL_PROPERTY} property name.
	 * @param model
	 */
	public void setModel(IGraphModel model);
	
	public void addNodeSelectionListener(GraphNodeCollectionListener listener);
	public void removeNodeSelectionListener(GraphNodeCollectionListener listener);

	/**
	 * @return the collection of selected nodes
	 */
	public Collection<IGraphNode> getSelectedNodes();
	
	/**
	 * Sets the selected nodes in the graph.  These nodes will most likely
	 * be rendered differently because they are selected.
	 * @param nodes the selected nodes
	 */
	public void setSelectedNodes(Collection<IGraphNode> nodes);
	
	/**
	 * @return the collection of matching nodes (from the last search result)
	 */
	public Collection<IGraphNode> getMatchingNodes();
	
	/**
	 * Sets the collection of matching nodes from a search result.
	 * These nodes will probably be rendered differently as a result.
	 * @param nodes the matching nodes.
	 */
	public void setMatchingNodes(Collection<IGraphNode> nodes);
	
	public void addGraphInputListener(PInputEventListener listener);
	public void removeGraphInputListener(PInputEventListener listener);
	
	public void clear();
	public void repaint();
	//public Rectangle2D getBounds();
	
	/**
	 * Sets the node style for all nodes in the graph.
	 * @param style the style to use
	 */
	public void setGraphNodeStyle(IGraphNodeStyle style);
	
	/**
	 * Sets the arc style for all arcs in the graph.
	 * @param style the style to use
	 */
	public void setGraphArcStyle(IGraphArcStyle style);
	
	/**
	 * Adds a layout to the graph.
	 * @param layout the layout to add
	 */
	public void addLayout(LayoutAction layout);
	
	/**
	 * Removes a layout from the graph.
	 * @param layout the layout to remove
	 */
	public void removeLayout(LayoutAction layout);
	
	/**
	 * Returns all the layouts currently supported on this graph.
	 * @return a collection of layouts
	 */
	public Collection<LayoutAction> getLayouts();
	
	/**
	 * Runs the layout using the last run layout.
	 * If no layouts have been performed then the default layout is used.
	 */
	public void performLayout();
	
	/**
	 * Runs the given layout on all the visible nodes in the graph.
	 * @param layout
	 */
	public void performLayout(LayoutAction layout);
	
	public void setShowNodeTooltips(boolean showNodeTooltips);
	
	public boolean isShowNodeTooltips();
	
}
