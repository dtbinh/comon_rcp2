package au.uq.dke.comon_rcp2.ontology.ui.model.node.icon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.util.List;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.ontology.StyleManager;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PPaintContext;

public class HiddenChildrenCountIcon extends PText {
	private static final long serialVersionUID = -871571524212274580L;

	private boolean ignoreInvalidatePaint = false;
	private GraphNode graphNode;

	public HiddenChildrenCountIcon(GraphNode GraphNode, String text) {
		super();
		this.graphNode = GraphNode;

		// make this node match the text size
		setText(text);
		setTextPaint(Color.white);
		this.setFont(getFont().deriveFont(
				StyleManager.DEFAULT_HIDDEN_CHILDREN_COUNT_TEXT_FONT_SIZE));

	}

	@Override
	protected void paint(PPaintContext paintContext) {
		// update the text paint - the super paint method doesn't call our
		// getTextPaint() method
		Paint p = getTextPaint();
		if (!p.equals(super.getTextPaint())) {
			ignoreInvalidatePaint = true;
			setTextPaint(getTextPaint());
			ignoreInvalidatePaint = false;
		}
		// the font is never set in the super paint class?
		paintContext.getGraphics().setFont(getFont());

		Ellipse2D circle = new Ellipse2D.Double();

		double d = Math.max(this.getWidth(), this.getHeight());
		circle.setFrame(
				this.getX() - Math.abs(this.getWidth() - this.getHeight()) / 2l,
				this.getY(), d, d);

		Graphics2D g2 = paintContext.getGraphics();

		List<GraphNode> children = EntryPoint.getGraphModel().getChildren(
				this.graphNode);

		boolean isExpanded = false;// if any one of the children is visible,
									// then it is expanded

		for (GraphNode child : children) {
			if(child == null){
				int a = 1;
			}
			if (child.isVisible() == true) {
				isExpanded = true;
				break;
			}
		}
		
		if(children == null || children.size() == 0){
			isExpanded = true;
		}

		if (!isExpanded) {
			String childrenCount = String.valueOf(children.size());
			this.setText(childrenCount);
			g2.setPaint(Color.red);
			g2.fill(circle);
			super.paintText(paintContext);
		}
	}

	@Override
	public void invalidatePaint() {
		if (!ignoreInvalidatePaint) {
			super.invalidatePaint();
		}
	}

	public String toString() {
		return this.getText();
	}

}
