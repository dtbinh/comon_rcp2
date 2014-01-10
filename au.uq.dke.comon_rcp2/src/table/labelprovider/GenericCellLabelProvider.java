package table.labelprovider;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.metawidget.util.ClassUtils;

import table.GenericTableUnit;

public class GenericCellLabelProvider extends CellLabelProvider {
	private int columnNumber;
	private GenericTableUnit viewer;

	public int getColumnNumber() {
		return columnNumber;
	}

	public GenericCellLabelProvider(GenericTableUnit viewer, int columnNumber) {
		this.viewer = viewer;
		this.columnNumber = columnNumber;
	}

	@Override
	public void update(ViewerCell cell) {
		try {
			Object fieldValue = ClassUtils.getProperty(cell.getElement(),
					this.viewer.getDeclaredFields()[this.getColumnNumber()].getName());
			cell.setText(fieldValue.toString());
		} catch (RuntimeException e) {
			// TODO exception
		}
	}

}
