package au.uq.dke.comon_rcp2.ontology.graph.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.semanticweb.owlapi.model.OWLEntity;

import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;
import edu.umd.cs.piccolox.util.PFixedWidthStroke;

/**
 * @author uqwwan10 manage the size, font, color, pound of border, background,
 *         line, label,etc
 */
public class StyleManager {

	private static StyleManager instance;

	// colors
	private static List<Color> nodeBackgroundColors;
	private static List<Color> arcColors;

	private static Map<Object, Color> nodeBackgroundColorMap;
	private static Map<Object, Color> arcColorMap;

	final private static float NODE_BACKGROUND_SATUATION = 0.6F;
	final private static float NODE_BACKGROUND_BRIGHTNESS = 1F;
	final private static float NODE_HUE_START = 0.0F;
	final private static float NODE_HUE_END = 0.8F;

	final private static float NODE_BORDER_BRIGHTNESS_GAIN = 0.2F;
	final private static float ARC_SATUATION = 179 / 256F;
	final private static float ARC_BRIGHTNESS = 0.7F;
	final private static float ARC_HUE_START = 0.58F;
	final private static float ARC_HUE_END = 0.1F;

	private static int MAX_ALPHA = 255;
	private static int MIN_ALPHA = 50;

	private final static int defultArcColorCount = 5;
	private static Color[] defaultArcColors;

	public static final Color DEFAULT_TOOLTIP_BACKGROUND_COLOR = new Color(255,
			255, 225);// standard tooltip color in eclipse editor

	// strokes
	final private static float MAX_BORDER_STROKE_WIDTH = 2F;
	final private static float BORDER_STROKE_WIDTH_SELECTION_INCREEMENT = 1F;
	final public static float DEFAULT_ARC_WIDTH = 1F;

	// fonts
	public static final float DEFAULT_NODE_TEXT_FONT_SIZE = 12f;
	public static final float DEFAULT_ARC_LABEL_TEXT_FONT_SIZE = 10f;
	final public static float DEFAULT_HIDDEN_CHILDREN_COUNT_TEXT_FONT_SIZE = 8F;
	final public static float DEFAULT_TOOLTIP_TEXT_FONT_SIZE = 10F;

	public static Font BASE_FONT = new Font("SansSerif", Font.PLAIN, 12);
	public static Font TOOLTIP_FONT = BASE_FONT
			.deriveFont(DEFAULT_TOOLTIP_TEXT_FONT_SIZE);

	// misc
	public final static float DEFAULT_ARROW_HEAD_SIZE = 4F;

	// annotation textpane

	public static final Color ANNOTATION_CLASS_COLOR = new Color(0, 0, 192);// standard
																			// blue
																			// color
																			// in
																			// eclipse
																			// editor
	public static final String ANNOTATION_CLASS_COLOR_HEX = "\"#1E90FF\"";//

	public static final String ANNOTATION_ATTRIBUTES_COLOR_HEX = "\"7F0055\""; // dark
																				// red
																				// like
																				// keywords
																				// in
																				// eclipse

	public static final String ANNOTATION_RELATIONSHIP_COLOR_HEX = "\"#8B008B\"";;

	private StyleManager() {
		defaultArcColors = new Color[defultArcColorCount];

		defaultArcColors[0] = new Color(253, 9, 2);
		defaultArcColors[1] = new Color(255, 50, 50);// red
		defaultArcColors[1] = new Color(11, 166, 150);// green blue
		defaultArcColors[2] = new Color(37, 234, 15);
		defaultArcColors[3] = new Color(217, 12, 222);// pink
		defaultArcColors[4] = new Color(4, 105, 230);// blue

		generateNodeColors();
		generateArcColors();
	}

	public Stroke getArcStroke(Object arc) {
		return new PFixedWidthStroke(DEFAULT_ARC_WIDTH);
	}


	public static StyleManager getStyleManager() {
		if (instance == null) {
			instance = new StyleManager();
		}
		return instance;
	}

