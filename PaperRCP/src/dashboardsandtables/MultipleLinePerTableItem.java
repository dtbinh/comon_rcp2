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

public class MultipleLinePerTableItem {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Multiple lines in a TableItem");
		shell.setLayout(new FillLayout());

		Font font1 = new Font(display, "Tahoma", 10, SWT.BOLD);
		final TextStyle style1 = new TextStyle(font1,
				display.getSystemColor(SWT.COLOR_BLUE), null);
		style1.underline= true;
		
		final TextStyle style2 = new TextStyle();
		style2.foreground = display.getSystemColor(SWT.COLOR_BLUE);
		style2.underline = true;


		
		final Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		int columnCount = 2;
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Column A");
		column = new TableColumn(table, SWT.NONE);
		column.setText("Column B");

			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "item1 col1\nline2", "item1 col2"});

			item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "item2 col1\nline2 \nline3\nline4", "item col2"});
		
			
			Listener paintListener = new Listener() {
			public void handleEvent(Event event) {
				final TextLayout textLayout = new TextLayout(display);

				TableItem item = (TableItem) event.item;

				switch (event.type) {
				case SWT.MeasureItem: {
					String text = item.getText(event.index);
//					textLayout.setStyle(style1, 0, textLayout.getText().length());
//					textLayout.setWidth(((Table)event.widget).getColumn(event.index).getWidth());
					Point size = event.gc.textExtent(text);

					event.width = size.x;
					event.height = Math.max(event.height, size.y);

//					event.width = textLayout.getWidth();
//					event.height = textLayout.getBounds().height;

					break;
				}
				case SWT.PaintItem: {
					textLayout.setText(item.getText(event.index));
					textLayout.setStyle(style2, 0, textLayout.getText().length());
					textLayout.setWidth(((Table)event.widget).getColumn(event.index).getWidth());
					textLayout.draw(event.gc, event.x, event.y);

//					TableItem item = (TableItem) event.item;
//					String text = item.getText(event.index);
//					Point size = event.gc.textExtent(text);
//					int offset2 = event.index == 0 ? Math.max(0,
//							(event.height - size.y) / 2) : 0;
//					event.gc.drawText(text, event.x, event.y + offset2, true);
					break;
				}
				case SWT.EraseItem: {
					event.detail &= ~SWT.FOREGROUND;
					break;
				}
			     case SWT.Resize:{
			    	 int a = 1;
			     }
			     default:{
			    	 int a = 1;
			     }

				}
			}

		};
		table.addListener(SWT.MeasureItem, paintListener);
		table.addListener(SWT.PaintItem, paintListener);
		table.addListener(SWT.EraseItem, paintListener);
		for (int i = 0; i < columnCount; i++) {
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
