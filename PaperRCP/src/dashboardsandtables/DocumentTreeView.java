package dashboardsandtables;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

public class DocumentTreeView extends ViewPart {
	public static final String ID = "PaperRCP.view";

	private TableViewer viewer;
	private ScrolledForm form;
	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
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
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		form = toolkit.createScrolledForm(parent);
		form.setText("HIPPA");
		GridLayout layout = new GridLayout();
		form.getBody().setLayout(layout);
		
		Tree tree = toolkit.createTree(form.getBody(), SWT.NONE);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		toolkit.paintBordersFor(tree);
		tree.setHeaderVisible(true);
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
		column1.setText("Structure");
		column1.setWidth(200);
		TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
		column2.setText("Discription");
		column2.setWidth(200);
		TreeColumn column3 = new TreeColumn(tree, SWT.RIGHT);
		column3.setText("Obligations");
		column3.setWidth(200);
		TreeColumn column4 = new TreeColumn(tree, SWT.RIGHT);
		column4.setText("Prohibition");
		column4.setWidth(200);
		TreeColumn column5 = new TreeColumn(tree, SWT.RIGHT);
		column5.setText("Rights");
		column5.setWidth(200);

		for (int i = 0; i < 4; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(new String[] { "164" + i, "content1", "31" ,"44", "3"});
			for (int j = 0; j < 4; j++) {
				TreeItem subItem = new TreeItem(item, SWT.NONE);
				subItem.setText(new String[] { "subitem " + j, "jklmnop", "qrs" });
				for (int k = 0; k < 4; k++) {
					TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
					subsubItem.setText(new String[] { "subsubitem " + k, "tuv",
							"wxyz" });
				}
			}
		}

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}