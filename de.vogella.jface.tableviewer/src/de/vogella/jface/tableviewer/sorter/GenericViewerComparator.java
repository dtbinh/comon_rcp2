package de.vogella.jface.tableviewer.sorter;

import java.lang.reflect.Field;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.metawidget.util.ClassUtils;

import de.vogella.jface.tableviewer.RefactoredView;

public class GenericViewerComparator extends ViewerComparator {

	private int lastSelectedColumnNumber;
	private int newSelectedColumnNumber;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	private RefactoredView viewer;

	public GenericViewerComparator(RefactoredView viewer) {
		this.viewer = viewer;
		this.lastSelectedColumnNumber = 0;
		direction = DESCENDING;
	}

	public int getDirection() {
		return direction == DESCENDING ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int newSelectedColumnNumber) {
		this.newSelectedColumnNumber = newSelectedColumnNumber;
		if (this.newSelectedColumnNumber == this.lastSelectedColumnNumber) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.lastSelectedColumnNumber = newSelectedColumnNumber;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		
		Field field = this.viewer.getDeclaredFields()[this.newSelectedColumnNumber];
	
		Object e1Field = ClassUtils.getProperty(e1, field.getName());
		Object e2Field = ClassUtils.getProperty(e2, field.getName());

		int rc = e1Field.toString().compareTo(e2Field.toString());

	    if (direction == DESCENDING) {
	        rc = -rc;
	      }
		return rc;
	}

}