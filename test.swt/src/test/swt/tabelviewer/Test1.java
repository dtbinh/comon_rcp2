package test.swt.tabelviewer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class Test1 {

	public static void main(String args[]){
		Display display = new Display();
		Shell shell = new Shell(display);
		TableViewer  viewer = null;
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
			      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

			// create the columns 
			// not yet implemented
			createColumns(viewer);

			// make lines and header visible
			final Table table = viewer.getTable();
			table.setHeaderVisible(true);
			table.setLinesVisible(true); 
	}
}
