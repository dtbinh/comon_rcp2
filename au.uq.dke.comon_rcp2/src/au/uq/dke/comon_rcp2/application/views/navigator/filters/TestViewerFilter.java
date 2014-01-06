package au.uq.dke.comon_rcp2.application.views.navigator.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;

public class TestViewerFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		BasicGraphNode graphNode = (BasicGraphNode) element;

		if(graphNode.getTreeNode().isLeaf()){
			return false;
		}
		return true;
	}
}
