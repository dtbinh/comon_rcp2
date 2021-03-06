package au.uq.dke.comon_rcp2.data.table;

import java.util.Collection;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import au.uq.dke.comon_rcp2.data.service.MockDataServiceImpl;
import au.uq.dke.comon_rcp2.data.table.model.ProcessObject;

public class GenericTableUnitTest {
	
	List<Object> beanList = null;
	Class beanType = ProcessObject.class;
	GenericTableUnit view;

	@Test
	public void test() {
		Display display = new Display();
		Shell shell = new Shell(display);


		Collection dataInput = MockDataServiceImpl.getInstance().getDataSet(beanType);
		view = new GenericTableUnit(shell, beanType, dataInput, true);
		view.init();
		

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	public static void main(String a[]) {
		new GenericTableUnitTest().test();
	}

}
