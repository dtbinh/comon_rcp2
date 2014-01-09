package au.uq.dke.comon_rcp2.application.views.graph;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;
import ca.uvic.cs.chisel.cajun.actions.LayoutManager;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class OntologyGraph extends FlatGraph {

	private static OntologyGraph instance;

	public static OntologyGraph getInstance() {
		if (instance == null) {
			//TODO Exception
		}
		return instance;
	}
	
	public static void createInstance(Composite parent){
		if (instance == null) {
			instance = new OntologyGraph(parent);
		}
	}

	public void performLayoutWithFilter() {
		this.applyFilters();
		super.performLayout();
	}

	public void performLayoutWithoutFilter() {
		super.performLayout();
	}

	@Deprecated
	@Override
	public void performLayout(LayoutManager layout) {
	}

	@Deprecated
	@Override
	public void performLayout() {
	}

	OntologyGraphModelImpl graphModel = OntologyGraphModelImpl.getInstance();

	private Collection<ViewerFilter> viewerFilters = new ArrayList<ViewerFilter>();

	private OntologyGraph(Composite parent) {
		super(parent);
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
			boolean shouldFilterOut = false;

			for (ViewerFilter viewerFilter : viewerFilters) {
				shouldFilterOut = viewerFilter.select(null, null, graphNode);
				if (shouldFilterOut == true) {// we should hide it if any filter
												// says
												// to hide it
					break;
				}
			}

			boolean oldVisibility = graphNode.isVisible();

			if (oldVisibility != shouldFilterOut) {
				graphNode.setVisible(false);
			}
		}

		for (IGraphArc graphArc : graphModel.getAllArcs()) {
			boolean shouldFilterOut = false;

			for (ViewerFilter viewerFilter : viewerFilters) {
				shouldFilterOut = viewerFilter.select(null, null, graphArc);
				if (shouldFilterOut == true) {// we should hide it if any filter
												// says
												// to hide it
					break;
				}
			}

			boolean oldVisibility = graphArc.isVisible();

			if (oldVisibility != shouldFilterOut) {
				graphArc.setVisible(false);
			}
		}
	}
}
