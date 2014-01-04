package au.uq.dke.comon_rcp2.application.views;

import java.awt.Frame;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import au.uq.dke.comon_rcp2.ontology.ui.model.GraphModel;
import au.uq.dke.comon_rcp2.ontology.ui.model.IGraphModel;
import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicGraphNode;
import au.uq.dke.comon_rcp2.ontology.ui.view.Graph;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;

public class OntologyView extends ViewPart {
	public static final String ID = "au.uq.dke.comon_rcp2.view";

	private TableViewer viewer;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
	    Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
	    Frame frame = SWT_AWT.new_Frame(composite);
	    
	    //JButton jbt1 = new JButton();
	    
	    String userObject = "hi";
	    
	    IGraphModel graphModel = new GraphModel();
	    graphModel.addNode(userObject);
	    Graph graph = new Graph();
	    frame.add(graph);
	    graph.performLayout();

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
		viewer.getControl().setFocus();
	}
}