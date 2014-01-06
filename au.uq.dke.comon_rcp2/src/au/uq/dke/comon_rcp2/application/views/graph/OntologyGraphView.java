package au.uq.dke.comon_rcp2.application.views.graph;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;

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

import au.uq.dke.comon_rcp2.ontology.graph.model.IOntologyGraphModel;
import au.uq.dke.comon_rcp2.ontology.graph.model.OntologyGraphModelImpl;
import au.uq.dke.comon_rcp2.ontology.graph.view.Graph;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolox.swing.PScrollPane;

public class OntologyGraphView extends ViewPart {
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
	    
	    String srcObject = "src";
	    String dstObject = "dst";
	    
	    String rel = "rel";
	    
	    IOntologyGraphModel graphModel = new OntologyGraphModelImpl();
	    IGraphNode srcNode = graphModel.addNode(srcObject);
	    IGraphNode dstNode = graphModel.addNode(dstObject);
	    graphModel.addArc(rel, srcNode, dstNode);
	    
	    Graph graph = new Graph((OntologyGraphModelImpl)graphModel);
	    JPanel graphPanel = new JPanel();
	    graphPanel.add(new PScrollPane(graph),BorderLayout.CENTER);
	   // graphPanel.add(new JButton("btn1"));
	    graphPanel.setVisible(true);
	    frame.add(graphPanel);
	    //frame.add(graph);
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
		//viewer.getControl().setFocus();
	}
}