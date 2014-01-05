package test.eventadmin;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
//		layout.addView("test.eventadmin.senderview", IPageLayout.TOP,
//		        IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);		
	}

}
