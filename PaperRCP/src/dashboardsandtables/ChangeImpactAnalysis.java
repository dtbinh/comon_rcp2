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
import org.eclipse.swt.widgets.Button;

public class ChangeImpactAnalysis extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ChangeImpactAnalysis(Composite parent, int style) {
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
		frmNewForm.setText("Change Impact Analysis");
		{
			frmNewForm.getBody().setLayout(new TableWrapLayout());
		}
		
		Section sctnChangeEventApplay = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnChangeEventApplay);
		sctnChangeEventApplay.setText("Event");
		sctnChangeEventApplay.setExpanded(true);
		
		Composite composite = toolkit.createComposite(sctnChangeEventApplay, SWT.NONE);
		toolkit.paintBordersFor(composite);
		sctnChangeEventApplay.setClient(composite);
		{
			composite.setLayout(new TableWrapLayout());
		}
		
		FormText formText = toolkit.createFormText(composite, false);
		formText.setSize(371, 25);
		toolkit.paintBordersFor(formText);
		formText.setText("Applay a complementing Regulation of HIPAA", false, false);
		
		Section sctnAffectedComplianceRequirements = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		TableWrapData twd_sctnAffectedComplianceRequirements = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP, 1, 1);
		twd_sctnAffectedComplianceRequirements.heightHint = 168;
		sctnAffectedComplianceRequirements.setLayoutData(twd_sctnAffectedComplianceRequirements);
		toolkit.paintBordersFor(sctnAffectedComplianceRequirements);
		sctnAffectedComplianceRequirements.setText("Affected Compliance Requirements");
		sctnAffectedComplianceRequirements.setExpanded(true);
		
		Composite composite_1 = toolkit.createComposite(sctnAffectedComplianceRequirements, SWT.NONE);
		toolkit.paintBordersFor(composite_1);
		sctnAffectedComplianceRequirements.setClient(composite_1);
		{
			composite_1.setLayout(new TableWrapLayout());
		}
		
		FormText formText_1 = toolkit.createFormText(composite_1, false);
		formText_1.setBounds(0, 0, 116, 25);
		toolkit.paintBordersFor(formText_1);
		formText_1.setText("3 Obligations are added", false, false);
		
		FormText formText_2 = toolkit.createFormText(composite_1, false);
		toolkit.paintBordersFor(formText_2);
		formText_2.setText("4 Prohibitions are added", false, false);
		
		FormText formText_3 = toolkit.createFormText(composite_1, false);
		toolkit.paintBordersFor(formText_3);
		formText_3.setText("1 Enhancement", false, false);
		
		Section sctnAffectedInternalControls = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnAffectedInternalControls);
		sctnAffectedInternalControls.setText("Affected Internal Controls");
		sctnAffectedInternalControls.setExpanded(true);
		
		Section sctnAffectedBusinessProcesses = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnAffectedBusinessProcesses);
		sctnAffectedBusinessProcesses.setText("Affected Business Processes");
		sctnAffectedBusinessProcesses.setExpanded(true);
		
		Section sctnAffectedBusinessRules = toolkit.createSection(frmNewForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		toolkit.paintBordersFor(sctnAffectedBusinessRules);
		sctnAffectedBusinessRules.setText("Affected Business Rules");
		sctnAffectedBusinessRules.setExpanded(true);
		

	}
}
