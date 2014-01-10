package au.uq.dke.comon_rcp2.data.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import au.uq.dke.comon_rcp2.data.table.model.ProcessObject;
import au.uq.dke.comon_rcp2.data.table.model.ProcessRule;

public class GenericTableUnitTest {
	
	List<Object> beanList = null;
	Class beanType = ProcessObject.class;
	GenericTableUnit view;

	@Test
	public void test() {
		Display display = new Display();
		Shell shell = new Shell(display);


		
		view = new GenericTableUnit(shell, beanType, true);
		
		beanList = view.getBeanList();
		for (int i = 0; i < 3; i++) {
			Object bean = null;
			try {
				bean = beanType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			beanList.add(bean);
		}

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
