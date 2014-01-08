package de.vogella.jface.tableviewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class CompositeTest
{
    public static void main(String[] args)
    {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        shell.setText("Header");
        Composite main = new Composite(shell, SWT.NONE);
        main.pack();
        main.setLayout(new GridLayout(1, false));
  //      Image bg = new Image(display, "resources/bg.png");
        Image bg = null;
        main.setBackgroundImage(bg);
        main.setBackgroundMode(SWT.INHERIT_DEFAULT);

        Label label = new Label(main, SWT.NONE);    
        //label.setLayoutData(new GridData(GridData.FILL_BOTH));
        label.setText("Label 1");

        main.setBounds(10, 5, 775, 505);
        shell.pack();
        shell.open();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
}