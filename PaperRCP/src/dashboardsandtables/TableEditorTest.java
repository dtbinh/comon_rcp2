package dashboardsandtables;

import java.awt.TextArea;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TableEditorTest extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TableEditorTest(Composite parent, int style) {
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

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(new Rectangle(47, 67, 595, 176));

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Check Column");

		TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
		tableColumn1.setWidth(100);
		tableColumn1.setText("Combo Column");

		TableItem tableItem = new TableItem(table, SWT.NONE);
		TableEditor editor = new TableEditor(table);

		Button checkButton = new Button(table, SWT.CHECK);
		checkButton.pack();

		editor.minimumWidth = checkButton.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(checkButton, tableItem, 0);
		editor = new TableEditor(table);

		Combo combo = new Combo(table, SWT.CHECK);
		combo.pack();

		Table t = new Table(table, SWT.NONE);
		
		TableItem tableItem2 = new TableItem(t, SWT.NONE);
		
		TextArea ta = new TextArea("hh");

		editor.minimumWidth = ta.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(ta, tableItem, 1);

	}
	
	public static void main(String[] args) {

		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		TableEditorTest t = new TableEditorTest(shell, 0);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
