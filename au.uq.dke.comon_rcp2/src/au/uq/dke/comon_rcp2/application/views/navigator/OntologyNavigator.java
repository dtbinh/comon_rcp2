package au.uq.dke.comon_rcp2.application.views.navigator;

import org.eclipse.ui.navigator.CommonNavigator;

import au.uq.dke.comon_rcp2.application.views.navigator.model.NavigatorRoot;


public class OntologyNavigator extends CommonNavigator {
	NavigatorRoot navigatorRoot = new NavigatorRoot();
	
	

	public OntologyNavigator() {
		// TODO Auto-generated constructor stub
	}
	
	public NavigatorRoot getInitialInput(){
		return navigatorRoot;
	}


}
