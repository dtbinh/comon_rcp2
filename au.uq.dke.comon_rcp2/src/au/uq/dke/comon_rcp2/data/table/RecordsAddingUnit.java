package au.uq.dke.comon_rcp2.data.table;

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

	private Composite parent;
	private Class beanType;
	private GenericTableUnit selectedUnit;
	private GenericTableUnit fullUnit;
	private Collection selectedSet;
	private Collection fullSet;

	private Button addToAssociationSetBtn;
	private Button removeFromAssociationSetBtn;

	
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
		fullUnit.getAddBtn().setVisible(false);
		fullUnit.getDeleteBtn().setVisible(false);

		//TODO: disable edit
		//fullUnit.getViewer().getTable().set

		//TODO: disable edit
		selectedUnit = new GenericTableUnit(maincomposite, this.beanType, false);
		selectedUnit.setDataInput(this.selectedSet);
		selectedUnit.init();
		selectedUnit.getAddBtn().setVisible(false);
		selectedUnit.getDeleteBtn().setVisible(false);

		addToAssociationSetBtn = new Button(maincomposite, SWT.PUSH);
		addToAssociationSetBtn.setText("Add to association set");
		addToAssociationSetBtn.addSelectionListener(new SelectionListener() {

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

		removeFromAssociationSetBtn = new Button(maincomposite, SWT.PUSH);
		removeFromAssociationSetBtn.setText("Remove from association set");
		removeFromAssociationSetBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = fullUnit.getViewer().getTable()
						.getSelection();
				for (TableItem selectedItem : selectedItems) {

					if (RecordsAddingUnit.this.beanType
							.isAssignableFrom(selectedItem.getData().getClass())) {
						RecordsAddingUnit.this.selectedSet.remove(selectedItem.getData());
					}
				}
				RecordsAddingUnit.this.fullUnit.refresh();
				RecordsAddingUnit.this.selectedUnit.refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		maincomposite.setBounds(10, 5, 775, 505);
	}
}