	/**
	 * generate colors apply for each branch
	 * 
	 * @param numBranches
	 *            , the number of branches
	 */
	private static void generateNodeColors() {
		OntologyClass root = EntryPoint.getOntologyRelationshipService()
				.findRoot();
		int numBranches = EntryPoint.getOntologyRelationshipService()
				.findChildren(root).size();
		nodeBackgroundColorMap = new HashMap();
		int numColors = numBranches + 1;
		nodeBackgroundColors = new ArrayList<Color>(numColors);
		float hueDistance = (NODE_HUE_END - NODE_HUE_START) / numColors;

		for (int i = 0; i < numColors; i++) {
			Color color = Color.getHSBColor(NODE_HUE_START + hueDistance * i
					+ 0.01f, NODE_BACKGROUND_SATUATION,
					NODE_BACKGROUND_BRIGHTNESS);
			nodeBackgroundColors.add(color);
		}

		return;
	}

	private static void generateArcColors() {
		
		Collection arcTypes = EntryPoint.getOntologyAxiomService().findAll();
		arcColorMap = new HashMap();
		arcColors = new ArrayList<Color>(arcTypes.size());
		float hueDistance = (ARC_HUE_END - ARC_HUE_START) / arcTypes.size();

		for (int i = 0; i < defultArcColorCount; i++) {
			arcColors.add(defaultArcColors[i]);
		}
		for (int i = defultArcColorCount; i < arcTypes.size(); i++) {
			Color color = Color.getHSBColor(ARC_HUE_START + hueDistance * i,
					ARC_SATUATION, ARC_BRIGHTNESS);
			arcColors.add(color);
		}

		int i = 0;
//		for (Object o : arcTypes) {
//			arcColorMap.put(o, arcColors.get(i++));
//		}

	}

	public Stroke getNodeBorderStroke(IGraphNode graphNode) {
		int level = ((OntologyClass) graphNode.getUserObject()).getLevel();
		float normalBorderStrokeWidth = this.MAX_BORDER_STROKE_WIDTH / level;
		float borderStrokeWidth = graphNode.isSelected() ? normalBorderStrokeWidth
				: normalBorderStrokeWidth
						+ this.BORDER_STROKE_WIDTH_SELECTION_INCREEMENT;
		return new PFixedWidthStroke(borderStrokeWidth);

	}

	public Color getNodeBackgroundColor(IGraphNode node) {

		Object branchGraphNode = EntryPoint.getGraphModel().getBranchGraphNode(
				node);

		try{
		if (nodeBackgroundColorMap.get(branchGraphNode) == null) {
			try {
				nodeBackgroundColorMap.put(branchGraphNode,
						nodeBackgroundColors.get(0));
				nodeBackgroundColors.remove(0);
			} catch (IndexOutOfBoundsException e) {
				int a = 1;
			}
		}
		}catch (NullPointerException e){
			e.printStackTrace();
		}

		Color branchColor = nodeBackgroundColorMap.get(branchGraphNode);
		int level = ((OntologyClass) node.getUserObject()).getLevel();
		int maxLevel = 5;

		Color nodeColor = null;
		try {
			nodeColor = new Color(branchColor.getRed(), branchColor.getGreen(),
					branchColor.getBlue(), MAX_ALPHA - (MAX_ALPHA - MIN_ALPHA)
							* (level - 1) / (maxLevel - 1));

			// float[] hsbValue;
			// hsbValue = Color.RGBtoHSB(branchColor.getRed(),
			// branchColor.getGreen(), branchColor.getBlue(), null);
			//
			// nodeColor = Color.getHSBColor(hsbValue[0], hsbValue[1] -
			// (NODE_BACKGROUND_SATUATION - 0.1f) * (level - 1f)/(maxLevel -1f),
			// hsbValue[2]);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return nodeColor;
	}

	public Color getNodeBorderColor(IGraphNode graphNode) {
		Color backgroundColor = getNodeBackgroundColor(graphNode);

		// ColorSpace colorSpace = new ColorSpace(ColorSpace.TYPE_HSV, 3);
		return backgroundColor.darker().darker();
	}

	public Color getArcColor(Object arc) {

		if (arcColorMap.get(arc) == null) {
			arcColorMap.put(arc, arcColors.get(0));
			arcColors.remove(0);
		}

		return arcColorMap.get(arc);
	}

}
