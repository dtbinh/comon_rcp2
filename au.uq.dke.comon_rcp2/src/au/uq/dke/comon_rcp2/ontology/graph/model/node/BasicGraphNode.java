package au.uq.dke.comon_rcp2.ontology.graph.model.node;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import uk.ac.manchester.cs.bhig.util.Tree;
import au.uq.dke.comon_rcp2.application.views.navigator.model.INavigatorNodeBean;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import ca.uvic.cs.chisel.cajun.graph.node.DefaultGraphNode;

public class BasicGraphNode extends DefaultGraphNode implements INavigatorNodeBean{
	private INodeUserObject userObject = null;
	
	public INodeUserObject getUserObject() {
		return userObject;
	}

	private Tree treeNode = null;

	
	public Tree getTreeNode() {
		return treeNode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicGraphNode(INodeUserObject userObject) {
		super(userObject);
		this.userObject = userObject;
		this.treeNode = new MutableTree(userObject);
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
		return userObject.isHasChildren();
	}

}
