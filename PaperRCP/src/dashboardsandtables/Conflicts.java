package dashboardsandtables;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;

public class Conflicts extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Conflicts(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Form frmConflicts = toolkit.createForm(this);
		frmConflicts.setBounds(10, 34, 413, 332);
		toolkit.paintBordersFor(frmConflicts);
		frmConflicts.setText("Conflicts");
		
		table = toolkit.createTable(frmConflicts.getBody(), SWT.NONE);
		table.setBounds(21, 21, 412, 320);
		toolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnSource = new TableColumn(table, SWT.NONE);
		tblclmnSource.setWidth(100);
		tblclmnSource.setText("Source");
		
		TableColumn tblclmnConflicts = new TableColumn(table, SWT.NONE);
		tblclmnConflicts.setWidth(100);
		tblclmnConflicts.setText("Conflicts");
		
		TableColumn tblclmnSolution = new TableColumn(table, SWT.NONE);
		tblclmnSolution.setWidth(100);
		tblclmnSolution.setText("Solution");

	}
}
