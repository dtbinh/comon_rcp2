package de.vogella.jface.tableviewer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.metawidget.util.ClassUtils;

import de.vogella.jface.tableviewer.edit.GenericCellLabelProvider;
import de.vogella.jface.tableviewer.edit.GenericEditingSupport;
import de.vogella.jface.tableviewer.filter.GenericFilter;
import de.vogella.jface.tableviewer.filter.PersonFilter;
import de.vogella.jface.tableviewer.sorter.GenericViewerComparator;

/**
 * @author wangwei first instanize then set dataInput & set if hide the search
 *         then init the widgets and table
 */
public class GenericTableUnit {
	public static final String ID = "de.vogella.jface.tableviewer.view";
	private TableViewer viewer;
	private GenericFilter filter;
	private boolean searchServiceVisible = true;

	private Class beanType;
	private Field[] declaredFields;

	public Field[] getDeclaredFields() {
		return declaredFields;
	}

	private int fieldIndex;
	private Collection dataInput;
	private Composite parent;

	public Collection getDataInput() {
		return dataInput;
	}

	public void setDataInput(Collection dataInput) {
		this.dataInput = dataInput;
	}

	Label searchLabel;
	Text searchText = null;

	private GenericViewerComparator comparator;

	public GenericTableUnit(Composite parent, Class beanType, boolean searchServiceVisible) {
		this.parent = parent;
		this.beanType = beanType;
		this.searchServiceVisible = searchServiceVisible;
		declaredFields = this.beanType.getDeclaredFields();
	}


	public void init() {

		// New to support the search
		if (this.searchServiceVisible == true) {
			searchLabel = new Label(parent, SWT.NONE);
			searchLabel.setText("Search: ");
			searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
			searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
					| GridData.HORIZONTAL_ALIGN_FILL));

			searchText.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent ke) {
					filter.setSearchText(searchText.getText());
					viewer.refresh();
				}

			});
		}

		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		createViewer();
		// Set the sorter for the table
		comparator = new GenericViewerComparator(this);
		viewer.setComparator(comparator);
		filter = new GenericFilter(this);
		viewer.addFilter(filter);


	}

	private void createViewer() {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider

		viewer.setInput(dataInput);
		// make the selection available to other views

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {

		for (fieldIndex = 0; fieldIndex < declaredFields.length; fieldIndex++) {

			TableViewerColumn col = createTableViewerColumn(
					declaredFields[fieldIndex].getName(), 100, fieldIndex);
			col.setLabelProvider(new GenericCellLabelProvider(this, fieldIndex));
			col.setEditingSupport(new GenericEditingSupport(viewer,
					declaredFields[fieldIndex]));

		}

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(colNumber);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();

			}
		};
		column.addSelectionListener(selectionAdapter);
		return viewerColumn;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}