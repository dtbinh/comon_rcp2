package test.eventadmin;

import java.text.DateFormat;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class ReceiverView2 extends ViewPart {
	private TableViewer viewer;

	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new FillLayout());
		viewer = new TableViewer(parent);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				//return DateFormat.getDateTimeInstance().format(element);
				return element.toString();
			}
		});

		BundleContext ctx = FrameworkUtil.getBundle(ReceiverView2.class)
				.getBundleContext();
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					
					viewer.add(event.getTopic() + ": " + event.getProperty("DATA"));
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							viewer.add(event.getProperty("DATA"));
						}
					});
				}
			}
		};

		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(EventConstants.EVENT_TOPIC, "viewcommunication/*");
		ctx.registerService(EventHandler.class, handler, properties);
	}

	@Override
	public void setFocus() {
		viewer.getTable().setFocus();
	}
}
