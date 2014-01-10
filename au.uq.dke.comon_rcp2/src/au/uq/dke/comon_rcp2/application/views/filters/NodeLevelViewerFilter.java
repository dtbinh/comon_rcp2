package au.uq.dke.comon_rcp2.application.views.filters;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModel;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;

public class NodeLevelViewerFilter extends ViewerFilter {
	private Map<Integer, Boolean> levelVisibilityMap = new HashMap<Integer, Boolean>();
	
	private OntologyGraphModel graphModel = OntologyGraphModel.getInstance();
	
	public NodeLevelViewerFilter(){
		for(int i = 0; i < graphModel.getRootTreeNode().getMaxDepth(); i ++){
			if( i < FilterConstants.DEFAULT_NODE_VISIBLE_LEVEL){
				levelVisibilityMap.put(i, true);
			}else{
				levelVisibilityMap.put(i, false);
			}
		}
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof BasicGraphNode){
			BasicGraphNode graphNode = (BasicGraphNode) element;
			int level = ((MutableTree)graphNode.getTreeNode()).getPathToRoot().size() - 1;
			boolean visiblity = levelVisibilityMap.get(level);
			
			return visiblity;
		}else{
			// not a graphNode
			// if is a graphArc, we return true doesn't harm: we decide nothing since 1 U anything  = anything 
			return true;
		}

	}
}
