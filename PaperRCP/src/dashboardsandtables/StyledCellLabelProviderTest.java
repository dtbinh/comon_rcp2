package dashboardsandtables;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class StyledCellLabelProviderTest extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StyledCellLabelProviderTest(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(60, 101, 64, 64);
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(0, 389);
		fd_composite.top = new FormAttachment(0, 101);
		fd_composite.left = new FormAttachment(0, 60);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new TableColumnLayout());
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		toolkit.paintBordersFor(table);
		
		TableViewerItem item = new TableItem(table, SWT.NONE);

		
		// define column
		TableViewerColumn colTesting = new TableViewerColumn(tableViewer, SWT.NONE);
		colTesting.getColumn().setText("Testing");
		colTesting.getColumn().setWidth(200);


		// set label provider
		colTesting.setLabelProvider(new StyledCellLabelProvider() {
		  @Override
		  public void update(ViewerCell cell) {
		    cell.setText("This is a test (15)");
		    StyleRange myStyledRange = 
		        new StyleRange(16, 2, null, 
		            Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
		    StyleRange[] range = { myStyledRange };
		    cell.setStyleRanges(range);
		    super.update(cell);
		    }
		}); 

	}
	
	public static void main(String args[]){
		final Display display = new Display();
		Shell shell = new Shell(display);
		
		StyledCellLabelProviderTest st = new StyledCellLabelProviderTest(shell, 0);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
}
