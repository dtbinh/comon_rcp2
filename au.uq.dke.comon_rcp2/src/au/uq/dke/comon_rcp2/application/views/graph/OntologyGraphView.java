package au.uq.dke.comon_rcp2.application.views.graph;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import au.uq.dke.comon_rcp2.application.views.filters.FilterManager;

public class OntologyGraphView extends ViewPart {
	public static final String ID = "au.uq.dke.comon_rcp2.view";

	private TableViewer viewer;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		OntologyGraph.createInstance(parent);
	    OntologyGraph graph = OntologyGraph.getInstance();
	   // graph.performLayoutWithFilter();
		
		
		
		
		
//		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
//	    Frame frame = SWT_AWT.new_Frame(composite);
//	    
//	    //JButton jbt1 = new JButton();
//	    
//	    
//	    OntologyGraph graph = OntologyGraph.getInstance();
//	    JPanel graphPanel = new JPanel();
//	    graphPanel.add(new PScrollPane(graph),BorderLayout.CENTER);
//	   // graphPanel.add(new JButton("btn1"));
//	    graphPanel.setVisible(true);
//	    //frame.add(graphPanel);
//	    frame.add(graph);
//	    graph.performLayout();

	}

	
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent instanceof Object[]) {
				return (Object[]) parent;
			}
	        return new Object[0];
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}