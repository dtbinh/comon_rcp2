package au.uq.dke.comon_rcp2.constant;

import java.awt.Color;
import java.awt.Paint;

import edu.umd.cs.piccolox.util.PFixedWidthStroke;

/**
 * @author uqwwan10
 * this class is a temporary class to store the discrete constants
 * the constants will finally be stored using the rcp preference service 
 */
public class UIConstants {
	//GraphNode text parameters
	public static final int MAX_TEXT_CHARS = 10;
	public static final int MAX_TOOLTIP_CHARS_IN_A_LINE = 50;
	public static final int MAX_LINES = 5;

	
	//arrow 
	public static final PFixedWidthStroke ARROWHEAD_STROKE = new PFixedWidthStroke(1f);
	public static final Paint ARROWHEAD_FILL = Color.white;
	public static final int ARROWHEAD_SIZE = 4;

}
