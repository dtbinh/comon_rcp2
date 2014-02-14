package dashboardsandtables;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class TopDashboard extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TopDashboard(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Form frmNewForm = toolkit.createForm(this);
		frmNewForm.setBounds(26, 41, 852, 609);
		toolkit.paintBordersFor(frmNewForm);
		frmNewForm.setText("New Form");
		{
			TableWrapLayout tableWrapLayout = new TableWrapLayout();
			tableWrapLayout.numColumns = 5;
			frmNewForm.getBody().setLayout(tableWrapLayout);
		}
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		
		Section sctnNewSection = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnNewSection);
		sctnNewSection.setText("Regulation");
		sctnNewSection.setExpanded(true);
		
		Composite composite = toolkit.createComposite(sctnNewSection, SWT.NONE);
		toolkit.paintBordersFor(composite);
		sctnNewSection.setClient(composite);
		{
			TableWrapLayout twl_composite = new TableWrapLayout();
			composite.setLayout(twl_composite);
		}
		
		Section sctnNewSection_3 = toolkit.createSection(composite, Section.TWISTIE | Section.TITLE_BAR);
		sctnNewSection_3.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE, 1, 1));
		toolkit.paintBordersFor(sctnNewSection_3);
		sctnNewSection_3.setText("HIPAA");
		sctnNewSection_3.setExpanded(true);
		
		Composite composite_2 = new Composite(sctnNewSection_3, SWT.NONE);
		toolkit.adapt(composite_2);
		toolkit.paintBordersFor(composite_2);
		sctnNewSection_3.setClient(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Hyperlink hprlnkDetail = toolkit.createHyperlink(composite_2, "Detail", SWT.NONE);
		toolkit.paintBordersFor(hprlnkDetail);
		
		Hyperlink hprlnkRisks = toolkit.createHyperlink(composite_2, "Risks:", SWT.NONE);
		toolkit.paintBordersFor(hprlnkRisks);
		
		Hyperlink hprlnkConflicts = toolkit.createHyperlink(composite_2, "Conflicts:", SWT.NONE);
		toolkit.paintBordersFor(hprlnkConflicts);
		
		Hyperlink hprlnkAmbiguities = toolkit.createHyperlink(composite_2, "Ambiguities:", SWT.NONE);
		toolkit.paintBordersFor(hprlnkAmbiguities);
		
		Hyperlink hprlnkCompliance = toolkit.createHyperlink(composite_2, "Compliance:", SWT.NONE);
		toolkit.paintBordersFor(hprlnkCompliance);
		
		Section sctnSox = toolkit.createSection(composite, Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnSox);
		sctnSox.setText("SOX");
		
		Section sctnNewSection_1 = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnNewSection_1);
		sctnNewSection_1.setText("Control");
		sctnNewSection_1.setExpanded(true);
		
		Composite composite_1 = toolkit.createComposite(sctnNewSection_1, SWT.NONE);
		toolkit.paintBordersFor(composite_1);
		sctnNewSection_1.setClient(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		Hyperlink hprlnkNewHyperlink = toolkit.createHyperlink(composite_1, "New Hyperlink", SWT.NONE);
		toolkit.paintBordersFor(hprlnkNewHyperlink);
		
		Section sctnNewSection_2 = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnNewSection_2);
		sctnNewSection_2.setText("Risk");
		sctnNewSection_2.setExpanded(true);
		
		Section sctnRule = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnRule);
		sctnRule.setText("Rule");
		
		Section sctnProcess = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnProcess);
		sctnProcess.setText("Process");

	}
}
