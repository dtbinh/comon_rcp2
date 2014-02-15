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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.forms.widgets.FormText;

public class TopDashboard2 extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TopDashboard2(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Form frmNewForm = toolkit.createForm(this);
		frmNewForm.getHead().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		frmNewForm.setBounds(10, 67, 1109, 465);
		toolkit.paintBordersFor(frmNewForm);
		frmNewForm.setText("Compliance Governance Dashboard");
		frmNewForm.getBody().setLayout(new GridLayout(3, false));
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		new Label(frmNewForm.getBody(), SWT.NONE);
		
		Section sctnRegulation = toolkit.createSection(frmNewForm.getBody(), Section.TITLE_BAR);
		GridData gd_sctnRegulation = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_sctnRegulation.widthHint = 558;
		gd_sctnRegulation.heightHint = 217;
		sctnRegulation.setLayoutData(gd_sctnRegulation);
		toolkit.paintBordersFor(sctnRegulation);
		sctnRegulation.setText("Regulation");
		sctnRegulation.setExpanded(true);
		
		Composite composite = new Composite(sctnRegulation, SWT.NONE);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		sctnRegulation.setClient(composite);
		{
			TableWrapLayout twl_composite = new TableWrapLayout();
			twl_composite.numColumns = 2;
			composite.setLayout(twl_composite);
		}
		
		FormText formText = toolkit.createFormText(composite, false);
		formText.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 1));
		toolkit.paintBordersFor(formText);
		formText.setText("A new amendment of HIPPA needs implementation", false, false);
		
		Hyperlink hprlnkImpact = toolkit.createHyperlink(composite, "Impact Analysis", SWT.NONE);
		toolkit.paintBordersFor(hprlnkImpact);
		
		FormText formText_1 = toolkit.createFormText(composite, false);
		TableWrapData twd_formText_1 = new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 1);
		twd_formText_1.heightHint = 39;
		formText_1.setLayoutData(twd_formText_1);
		toolkit.paintBordersFor(formText_1);
		formText_1.setText("<B>3</B> Ambiguties needs to be resolved", false, false);
		
		Hyperlink hprlnkSeeDetails = toolkit.createHyperlink(composite, "See Details", SWT.NONE);
		toolkit.paintBordersFor(hprlnkSeeDetails);
		
		FormText formText_2 = toolkit.createFormText(composite, false);
		TableWrapData twd_formText_2 = new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 1);
		twd_formText_2.heightHint = 47;
		formText_2.setLayoutData(twd_formText_2);
		toolkit.paintBordersFor(formText_2);
		formText_2.setText("2 Conflicts need to be resolved", false, false);
		
		Hyperlink hprlnkSeeDetails_1 = toolkit.createHyperlink(composite, "See Details", SWT.NONE);
		toolkit.paintBordersFor(hprlnkSeeDetails_1);
		new Label(composite, SWT.NONE);
		
		Hyperlink hprlnkMore = toolkit.createHyperlink(composite, "More...", SWT.NONE);
		hprlnkMore.setLayoutData(new TableWrapData(TableWrapData.RIGHT, TableWrapData.TOP, 1, 1));
		toolkit.paintBordersFor(hprlnkMore);
		
		Section sctnControl = toolkit.createSection(frmNewForm.getBody(), Section.TITLE_BAR);
		sctnControl.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		toolkit.paintBordersFor(sctnControl);
		sctnControl.setText("Control");
		
		Composite composite_1 = new Composite(sctnControl, SWT.NONE);
		toolkit.adapt(composite_1);
		toolkit.paintBordersFor(composite_1);
		sctnControl.setClient(composite_1);
		composite_1.setLayout(new GridLayout(2, false));
		
		FormText formText_6 = toolkit.createFormText(composite_1, false);
		GridData gd_formText_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_formText_6.widthHint = 208;
		formText_6.setLayoutData(gd_formText_6);
		toolkit.paintBordersFor(formText_6);
		formText_6.setText("1 new control needs implementation", false, false);
		new Label(composite_1, SWT.NONE);
		
		FormText formText_7 = toolkit.createFormText(composite_1, false);
		GridData gd_formText_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_formText_7.widthHint = 169;
		formText_7.setLayoutData(gd_formText_7);
		toolkit.paintBordersFor(formText_7);
		formText_7.setText("Current Control Objectives: 153", false, false);
		
		Hyperlink hprlnkSeeControl = toolkit.createHyperlink(composite_1, "Control Graph", SWT.NONE);
		hprlnkSeeControl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		toolkit.paintBordersFor(hprlnkSeeControl);
		new Label(frmNewForm.getBody(), SWT.NONE);
		
		Section sctnRisk = toolkit.createSection(frmNewForm.getBody(), Section.TITLE_BAR);
		sctnRisk.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		toolkit.paintBordersFor(sctnRisk);
		sctnRisk.setText("Risk");
		
		Composite composite_2 = new Composite(sctnRisk, SWT.NONE);
		toolkit.adapt(composite_2);
		toolkit.paintBordersFor(composite_2);
		sctnRisk.setClient(composite_2);
		composite_2.setLayout(new GridLayout(2, false));
		
		FormText formText_3 = toolkit.createFormText(composite_2, false);
		GridData gd_formText_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_formText_3.widthHint = 416;
		formText_3.setLayoutData(gd_formText_3);
		toolkit.paintBordersFor(formText_3);
		formText_3.setText("3 Obligations and 4 Prohibitions may have risk of not being compliant", false, false);
		new Label(composite_2, SWT.NONE);
		
		FormText formText_4 = toolkit.createFormText(composite_2, false);
		toolkit.paintBordersFor(formText_4);
		formText_4.setText("2 new Risks are identified and needs evaluation", false, false);
		
		Hyperlink hprlnkEvaluate = toolkit.createHyperlink(composite_2, "Evaluate", SWT.NONE);
		GridData gd_hprlnkEvaluate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_hprlnkEvaluate.widthHint = 125;
		hprlnkEvaluate.setLayoutData(gd_hprlnkEvaluate);
		toolkit.paintBordersFor(hprlnkEvaluate);
		
		Section sctnProcess = toolkit.createSection(frmNewForm.getBody(), Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnProcess);
		sctnProcess.setText("Process & Rule");
		
		Composite composite_3 = toolkit.createComposite(sctnProcess, SWT.NONE);
		toolkit.paintBordersFor(composite_3);
		sctnProcess.setClient(composite_3);
		composite_3.setLayout(new GridLayout(1, false));
		
		FormText formText_5 = toolkit.createFormText(composite_3, false);
		toolkit.paintBordersFor(formText_5);
		formText_5.setText("5 processes are modified and need review for compliance", false, false);
		
		FormText formText_11 = toolkit.createFormText(composite_3, false);
		toolkit.paintBordersFor(formText_11);
		formText_11.setText("1 Business Rule needs modification", false, false);
		
		FormText formText_8 = toolkit.createFormText(composite_3, false);
		toolkit.paintBordersFor(formText_8);
		formText_8.setText("Business Rules: 231", false, false);
		
		FormText formText_9 = toolkit.createFormText(composite_3, false);
		toolkit.paintBordersFor(formText_9);
		formText_9.setText("Business Process Models: 45", false, false);
		
		FormText formText_10 = toolkit.createFormText(composite_3, false);
		toolkit.paintBordersFor(formText_10);
		formText_10.setText("Process Instances: 3891", false, false);
		

	}
}
