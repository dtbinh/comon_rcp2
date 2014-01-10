package au.uq.dke.comon_rcp2.application.views.navigator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonNavigator;

import au.uq.dke.comon_rcp2.application.views.filters.TestViewerFilter;
import au.uq.dke.comon_rcp2.application.views.navigator.model.NavigatorRoot;


public class OntologyNavigator extends CommonNavigator {
	
	public static final String ID = "au.uq.dke.comon_rcp2.ontologyNavigatorView";

	NavigatorRoot navigatorRoot = new NavigatorRoot();
	
	

	@Override
	public void createPartControl(Composite aParent) {
		super.createPartControl(aParent);
		
		TestViewerFilter testViewerFilter = new TestViewerFilter();
		super.getCommonViewer().addFilter(testViewerFilter);
		super.getCommonViewer().getFilters();
	}

	public OntologyNavigator() {
		// TODO Auto-generated constructor stub
	}
	
	public NavigatorRoot getInitialInput(){
		return navigatorRoot;
	}


}
