package de.vogella.jface.tableviewer;

import java.awt.Color;
import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;

public class RecordsAddingUnit {

	Composite parent;
	Class beanType;
	GenericTableUnit selectedUnit;
	GenericTableUnit fullUnit;
	Collection selectedSet;
	Collection fullSet;

	public RecordsAddingUnit(Composite parent, Class beanType,
			Collection selectedSet, Collection fullSet) {
		Composite maincomposite = new Composite(parent, SWT.NONE);
		maincomposite.pack();
		maincomposite.setLayout(new GridLayout(2, false));


		this.parent = parent;
		this.beanType = beanType;
		this.selectedSet = selectedSet;
		this.fullSet = fullSet;

		fullUnit = new GenericTableUnit(maincomposite, this.beanType, true);
		fullUnit.setDataInput(this.fullSet);
		fullUnit.init();

		selectedUnit = new GenericTableUnit(maincomposite, this.beanType, false);
		selectedUnit.setDataInput(this.selectedSet);
		selectedUnit.init();

		Button addBtn = new Button(maincomposite, SWT.PUSH);
		addBtn.setText("Add");
		addBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = fullUnit.getViewer().getTable()
						.getSelection();
				for (TableItem selectedItem : selectedItems) {

					if (RecordsAddingUnit.this.beanType
							.isAssignableFrom(selectedItem.getData().getClass())) {
						RecordsAddingUnit.this.selectedSet.add(selectedItem.getData());
					}
				}
				RecordsAddingUnit.this.fullUnit.refresh();
				RecordsAddingUnit.this.selectedUnit.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		//maincomposite.setBounds(10, 5, 775, 505);
	}
}
