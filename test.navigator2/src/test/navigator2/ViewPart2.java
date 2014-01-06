package test.navigator2;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.navigator.CommonNavigator;


public class ViewPart2 extends CommonNavigator {
	NavigatorRoot navigatorRoot = new NavigatorRoot();
	
	

	public ViewPart2() {
		// TODO Auto-generated constructor stub
	}
	
	public NavigatorRoot getInitialInput(){
		return navigatorRoot;
	}


}
