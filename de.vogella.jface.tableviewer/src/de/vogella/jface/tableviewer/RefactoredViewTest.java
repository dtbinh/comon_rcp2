package de.vogella.jface.tableviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import test.reflectonTest.model.ProcessRule;

public class RefactoredViewTest {

	@Test
	public void test() {
		Display display = new Display();
		Shell shell = new Shell(display);
		Class beanType = ProcessRule.class;

		List<Object> beanList = new ArrayList();

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
		
		GenericTableUnit view = new GenericTableUnit(shell, beanType, true);
		view.setDataInput(beanList);
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
		new RefactoredViewTest().test();
	}

}
