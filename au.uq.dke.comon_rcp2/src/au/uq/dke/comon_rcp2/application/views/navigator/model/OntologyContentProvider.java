package au.uq.dke.comon_rcp2.application.views.navigator.model;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModel;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;



public class OntologyContentProvider implements ITreeContentProvider {
	private NavigatorRoot navigatorRoot = new NavigatorRoot();
	private OntologyGraphModel ontologyGraphModel = OntologyGraphModel.getInstance();

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
		
		return navigatorRoot.getOntologyRoots().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof BasicGraphNode){
			BasicGraphNode parentBasicGraphNode = (BasicGraphNode) parentElement;
			Collection<MutableTree> childrenTreeNode = parentBasicGraphNode.getTreeNode().getChildren();
			Collection<BasicGraphNode> childrenBasicGraphNode = new ArrayList();
			
			for(MutableTree childTreeNode : childrenTreeNode){
				
				BasicGraphNode childBasicGraphNode = (BasicGraphNode) ontologyGraphModel.getNode(childTreeNode.getUserObject());
				childrenBasicGraphNode.add(childBasicGraphNode);
			}
			
			return childrenBasicGraphNode.toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object parentElement) {
		if(parentElement instanceof BasicGraphNode){
			BasicGraphNode parentBasicGraphNode = (BasicGraphNode) parentElement;
			return !parentBasicGraphNode.getTreeNode().isLeaf();
		}
		
		return false;
	}


}
