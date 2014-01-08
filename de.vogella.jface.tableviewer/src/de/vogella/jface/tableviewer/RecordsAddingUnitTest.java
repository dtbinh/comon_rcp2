package de.vogella.jface.tableviewer;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import test.reflectonTest.model.ProcessObject;

public class RecordsAddingUnitTest {

	Set<Object> fullSet = new HashSet();
	Set<Object> selectedSet = new HashSet();
	Class beanType = ProcessObject.class;
	GenericTableUnit view;

	@Test
	public void test() {
		Display display = new Display();
		Shell shell = new Shell(display);
		for (int i = 0; i < 3; i++) {
			Object bean = null;
			try {
				bean = beanType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fullSet.add(bean);
		}

		new RecordsAddingUnit(shell, beanType, selectedSet, fullSet);
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public static void main(String a[]){
		new RecordsAddingUnitTest().test();
	}

}
