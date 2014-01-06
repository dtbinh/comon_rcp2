package au.uq.dke.comon_rcp2.application.views.graph;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;
import ca.uvic.cs.chisel.cajun.actions.LayoutAction;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class Graph extends FlatGraph {

	@Override
	public void performLayout() {
		this.applyFilters();
		super.performLayout();
	}

	@Override
	public void performLayout(LayoutAction layout) {
		this.applyFilters();
		super.performLayout(layout);
	}

	OntologyGraphModelImpl graphModel = OntologyGraphModelImpl.getInstance();

	private Collection<ViewerFilter> viewerFilters = new ArrayList<ViewerFilter>();

	public Graph() {
		super();
		super.setModel(graphModel);
	}

	public void addViewerFilter(ViewerFilter viewerFilter) {

		viewerFilters.add(viewerFilter);
	}

	public void removeViewerFilter(ViewerFilter viewerFilter) {
		viewerFilters.remove(viewerFilter);
		this.performLayout();

	}

	public void applyFilters() {
		for (IGraphNode graphNode : graphModel.getAllNodes()) {
			boolean isVisible = true;

			for (ViewerFilter viewerFilter : viewerFilters) {
				isVisible = viewerFilter.select(null, null, graphNode);
				if (isVisible == false) {// we should hide it if any filter says
											// to hide it
					break;
				}
			}

			boolean oldVisibility = graphNode.isVisible();
			if (oldVisibility != isVisible) {
				graphNode.setVisible(isVisible);
			}
		}

		for (IGraphArc graphArc : graphModel.getAllArcs()) {
			boolean isVisible = true;

			for (ViewerFilter viewerFilter : viewerFilters) {
				isVisible = viewerFilter.select(null, null, graphArc);
				if (isVisible == false) {// we should hide it if any filter says
											// to hide it
					break;
				}
			}

			boolean oldVisibility = graphArc.isVisible();
			if (oldVisibility != isVisible) {
				graphArc.setVisible(isVisible);
			}
		}
	}
}
