package au.uq.dke.comon_rcp2.data.table.edit;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement.ProcessRule;
import au.uq.dke.comon_rcp2.data.table.GenericTableUnit;
import au.uq.dke.comon_rcp2.data.table.RecordsAddingUnit;
import au.uq.dke.comon_rcp2.data.table.model.ProcessObject;

public class BasicDialogCellEditor extends DialogCellEditor {
	private Composite parent = null;

	public BasicDialogCellEditor(Composite parent) {
		super(parent);
		this.parent = parent;
	}

	// @Override
	// protected Button createButton(Composite parent) {
	// Button button = super.createButton(parent);
	// button.setText("");
	// return button;
	// }

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		// MessageDialog.openInformation(cellEditorWindow.getShell(), "Test",
		// "It works");

		Set<Object> fullSet = new HashSet();
		Set<Object> selectedSet = new HashSet();

		Class beanType = ProcessObject.class;
		GenericTableUnit view;

		for (int i = 0; i < 3; i++) {
			Object bean = null;
			try {
				bean = beanType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fullSet.add(bean);
		}
		
		Shell shell = new Shell(Display.getCurrent());

		new RecordsAddingUnit(shell, beanType, selectedSet, fullSet);
		shell.pack();
		shell.open();

		Set<ProcessRule> newSet = new HashSet<ProcessRule>();
		newSet.add(new ProcessRule());
		newSet.addAll(((Set) this.getValue()));
		return newSet;
	}

}
