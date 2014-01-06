package test.eventadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

public class SenderView2 extends ViewPart {
	public static final String ID = "viewcommunication.views.SenderView";
	private Button b;

	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout());
		b = new Button(parent, SWT.PUSH);
		b.setText("Send Event 2");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BundleContext ctx = FrameworkUtil.getBundle(SenderView.class)
						.getBundleContext();
				ServiceReference<EventAdmin> ref = ctx
						.getServiceReference(EventAdmin.class);
				EventAdmin eventAdmin = ctx.getService(ref);
				Map<String, Object> properties = new HashMap<String, Object>();
				properties.put("DATA", new Date());

				Event event = new Event("viewcommunication/syncEvent2",
						properties);
				eventAdmin.sendEvent(event);

				event = new Event("viewcommunication/asyncEvent2", properties);
//				eventAdmin.postEvent(event);
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		b.setFocus();
	}
}
