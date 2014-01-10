package au.uq.dke.comon_rcp2.application.views.graph;

import java.util.Collection;

import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

import au.uq.dke.comon_rcp2.application.views.filters.FilterManager;
import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModel;
import ca.uvic.cs.chisel.cajun.actions.LayoutManager;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class OntologyGraph extends FlatGraph {

	private static OntologyGraph instance;

	public static OntologyGraph getInstance() {
		if (instance == null) {
			// TODO Exception
		}
		return instance;
	}

	public static void createInstance(Composite parent) {
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

	OntologyGraphModel graphModel = OntologyGraphModel.getInstance();

	private OntologyGraph(Composite parent) {
		super(parent);
		super.setModel(graphModel);
	}

	public void applyFilters() {

//		ViewerFilter[] viewerFilters = null;
//
//		IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//				.getActivePage().findView(OntologyNavigator.ID);
//		if (part instanceof OntologyNavigator) {
//			OntologyNavigator view = (OntologyNavigator) part;
//			viewerFilters = ((OntologyNavigator) part).getCommonViewer()
//					.getFilters();
//		}

		Collection<ViewerFilter> viewerFilters = FilterManager.getInstance().getAllFilters();
		
		for (IGraphNode graphNode : graphModel.getAllNodes()) {
			boolean shouldBeVisible = true;

			for (ViewerFilter viewerFilter : viewerFilters) {
				shouldBeVisible = viewerFilter.select(null, null, graphNode);
				if (shouldBeVisible == false) {// we should hide it if any filter
												// says
												// to hide it
					break;
				}
			}

			boolean oldVisibility = graphNode.isVisible();

			if (oldVisibility != shouldBeVisible) {
				graphNode.setVisible(shouldBeVisible);
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
