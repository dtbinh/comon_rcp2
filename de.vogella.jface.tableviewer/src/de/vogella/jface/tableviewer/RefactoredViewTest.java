package de.vogella.jface.tableviewer;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import test.reflectonTest.model.ProcessObject;

public class RefactoredViewTest {

	@Test
	public void test() {
		RefactoredView view = new RefactoredView(ProcessObject.class);
		Display display = new Display();
		Shell shell = new Shell(display);
		view.createPartControl(shell);
		shell.setVisible(true);
		
		shell.pack();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
		
		
	}
	
	public static void main(String a[]){
		new RefactoredViewTest().test();
	}

}
