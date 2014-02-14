package dashboardsandtables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ConflictsMultiline {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Multiple lines in a TableItem");
		shell.setLayout(new FillLayout());

		final TextStyle hpyerlinkstyle = new TextStyle();
		hpyerlinkstyle.foreground = display.getSystemColor(SWT.COLOR_BLUE);
		hpyerlinkstyle.underline = true;

		final TextStyle normalStyle = new TextStyle();

		final Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Conflicting Sources");
		
		column = new TableColumn(table, SWT.NONE);
		column.setText("Conflicts");

		column = new TableColumn(table, SWT.NONE);
		column.setText("Solution");

		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(new String[] {
				"• HIPAA §164.530(j)(2)"
						+ "\n• Privacy Act of 1974 (cited at §164.524(a)(2)(iv)) "
						+ "\n• 29 CFR 1910.1020 (cited at §164.512(b)(1)(v)(C)) ",

				"Length of data retention: "
						+ "\n• HIPAA: at least 5 years "
						+ "\n• Privacy Act: at least 6 years or the life of the record, whichever is longer "
						+ "\n• 29 CFR 1910.1020: at least 30 years if the employee worked for longer than a year ",

				"• Comply with most restrictive law "
						+ "\n• Keep data separate " 
						
		});

		// item = new TableItem(table, SWT.NONE);
		// item.setText(new String[] { ,
		// "item col2" });

		Listener paintListener = new Listener() {
			public void handleEvent(Event event) {
				final TextLayout textLayout = new TextLayout(display);

				TableItem item = (TableItem) event.item;

				switch (event.type) {
				case SWT.MeasureItem: {
					String text = item.getText(event.index);
					Point size = event.gc.textExtent(text);

					event.width = size.x;
					event.height = Math.max(event.height, size.y);

					break;
				}
				case SWT.PaintItem: {
					textLayout.setText(item.getText(event.index));
					if (event.index == 0) {
						textLayout.setStyle(hpyerlinkstyle, 0, textLayout
								.getText().length());
					}

					textLayout.setWidth(((Table) event.widget).getColumn(
							event.index).getWidth());
					textLayout.draw(event.gc, event.x, event.y);

					break;
				}
				case SWT.EraseItem: {
					event.detail &= ~SWT.FOREGROUND;
					break;
				}
				case SWT.Resize: {
					int a = 1;
				}
				default: {
					int a = 1;
				}

				}
			}

		};
		table.addListener(SWT.MeasureItem, paintListener);
		table.addListener(SWT.PaintItem, paintListener);
		table.addListener(SWT.EraseItem, paintListener);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(i).pack();
		}
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
