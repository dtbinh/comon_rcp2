package au.uq.dke.comon_rcp2.data.table.edit;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement.ProcessRule;
import au.uq.dke.comon_rcp2.data.service.MockDataServiceImpl;
import au.uq.dke.comon_rcp2.data.table.GenericTableUnit;
import au.uq.dke.comon_rcp2.data.table.RecordsAddingUnit;
import au.uq.dke.comon_rcp2.data.table.model.ProcessObject;
import au.uq.dke.comon_rcp2.data.utils.SetUtils;

public class BasicDialogCellEditor extends DialogCellEditor {
	private Composite parent = null;
	private Field field;

	public BasicDialogCellEditor(Composite parent, Field field) {
		super(parent);
		this.parent = parent;
		this.field = field;
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

		Class beanType = SetUtils.getSetElementType(this.field);
		cellEditorWindow.getData();
		Collection<Object> fullSet = MockDataServiceImpl.getInstance().getDataSet(beanType);
		Collection<Object> selectedSet = new HashSet();

		
		Shell shell = new Shell(Display.getCurrent());

		new RecordsAddingUnit(shell, beanType, selectedSet, fullSet);
		shell.pack();
		shell.open();

		Set<ProcessRule> newSet = new HashSet<ProcessRule>();
		newSet.add(new ProcessRule());
		newSet.addAll(((Set) this.getValue()));
		return null;
	}

}
