package au.uq.dke.comon_rcp2.ontology.graph.model;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import au.uq.dke.comon_rcp2.ontology.graph.model.arc.BasicGraphArc;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.IArcUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;
import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;


public class OntologyGraphModelImpl  extends DefaultGraphModel implements IOntologyGraphModel, ITreeContentProvider{

	@Override
	public IGraphArc addArc(IArcUserObject userObject, IGraphNode src, IGraphNode dest) {
		return super.addArc(userObject, src, dest);
	}


	@Override
	public IGraphNode addNode(INodeUserObject userObject) {
		return super.addNode(userObject);
	}

	@Override
	public void removeNode(INodeUserObject userObject) {
		super.removeNode(userObject);
		
	}

	@Override
	public void removeArc(IArcUserObject userObject) {
		super.removeArc(userObject);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public IGraphNode getNode(INodeUserObject userObject) {
		return super.getNode(userObject);
	}

	@Override
	public boolean containsNode(IGraphNode node) {
		return super.containsNode(node);
	}

	@Override
	public IGraphArc getArc(IArcUserObject userObject) {
		return super.getArc(userObject);
	}

	@Override
	public boolean containsArc(IGraphArc arc) {
		return super.containsArc(arc);
	}

	@Override
	public Collection<IGraphNode> getConnectedNodes(INodeUserObject nodeUserObject) {
		return super.getConnectedNodes(nodeUserObject);
	}

	@Override
	public IGraphNode getSourceNode(IArcUserObject arcUserObject) {
		return super.getSourceNode(arcUserObject);
	}

	@Override
	public IGraphNode getDestinationNode(IArcUserObject arcUserObject) {
		return super.getDestinationNode(arcUserObject);
	}

	@Override
	public Collection<IGraphNode> getVisibleNodes() {
		return super.getVisibleNodes();
	}

	@Override
	public Collection<IGraphArc> getVisibleArcs() {
		return super.getVisibleArcs();
	}

	@Override
	public Collection<Object> getArcTypes() {
		return super.getArcTypes();
	}

	@Override
	public void addGraphModelListener(GraphModelListener listener) {
		super.addGraphModelListener(listener);
	}

	@Override
	public void removeGraphModelListener(GraphModelListener listener) {
		super.removeGraphModelListener(listener);
	}

	@Override
	public void arrangeArcs(IGraphNode src, IGraphNode dest) {
		super.arrangeArcs(src, dest);
	}

	@Override
	public Collection<IGraphNode> getAllNodes() {
		return super.getAllNodes();
	}


	@Override
	public Collection<IGraphArc> getAllArcs() {
		return super.getAllArcs();
	}



	@Override
	public boolean containsNode(INodeUserObject userObject) {
		IGraphNode graphNode = new BasicGraphNode(userObject);
		return super.containsNode(graphNode);
	}



	@Override
	public boolean containsArc(IArcUserObject userObject) {
		IGraphArc graphArc = new BasicGraphArc(userObject, null, null);
		return super.containsArc(graphArc);
	}


	// ITreeContentProvider
	
	@Override
	public Collection<IGraphNode> getChildren(INodeUserObject parentNodeUserObject) {
		// TODO search relation table and get all the qualified 
		return null;
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}


}
