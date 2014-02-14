package dashboardsandtables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TestStyle {

	final static Display display = new Display();

	public static void main(String[] args) {
		Shell shell = new Shell(display);
		shell.setText("Table: Change style multiple times in cell");
		shell.setLayout(new FillLayout());
		Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		for (int i = 0; i < 10; i++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText("item" + i + "\nnew \nline");
		}
		final TextLayout textLayout = new TextLayout(display);
		textLayout.setText("SWT: Standard Widget Toolkit\nnew line");
		Font font1 = new Font(display, "Tahoma", 10, SWT.BOLD);
		final TextStyle style1 = new TextStyle(font1,
				display.getSystemColor(SWT.COLOR_BLUE), null);
		textLayout.setStyle(style1, 0, textLayout.getText().length());

		/*
		 * NOTE: MeasureItem, PaintItem and EraseItem are called repeatedly.
		 * Therefore, it is critical for performance that these methods be as
		 * efficient as possible.
		 */
		table.addListener(SWT.PaintItem, new Listener() {
			@Override
			public void handleEvent(Event event) {
				//TextLayout textLayout = new TextLayout(display);
				textLayout.setText(((TableItem)event.item).getText());
				style1.underline= true;
				textLayout.setStyle(style1, 0, textLayout.getText().length());
				textLayout.draw(event.gc, event.x, event.y);

			}
		});
		final Rectangle textLayoutBounds = textLayout.getBounds();
		table.addListener(SWT.MeasureItem, new Listener() {
			@Override
			public void handleEvent(Event e) {
				e.width = textLayoutBounds.width + 2;
				e.height = textLayoutBounds.height + 50;
			}
		});
		
		
		
		shell.setSize(400, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		font1.dispose();
		textLayout.dispose();
		display.dispose();
	}
}