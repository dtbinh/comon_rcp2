package de.vogella.jface.tableviewer;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

import de.vogella.jface.tableviewer.model.ModelProvider;

public class RefactoredView {
	public static final String ID = "de.vogella.jface.tableviewer.view";
	private TableViewer viewer;
	
	private Class beanType;
	private Field[] declaredFields;
	
	private int i;
	
	
	public  RefactoredView(Class beanType){
		this.beanType = beanType;
		declaredFields = this.beanType.getDeclaredFields();

	}

	public void createPartControl(Composite parent) {
		   GridLayout layout = new GridLayout(2, false);
		    parent.setLayout(layout);
		    Label searchLabel = new Label(parent, SWT.NONE);
		    searchLabel.setText("Search: ");
		    final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		    searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
		        | GridData.HORIZONTAL_ALIGN_FILL));
		    createViewer(parent);
		    // Set the sorter for the table

		    // New to support the search
		    searchText.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent ke) {
		        viewer.refresh();
		      }

		    });

	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		//TODO: fix this
		

		List<Object> beanList = new ArrayList();

		for(int i = 0 ; i < 3; i ++ ){
			Object bean = null;
			try {
				bean = this.beanType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			beanList.add(bean);
		}
		
		viewer.setInput(beanList);
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

	class MyCellLabelProvider extends CellLabelProvider{
		private int columnNumber;
			
		public int getColumnNumber() {
			return columnNumber;
		}
		public MyCellLabelProvider(int columnNumber){
			this.columnNumber = columnNumber;
		}
		@Override
		public void update(ViewerCell cell) {
			Object fieldValue = ClassUtils.getProperty(cell.getElement(), declaredFields[this.getColumnNumber()].getName());
			cell.setText(fieldValue.toString());
		}
		
		
		
	}
	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {
		
		
		for(i = 0 ; i < declaredFields.length; i ++){
			
			TableViewerColumn col = createTableViewerColumn(declaredFields[i].getName(), 100, i);
			col.setLabelProvider(new MyCellLabelProvider(i));
			//col.setEditingSupport(new FirstNameEditingSupport(viewer));
			
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