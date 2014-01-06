package ca.uvic.cs.chisel.cajun.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;
import ca.uvic.cs.chisel.cajun.graph.arc.DefaultGraphArc;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class DefaultGraphModel implements IGraphModel {

	private Collection<GraphModelListener> listeners;

	private Map<Object, IGraphNode> nodes;
	private Map<Object, IGraphArc> arcs;

	private Set<Object> nodeTypes;
	private Set<Object> arcTypes;
	
	public DefaultGraphModel() {
		this.listeners = new ArrayList<GraphModelListener>();
		this.nodes = new HashMap<Object, IGraphNode>();
		this.arcs = new HashMap<Object, IGraphArc>();
		this.nodeTypes = new HashSet<Object>();
		this.arcTypes = new HashSet<Object>();
	}

	public void clear() {
		if ((nodes.size() > 0) || (arcs.size() > 0)) {
			nodes.clear();
			arcs.clear();
			nodeTypes.clear();
			arcTypes.clear();
			fireGraphClearedEvent();
		}
	}

	public void addGraphModelListener(GraphModelListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeGraphModelListener(GraphModelListener listener) {
		listeners.remove(listener);
	}

	protected void fireGraphClearedEvent() {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphCleared();
			}
		}
	}
	
	protected void fireNodeTypeAddedEvent(Object nodeType) {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphNodeTypeAdded(nodeType);
			}
		}
	}
	
	protected void fireNodeAddedEvent(IGraphNode node) {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphNodeAdded(node);
			}
		}
	}

	protected void fireNodeRemovedEvent(IGraphNode node) {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphNodeRemoved(node);
			}
		}
	}

	protected void fireArcTypeAddedEvent(Object arcType) {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphArcTypeAdded(arcType);
			}
		}
	}
	
	protected void fireArcAddedEvent(IGraphArc arc) {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphArcAdded(arc);
			}
		}
	}

	protected void fireArcRemovedEvent(IGraphArc arc) {
		if (listeners.size() > 0) {
			ArrayList<GraphModelListener> clonedListeners = new ArrayList<GraphModelListener>(listeners);
			for (GraphModelListener gml : clonedListeners) {
				gml.graphArcRemoved(arc);
			}
		}
	}

	public Collection<IGraphNode> getAllNodes() {
		return nodes.values();
	}
	
	public Collection<IGraphNode> getVisibleNodes() {
		ArrayList<IGraphNode> visibleNodes = new ArrayList<IGraphNode>();
		for (IGraphNode node : nodes.values()) {
			if (node.isVisible()) {
				visibleNodes.add(node);
			}
		}
		return visibleNodes;
	}

	public IGraphNode getNode(Object userObject) {
		if (userObject != null) {
			return nodes.get(userObject);
		}
		return null;
	}

	public boolean containsNode(IGraphNode node) {
		if (node != null) {
			return nodes.containsKey(node.getUserObject());
		}
		return false;
	}

	public Collection<IGraphNode> getConnectedNodes(Object nodeUserObject) {
		if (nodes.containsKey(nodeUserObject)) {
			IGraphNode node = nodes.get(nodeUserObject);
			return node.getConnectedNodes();
		}
		// not sure if this is necessary or a good idea?
		if (nodeUserObject instanceof IGraphNode) {
			IGraphNode node = (IGraphNode) nodeUserObject;
			return node.getConnectedNodes();
		}

		// return null or empty list?
		return Collections.emptyList();
	}

	public Collection<IGraphArc> getArcs(Object nodeUserObject) {
		if (nodes.containsKey(nodeUserObject)) {
			IGraphNode node = nodes.get(nodeUserObject);
			return node.getArcs();
		}

		// not sure if this is necessary or a good idea
		if (nodeUserObject instanceof IGraphNode) {
			IGraphNode node = (IGraphNode) nodeUserObject;
			return node.getArcs();
		}

		// return null or empty list?
		return Collections.emptyList();
	}
	
	public Collection<Object> getNodeTypes() {
		return new HashSet<Object>(nodeTypes);
	}
	
	// ARCS

	public Collection<IGraphArc> getAllArcs() {
		return arcs.values();
	}

	public Collection<IGraphArc> getVisibleArcs() {
		ArrayList<IGraphArc> visibleArcs = new ArrayList<IGraphArc>();
		for (IGraphArc arc : arcs.values()) {
			if (arc.isVisible()) {
				visibleArcs.add(arc);
			}
		}
		return visibleArcs;
	}
	
	public IGraphArc getArc(Object userObject) {
		if (userObject != null) {
			return arcs.get(userObject);
		}
		return null;
	}

	public boolean containsArc(IGraphArc arc) {
		if (arc != null) {
			return arcs.containsKey(arc.getUserObject());
		}
		return false;
	}

	public Object getArcType(Object arcUserObject) {
		return GraphItem.UNKNOWN_TYPE;
	}

	public IGraphNode getSourceNode(Object arcUserObject) {
		if (arcs.containsKey(arcUserObject)) {
			IGraphArc arc = arcs.get(arcUserObject);
			return arc.getSource();
		}

		// not sure if this is necessary or a good idea
		if (arcUserObject instanceof IGraphArc) {
			IGraphArc arc = (IGraphArc) arcUserObject;
			return arc.getSource();
		}

		return null;
	}

	public IGraphNode getDestinationNode(Object arcUserObject) {
		if (arcs.containsKey(arcUserObject)) {
			IGraphArc arc = arcs.get(arcUserObject);
			return arc.getDestination();
		}

		// not sure if this is necessary or a good idea
		if (arcUserObject instanceof IGraphArc) {
			IGraphArc arc = (IGraphArc) arcUserObject;
			return arc.getDestination();
		}

		return null;
	}
	
	public Collection<Object> getArcTypes() {
		return new HashSet<Object>(arcTypes);
	}

	// Add/Remove methods

	protected void addNodeInternal(IGraphNode node) {
		if ((node != null) && !nodes.containsKey(node.getUserObject())) {
			// this should be the ONLY place where nodes are added to the map
			nodes.put(node.getUserObject(), node);
			if (!nodeTypes.contains(node.getType())) {
				nodeTypes.add(node.getType());
				fireNodeTypeAddedEvent(node.getType());
			}
			fireNodeAddedEvent(node);
		}
	}


	public IGraphNode addNode(Object userObject) {
		if (userObject == null) {
			throw new NullPointerException("All graph nodes must have a user object.");
		}
		if (!nodes.containsKey(userObject)) {
			BasicGraphNode node = new BasicGraphNode((INodeUserObject) userObject);
			addNodeInternal(node);
		}
		return nodes.get(userObject);
	}

	protected void removeNodeInternal(IGraphNode node) {
		if (nodes.containsKey(node.getUserObject())) {
			// remove the arcs for this node first
			IGraphArc[] arcs = node.getArcs().toArray(new IGraphArc[node.getArcs().size()]);

			for (IGraphArc arc : arcs) {
				removeArc(arc.getUserObject());
			}

			// now remove the node from this model
			nodes.remove(node.getUserObject());
			fireNodeRemovedEvent(node);
		}
	}

	public void removeNode(Object userObject) {
		if ((userObject != null) && nodes.containsKey(userObject)) {
			removeNodeInternal(nodes.get(userObject));
		}
	}

	/**
	 * Clears the node types, then iterates through all the nodes and adds the
	 * node types back in.
	 */
	public void recalculateNodeTypes() {
		nodeTypes.clear();
		for (IGraphNode node : nodes.values()) {
			Object nodeType = node.getType();
			if (!nodeTypes.contains(nodeType)) {
				nodeTypes.add(nodeType);
				fireNodeTypeAddedEvent(nodeType);
			}
		}
	}
	
	/**
	 * Clears the arc types, then iterates through all the arcs and adds the
	 * arc types back in.
	 */
	public void recalculateArcTypes() {
		arcTypes.clear();
		for (IGraphArc arc: arcs.values()) {
			Object arcType = arc.getType();
			if (!arcTypes.contains(arcType)) {
				arcTypes.add(arcType);
				fireArcTypeAddedEvent(arcType);
			}
		}
	}
	
	public void recalculateArcStyles() {
		for (IGraphArc arc: arcs.values()) {
			arc.getArcStyle().setTypes(arcTypes);
		}
	}
	
	protected void addArcInternal(DefaultGraphArc arc) {
		// this is the ONLY place where arcs are added
		arcs.put(arc.getUserObject(), arc);
		if (!arcTypes.contains(arc.getType())) {
			arcTypes.add(arc.getType());
			fireArcTypeAddedEvent(arc.getType());
		}

		// add this arc to the source and destination nodes
		arc.getSource().addArc(arc);
		arc.getDestination().addArc(arc);

		fireArcAddedEvent(arc);
	}
	
	public IGraphArc addArc(Object userObject, IGraphNode src, IGraphNode dest) {
		if (userObject == null) {
			throw new NullPointerException("All graph arcs must have a user object.");
		}
		if (!arcs.containsKey(userObject)) {
			addNodeInternal(src);
			addNodeInternal(dest);
			DefaultGraphArc arc = new DefaultGraphArc(userObject, src, dest);
			addArcInternal(arc);
			arrangeArcs(arc.getSource(), arc.getDestination());
		}
		return arcs.get(userObject);
	}

	protected void removeArcInternal(IGraphArc arc) {
		if ((arc != null) && arcs.containsKey(arc.getUserObject())) {
			// remove this arc from the source and destination nodes
			arc.getSource().removeArc(arc);
			arc.getDestination().removeArc(arc);
			// now remove the arc from the model and fire the event
			arcs.remove(arc.getUserObject());
			fireArcRemovedEvent(arc);
		}
	}

	public void removeArc(Object userObject) {
		if ((userObject != null) && arcs.containsKey(userObject)) {
			removeArcInternal(arcs.get(userObject));
		}
	}

	/**
	 * Arranges all arcs going between the source and destination nodes so that they do not overlap.
	 * Sets the curve factor on each arc.
	 * 
	 * @see IGraphArc#setCurveFactor(int)
	 * @param src the source node
	 * @param dest the destination node
	 */
	public void arrangeArcs(IGraphNode src, IGraphNode dest) {
		ArrayList<IGraphArc> srcToDestArcs = new ArrayList<IGraphArc>();
		ArrayList<IGraphArc> destToSrcArcs = new ArrayList<IGraphArc>();
		for (IGraphArc arc : src.getArcs()) {
			if ((src == arc.getSource()) && (dest == arc.getDestination())) {
				srcToDestArcs.add(arc);
			} else if ((src == arc.getDestination()) && ((dest == arc.getSource()))) {
				destToSrcArcs.add(arc);
			}
		}

		// the initial curve factor
		// if arcs are only in one direction then we use a straight line for the first arc
		int startingCurve = 1;
		if ((srcToDestArcs.size() == 0) || (destToSrcArcs.size() == 0)) {
			startingCurve = 0;
		}

		int curveFactor = startingCurve;
		for (IGraphArc arc : srcToDestArcs) {
			arc.setCurveFactor(curveFactor);
			curveFactor++;
		}

		curveFactor = startingCurve;
		for (IGraphArc arc : destToSrcArcs) {
			arc.setCurveFactor(curveFactor);
			curveFactor++;
		}
	}

}
