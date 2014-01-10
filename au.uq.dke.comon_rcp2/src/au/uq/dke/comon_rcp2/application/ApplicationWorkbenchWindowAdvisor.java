package au.uq.dke.comon_rcp2.application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import au.uq.dke.comon_rcp2.application.views.graph.OntologyGraph;
import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(800, 600));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);
		OntologyGraphModelImpl.getInstance();
	}

	@Override
	public void postWindowCreate() {
		// TODO Auto-generated method stub
		super.postWindowCreate();
	}

	@Override
	public void postWindowOpen() {
		//OntologyGraph.getInstance().performLayoutWithoutFilter();
		super.postWindowOpen();
	}
}
