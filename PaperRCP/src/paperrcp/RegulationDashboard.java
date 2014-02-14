package paperrcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class RegulationDashboard extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	final ScrolledForm form = toolkit.createScrolledForm(this);

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RegulationDashboard(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FormLayout());

		FormData fd_scrldfrmNewScrolledform = new FormData();
		fd_scrldfrmNewScrolledform.top = new FormAttachment(0, 10);
		fd_scrldfrmNewScrolledform.left = new FormAttachment(0, 10);
		form.setLayoutData(fd_scrldfrmNewScrolledform);
		toolkit.paintBordersFor(form);
		form.setText("Regulations");
		{
			TableWrapLayout tableWrapLayout = new TableWrapLayout();
			form.getBody().setLayout(tableWrapLayout);
		}

		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE
				| Section.TITLE_BAR);
		toolkit.paintBordersFor(section);
		section.setText("Hippa");
		section.setExpanded(true);


		Label lblNewLabel = toolkit.createLabel(section, "New Label", SWT.NONE);
		section.setClient(lblNewLabel);
		
		Section sctnNewSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnNewSection);
		sctnNewSection.setText("New Section");
		sctnNewSection.setExpanded(true);
		
		Label lblNewLabel_1 = new Label(sctnNewSection, SWT.NONE);
		toolkit.adapt(lblNewLabel_1, true, true);
		sctnNewSection.setClient(lblNewLabel_1);
		lblNewLabel_1.setText("New Label 2");

	}

	public static void main(String args[]) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		RegulationDashboard r = new RegulationDashboard(shell, 0);
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
