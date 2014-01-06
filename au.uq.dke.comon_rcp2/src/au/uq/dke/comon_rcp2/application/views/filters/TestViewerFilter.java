package au.uq.dke.comon_rcp2.application.views.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;

public class TestViewerFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof BasicGraphNode){
			BasicGraphNode graphNode = (BasicGraphNode) element;
			
			if(graphNode.getTreeNode().isLeaf()){
				return false;
			}
			return true;
		}else{
			// not a graphNode
			// if is a graphArc, we return true doesn't harm: we decide nothing since 1 U anything  = anything 
			return true;
		}

	}
}
