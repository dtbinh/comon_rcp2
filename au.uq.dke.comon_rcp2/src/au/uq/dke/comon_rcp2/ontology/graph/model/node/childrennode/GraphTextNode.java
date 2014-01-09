package au.uq.dke.comon_rcp2.ontology.graph.model.node.childrennode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;

import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.PSWTText;

public class GraphTextNode extends PSWTText {

//	@Override
//	protected void internalUpdateBounds(double x, double y, double width,
//			double height) {
//		//super.internalUpdateBounds(x, y, width, height);
//	}

	private static final long serialVersionUID = -871571524212274580L;

	private boolean ignoreInvalidatePaint = false;

	private IGraphNode graphNode;
	
	private Font font = new Font("Verdana", Font.PLAIN, 6);
	
	public GraphTextNode(IGraphNode graphNode){
		this.graphNode = graphNode;
		this.setText("node text");
		this.setFont(font);
	}

	@Override
	public Font getFont() {
		Font font = null;
		if (font == null) {
			font = DEFAULT_FONT;
		}
		return font;
	}

	@Override
	public Paint getPenPaint() {
		Paint paint = null;
		if (paint == null) {
			paint = Color.black;
		}
		return paint;
	}

	@Override
	public void paint(PPaintContext paintContext) {
		// update the text paint - the super paint method doesn't call our
		// getTextPaint() method
		Paint p = getPenPaint();
		if (!p.equals(super.getPenPaint())) {
			ignoreInvalidatePaint = true;
			setPenPaint(getPenPaint());
			ignoreInvalidatePaint = false;
		}
		// the font is never set in the super paint class?
		paintContext.getGraphics().setFont(getFont());
		super.paint(paintContext);
	}

	@Override
	public void invalidatePaint() {
		if (!ignoreInvalidatePaint) {
			super.invalidatePaint();
		}
	}

}
