package de.vogella.jface.tableviewer.sorter;

import java.lang.reflect.Field;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.metawidget.util.ClassUtils;

import de.vogella.jface.tableviewer.model.Person;

public class GenericViewerComparator extends ViewerComparator {

	private int direction = SWT.DOWN;
	private Field field;
	
	public GenericViewerComparator(Field field) {
		this.field = field;
	}



	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Object e1Field = ClassUtils.getProperty(e1, field.getName());
		Object e2Field = ClassUtils.getProperty(e2, field.getName());
		
		int rc = e1Field.toString().compareTo(e2Field.toString());
		
		if (direction == SWT.DOWN) {
			rc = -rc;
			direction = SWT.UP;//toggle
		} else if( direction == SWT.UP){
			direction = SWT.DOWN;
		}
		
		return rc;
	}

}