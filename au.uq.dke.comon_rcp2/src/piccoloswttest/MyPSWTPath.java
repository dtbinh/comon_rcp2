package piccoloswttest;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.PSWTPath;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

public class MyPSWTPath extends PSWTPath{
	@Override
    protected void paint(final PPaintContext paintContext) {
        final SWTGraphics2D g2 = (SWTGraphics2D) paintContext.getGraphics();
        g2.setLineWidth(5.5f);
        super.paint(paintContext);

    }
	
	public MyPSWTPath(Shape shape){
		super(shape);
	}
}
