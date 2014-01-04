package au.uq.dke.comon_rcp2.ontology.ui.model.node.icon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;

import javax.swing.ImageIcon;

import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicGraphNode;
import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicIconNode;
import edu.umd.cs.piccolo.util.PPaintContext;

public class RecordsTableIconNode extends BasicIconNode {

	//private final static String defaultIconFilePath = "F:/Git/comonviz-maven/comonviz-maven/src/main/resources/table.png";
	private final static String defaultIconFilePath = "F:/Git/comonviz-maven/comonviz-maven/src/main/resources/table.png";

	public RecordsTableIconNode(String fileName) {
		super(fileName);
	}

	public RecordsTableIconNode() {
		super();
		ImageIcon icon = new ImageIcon(RecordsTableIconNode.class.getResource("/table.png"));
		Image iconImage = icon.getImage();
		super.setImage(iconImage);
	}

	public Class getRecordType() {
		BasicGraphNode parentNode = (BasicGraphNode) this.getParent();
		Class recordType = parentNode.getRecordType();
		return recordType;
	}

	@Override
	protected void paint(PPaintContext paintContext) {
		super.paint(paintContext);

//		if (this.getRecordType() != null) {
//
//			Graphics2D g2 = paintContext.getGraphics();
//
//			Paint bg = Color.BLUE;
//			g2.setPaint(bg);
//			g2.fill(this.getBounds());
//
//			super.paint(paintContext);
//		}
	}

}
