package au.uq.dke.comon_rcp2.data.table.edit;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import au.uq.dke.comon_rcp2.data.model.data.businessProcessManagement.ProcessRule;

public class BasicDialogCellEditor extends DialogCellEditor {

	public BasicDialogCellEditor(Composite parent) {
		super(parent);
	}

//	@Override
//	protected Button createButton(Composite parent) {
//		Button button = super.createButton(parent);
//		button.setText("");
//		return button;
//	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
//		MessageDialog.openInformation(cellEditorWindow.getShell(), "Test",
//				"It works");
//		int a = 1;
		Set<ProcessRule> newSet = new HashSet<ProcessRule>();
		newSet.add(new ProcessRule());
		newSet.addAll(((Set)this.getValue()));
		return newSet;
	}

}
