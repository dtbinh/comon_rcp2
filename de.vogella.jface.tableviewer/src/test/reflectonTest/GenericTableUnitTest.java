package test.reflectonTest;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import test.reflectonTest.model.ProcessRule;

public class GenericTableUnitTest {
	
	List<Object> beanList = new ArrayList();
	Class beanType = ProcessRule.class;
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
			beanList.add(bean);
		}
		
		view = new GenericTableUnit(shell, beanType, true);
		view.setDataInput(beanList);
		view.init();
		
		Button addBtn = new Button(shell, SWT.PUSH);
		addBtn.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				Object bean = null;
				try {
					bean = beanType.newInstance();
				} catch (InstantiationException | IllegalAccessException e2) {
					e2.printStackTrace();
				}
				beanList.add(bean);
				view.refresh();
			
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

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
